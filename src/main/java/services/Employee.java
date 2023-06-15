package services;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {
    private String employeeName;
    private String employeeId;
    private String employeePassword;
    private String employeePhone;
    private String employeeCnic;
    private Boolean withdraw;
    private Boolean deposit;
    private Boolean create;
    private Boolean delete;


 public String getEmployeeName() {
 	return this.employeeName;
 }
 public void setEmployeeName(String employeeName) {
 	this.employeeName = employeeName;
 }


    public String getEmployeeId() {
    	return this.employeeId;
    }
    public void setEmployeeId(String employeeId) {
    	this.employeeId = employeeId;
    }


    public String getEmployeePassword() {
    	return this.employeePassword;
    }
    public void setEmployeePassword(String employeePassword) {
    	this.employeePassword = employeePassword;
    }


    public String getEmployeePhone() {
    	return this.employeePhone;
    }
    public void setEmployeePhone(String employeePhone) {
    	this.employeePhone = employeePhone;
    }


    public String getEmployeeCnic() {
    	return this.employeeCnic;
    }
    public void setEmployeeCnic(String employeeCnic) {
    	this.employeeCnic = employeeCnic;
    }


    public Boolean getWithdraw() {
    	return this.withdraw;
    }
    public void setWithdraw(Boolean withdraw) {
    	this.withdraw = withdraw;
    }


    public Boolean getDeposit() {
    	return this.deposit;
    }
    public void setDeposit(Boolean deposit) {
    	this.deposit = deposit;
    }


    public Boolean getCreate() {
    	return this.create;
    }
    public void setCreate(Boolean create) {
    	this.create = create;
    }


    public Boolean getDelete() {
    	return this.delete;
    }
    public void setDelete(Boolean delete) {
    	this.delete = delete;
    }

 
    

    Employee()
    {
        this.employeeName = "";
        this.employeeId = "";
        this.employeePassword = "";
        this.employeePhone = "";
        this.withdraw = true;
        this.deposit = true;
        this.create = true;
        this.delete = true;
    }
    Employee(String employeeName, String employeeId, String cnic, String employeePassword, String employeePhone, Boolean withdraw, Boolean deposit, Boolean create, Boolean delete)
    {
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.employeeCnic = cnic;
        this.employeePassword = employeePassword;
        this.employeePhone = employeePhone;
        this.withdraw = withdraw;
        this.deposit = deposit;
        this.create = create;
        this.delete = delete;
    }
    public Employee(String name,String pass,String id,String cnic)
    {
        this.employeeName = name;
        this.employeePassword = pass;
        this.employeeId=id;
        this.employeeCnic=cnic;
        //set all access to true
        this.withdraw = true;
        this.deposit = true;
        this.create = true;
        this.delete = true;
    }
    public String toString()
    {
        return "Employee Name: " + this.employeeName + "\nEmployee ID: " + this.employeeId + "\nEmployee Password: " + this.employeePassword + "\nEmployee Phone: " + this.employeePhone + "\nWithdraw: " + this.withdraw + "\nDeposit: " + this.deposit + "\nCreate: " + this.create + "\nDelete: " + this.delete;
    }
    public Account create_account(String acc_id,String name,String CNIC,int PIN)
    {
        Account a = new Account(acc_id,name,CNIC,0.0,PIN);
        return a;
    }
    public boolean remove_account(ArrayList<Customer> c,ArrayList<Account> a,String id)
    {
        for(Account A:a)
        {
            if(A.getAcc_id().equals(id))
            {
                a.remove(A);
                for(Customer C:c)
                {
                    if(C.getA().getAcc_id().equals(id))
                    {
                        c.remove(C);
                    }
                }
                return true;
            }
        }
        return false;
    }
    public String deposit(ArrayList<Account> a,String id,Double amount,Integer Pin)
    {
        for(Account A:a)
        {
            if(A.getAcc_id().equals(id))
            {
                if(!A.getStatus())
                {
                    return "Blacklisted Account";
                }
                if(A.getPIN()==(Pin))
                {
                    A.getBalance();
                    A.deposit(amount);
                    System.out.println(A.getBalance());
                    return "Success";
                }
                else
                {
                    return "Wrong PIN";
                }
            }
        }
        return "Account Not Found";
    }
    public String withdraw(ArrayList<Account> Aa,String id,Double amount,int Pin)
    {
        for(Account A:Aa)
        {
            if(A.getAcc_id().equals(id)) 
            {
                if(!A.getStatus())
                {
                    return "Blacklisted Account";
                }
                if(A.getPIN()==(Pin))
                {
                    A.getBalance();
                    return A.withdraw(amount);
                }
                else
                {
                    return "Wrong PIN";
                }
            }
        }
        return "Account Not Found";
        
    }
    public Boolean login(String id,String password)
    {
        if(this.employeeId.equals(id) && this.employeePassword.equals(password))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Loan approve_loan(int id,LoanRequest L,LoanReqList ll,ArrayList<Account> A)
    {
        Loan l = new Loan(id,L.getAmount(),L.getInterestRate(),L.getDuration());
        ll.remove(L);
        for(Account a:A)
        {
            if(a.getAcc_id().equals(L.getAcc_id()))
            {
                a.setL(l);
                a.getLs().remove(L);
            }
        }
        return l;
    }
    public void reject_loan(LoanRequest L,LoanReqList ll)
    {
        ll.remove(L);

    }
    public void activate_account(ArrayList<Account> a,String acc_id)
    {
        for(Account A:a)
        {
            if(A.getAcc_id().equals(acc_id))
            {
                A.setStatus(true);
            }
        }
    }
    public void deactivate_account(ArrayList<Account> a,String acc_id)
    {
        for(Account A:a)
        {
            if(A.getAcc_id().equals(acc_id))
            {
                A.setStatus(false);
            }
        }
    }
    public static String toJson(ArrayList<Employee> e)
    {
        ObjectMapper m=new ObjectMapper();
        String s="";
        try
        {
            s=m.writeValueAsString(e);
        }
        catch(Exception e1)
        {
            System.out.println(e1);
        }
        return s;
        
    }





    
}
