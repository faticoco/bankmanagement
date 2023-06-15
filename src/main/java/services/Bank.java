package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Bank {
    private Admin A;
    private ArrayList<Employee> E;
    private ArrayList<Account> Aa;
    private TransactionLedger t;
    private LoanLedger l;
    private LoanReqList ll;
    private Employee curr;
    private Customer curr_cust;
    private LoanRequest curr_l;
    private ArrayList<Customer> C;
    private int acc_count;
    private int emp_count;
    private int loan_count;
    private int loanr_count;
  

   
    
 public ArrayList<Employee> getE() {
 	return this.E;
 }
 public void setE(ArrayList<Employee> E) {
 	this.E = E;
 }


public LoanRequest getCurr_l() {
	return this.curr_l;
}
public void setCurr_l(LoanRequest curr_l) {
	this.curr_l = curr_l;
}


public Employee getCurr() {
	return this.curr;
}
public void setCurr(Employee curr) {
	this.curr = curr;
}



    public String get_curr_cust_cnic()
    {
        return curr_cust.getCnic();
    }
    public String get_curr_cust_name()
    {
        return curr_cust.getName();
    }
    public String get_curr_cust_Phone()
    {
        return curr_cust.getPhone();
    }
    public Boolean validate_pin(int pin)
    {
        if(curr_cust.getA().getPIN()==pin)
        {
            return true;
        }
        return false;
    }
    public Boolean pass(String pass)
    {
        if(curr_cust.getPassword().equals(pass))
        {
            return true;
        }
        return false;
    }


    public String get_id_curr_customer() {
       return curr_cust.getA().getAcc_id();
    }

    public String get_last_active_curr_customer() {
        return curr_cust.getA().getLastactive();
     }

     public String get_balance_curr_customer() {
        return curr_cust.getA().getBalance().toString();
     }

    public Customer getCurr_cust() {
    	return this.curr_cust;
    }
    public void setCurr_cust(Customer curr_cust) {
    	this.curr_cust = curr_cust;
    }
    public Bank()
    {
        acc_count=0;
        emp_count=0;
        C = new ArrayList<Customer>();
        E = new ArrayList<Employee>();
        A = new Admin();
        Aa = new ArrayList<Account>();
        t = new TransactionLedger();
        l = new LoanLedger();
        ll = new LoanReqList();
    
    }
    public Boolean add_employee(String name,String password,String cnic)
    {
        Dbcontroller db = Dbcontroller.get();
        E=db.get_emp();
        for(Employee e: E)
        {
            if(e.getEmployeeCnic().equals(cnic))
            {
                return false;
            }
        }

        emp_count+=1;
        //adding employee to db
      
        db.add_employee_to_db("EMP "+emp_count, name, password,cnic, 1, 1, 1, 1);
       
       
        A.create_employee(E,name, password,"EMP"+emp_count,cnic);
       
        return true;
        
    }
    public boolean remove_Employee(String cn)
    {
        Dbcontroller db = Dbcontroller.get();
        E=db.get_emp();
        if(A.remove_Employee(E, cn))
        {
            Dbcontroller d= Dbcontroller.get();
            d.remove_employee_from_db(cn);
            return true;

        }
        return false;
        
    }
    public void init()
    {
        Dbcontroller db = Dbcontroller.get();
        // E=db.get_emp();
        // ll.setL(db.get_all_loan_requests());
        // Aa= db.get_all_accounts(ll.getL());
        // l.setL(db.get_all_loans(Aa));
        // C=db.get_allCustomers(Aa);
        
        
        // t.setT(db.get_transaction());
        Map<String,Integer> m=db.get_counts();
        acc_count=m.get("acc");
        emp_count=m.get("emp");
        loan_count=m.get("loan");
        loanr_count=m.get("lonr_req");

    }
      
    public String AccJson()
    {
        Dbcontroller d=Dbcontroller.get();
        Aa=d.get_all_Accounts();
        curr=d.getEmployee(curr.getEmployeeCnic());
        ObjectMapper m= new ObjectMapper();
        String s="";
        try {
            s=m.writeValueAsString(Aa);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
    public void set_Acc_status(boolean x,String acc_id)
    {
        for(Account a:Aa)
        {
            if(a.getAcc_id().equals(acc_id))
            {
                a.setStatus(x);
                Dbcontroller d=Dbcontroller.get();
                d.update_acc_status(acc_id, x);

            }
        }
    }
    public void app_loan()

    {   
        loan_count++;
        l.add(curr.approve_loan(loan_count,curr_l,ll,Aa));
        Dbcontroller d=Dbcontroller.get();
        d.add_loan_to_db(curr_l.getAcc_id(),loan_count, curr_l.getAmount(),0.0, curr_l.getInterestRate(), curr_l.getDuration());
        d.remove_loan_req_from_db(curr_l.getReqid());
        curr_l=null;


       
    }
    public void rej_loan()
    {
        curr.reject_loan(curr_l,ll);
        Dbcontroller d=Dbcontroller.get();
        d.remove_loan_req_from_db(curr_l.getReqid());
        curr_l=null;
    }
    public String create_account(String name,String cnic,int PIN)
    {
        Dbcontroller d=Dbcontroller.get();
        Aa=d.get_all_Accounts();
        curr=d.getEmployee(curr.getEmployeeCnic());
        
        for (Account account : Aa) {
            if(account.getCnic().equals(cnic))
            {
                return "Account with the Cnic exsists";
            }
        }
        if(curr.getCreate())
        {
            acc_count+=1;
            Aa.add(curr.create_account("PK "+acc_count, name, cnic, PIN));
            d.add_account_to_db("PK "+acc_count, name, cnic,  0.0,PIN, true, LocalDate.now().toString());
            for(Account A:Aa)
            {
                System.out.println(A.getCnic());
            }
            return "Account succesfully created with Account id PK " +acc_count;
        }
        return "Access Error";
    }
    public boolean remove_account(String id)
    {
        Dbcontroller d=Dbcontroller.get();
        Aa=d.get_all_Accounts();
        curr=d.getEmployee(curr.getEmployeeCnic());
        if(curr.getDelete())
        {

            if(curr.remove_account(C,Aa, id))
            {
                
                d.remove_account_from_db(id);
                d.remove_cust(id);
                return true;

            }
            return false;
        }
        return false;
    }
    public String deposit(String id,Double amount,int PIN)
    {
        Dbcontroller d=Dbcontroller.get();
        Aa=d.get_all_Accounts();
        curr=d.getEmployee(curr.getEmployeeCnic());
        if(curr.getDeposit())
        {
            String st=curr.deposit(Aa,id,amount,PIN);
            if(st.equals("Success"))
            {
                //get loacal date as string
                LocalDate date = LocalDate.now();
                String date_str = date.toString();

                Transaction Ts=new Transaction("Deposit",amount,id,date_str);
                d.add_transaction_to_db("Deposit",amount,id,date_str);
                d.update_balance(id, amount, date_str);
                t.addTransaction(Ts);

            }
            return st;

        }
        return "Emp access error";
    }
    public String withdraw(String id, Double amount,int PIN)
    {
        
        Dbcontroller d=Dbcontroller.get();
        Aa=d.get_all_Accounts();
        curr=d.getEmployee(curr.getEmployeeCnic());
        if(curr.getWithdraw())
        {
            String st=curr.withdraw(Aa,id,amount,PIN);
            if(st.equals("Success"))
            {
                //get loacal date as string
                LocalDate date = LocalDate.now();
                String date_str = date.toString();
                t.addTransaction(new Transaction("Withdraw",amount,id,date_str));
                d.update_balance(id, -amount, date_str);
                d.add_transaction_to_db("Withdraw",-amount,id,date_str);
            }
            return st;
        }
        return "Emp access error";
    }
    public boolean adminlogin(String name,String password) {
        	return A.login(name, password);

        
    }
    public Boolean emplogin(String cnic,String password)
    {
        Dbcontroller d= Dbcontroller.get();
        if( d.emp_login(cnic, password))
        {
            curr=d.getEmployee(cnic);
            return true;

        
        }
        return false;

    }
    public String Custsignup(String cnic,String acc_id,String password)
    {
        Dbcontroller d=Dbcontroller.get();
        Aa=d.get_all_Accounts();
        for(Account a:Aa)
        {
            if(a.getAcc_id().equals(acc_id))
            {
                for(Customer c: C)
                {
                    if(c.getCnic().equals(cnic))
                    {
                        return "Customer with this cnic already exists";
                    }
                }
                if(!a.getCnic().equals(cnic))
                {
                    return "Account and cnic do not match";
                }
                Customer temp=new Customer();
                temp.setA(a);
                temp.setCnic(cnic);
                temp.setPassword(password);
                temp.setName(a.getName());
                C.add(temp);
               
                d.add_customer_to_db(cnic, acc_id, password,a.getName());
                return "Success";

            }
        }
        return "Account not found";

    }
    public Boolean custlogin(String cnic,String password) 
    {

            Dbcontroller d= Dbcontroller.get();
        if( d.cust_login(cnic, password))
        {
            curr_cust=d.getCustomer(cnic);
            return true;

        
        }
        return false;
        
    }
    public void updatecustpass(String pass)
    {
        curr_cust.setPassword(pass);
        Dbcontroller d=Dbcontroller.get();
        d.update_cust_pass(curr_cust.getCnic(),pass);
    }
    public void updateemppass(String pass)
    {
        curr.setEmployeePassword(pass);
        Dbcontroller d=Dbcontroller.get();
        d.update_emp_pass(curr.getEmployeeCnic(),pass);


    }
    public void updatepin(int PIN)
    {
        curr_cust.getA().setPIN(PIN);
        Dbcontroller d=Dbcontroller.get();
        d.update_acc_pin(curr_cust.getA().getAcc_id(),PIN);
    }
    public String transfer(String id,Double amount,int PIN)
    {
        Dbcontroller d=Dbcontroller.get();
        Aa=d.get_all_Accounts();
        String st=curr_cust.transfer(Aa, id, amount, PIN , curr_cust);
        if(st.equals("Success"))
        {
            LocalDate ts=LocalDate.now();
            String date=ts.toString();
            t.addTransaction(new Transaction("Transfer out",amount,curr_cust.getA().getAcc_id(),date));
            t.addTransaction(new Transaction("Transfer in",amount,id,date));
            d.add_transaction_to_db("Transfer out",amount,curr_cust.getA().getAcc_id(),date);
            d.add_transaction_to_db("Transfer in",amount,id,date);
        }
        return st;
    }
    public String TransactionJson()
    {
        Dbcontroller d=Dbcontroller.get();
        t.setT(d.get_transaction());
        return t.toJson(curr_cust.getA().getAcc_id());
    }
    public String EmpJson()
    {
        Dbcontroller db = Dbcontroller.get();
        E=db.get_emp();
        return Employee.toJson(E);
        
    }
    public void apply_for_loan(double loanAmount ,  Double loanDuration , Double interestRate  , String repayment)
    {

       loanr_count++;
       LoanRequest l= curr_cust.Apply_for_loan(loanr_count,loanAmount , loanDuration , interestRate , repayment, curr_cust.getA().getAcc_id());
       ll.add(l);
        Dbcontroller d=Dbcontroller.get();
        d.add_loan_req_to_db(l.getReqid(),l.getAmount(),Double.parseDouble(l.getRepayment()),l.getInterestRate(),l.getDuration(),curr_cust.getA().getAcc_id());

       
       
    }
    public void set_curr_loan(String acc_id)
    {
        for (int i=0 ; i<ll.getL().size() ; i++)
        {
            if (ll.getL().get(i).getAcc_id().equals(acc_id))
            {
                curr_l = ll.getL().get(i);
            }
        }
    }
    public String getLoanReqlist()
    {
        Dbcontroller d=Dbcontroller.get();
        ll.setL(d.get_all_loan_requests());
        return ll.toJson();
    }
    public void synccust()
    {
        Dbcontroller d=Dbcontroller.get();
        curr_cust=d.getCustomer(get_curr_cust_cnic());
    }

    public void setEmpstatus(String Empid,boolean create,boolean delete,boolean deposit,boolean withdraw)
    {
        for(Employee e: E)
        {
            if(e.getEmployeeId().equals(Empid))
            {
                e.setCreate(create);
                e.setDelete(delete);
                e.setWithdraw(withdraw);
                e.setDeposit(deposit);
                Dbcontroller d=Dbcontroller.get();
                d.update_emp_status(Empid,create,delete,withdraw,deposit);
            }
        }
    }
    public LoanReqList get_loan_requReqList()
    {
        return ll;
    }
    public Admin getA() {
        return A;
    }
    public void setA(Admin a) {
        A = a;
    }
    public ArrayList<Account> getAa() {
        return Aa;
    }
    public void setAa(ArrayList<Account> aa) {
        Aa = aa;
    }
    public TransactionLedger getT() {
        return t;
    }
    public void setT(TransactionLedger t) {
        this.t = t;
    }
    public LoanLedger getL() {
        return l;
    }
    public void setL(LoanLedger l) {
        this.l = l;
    }
   
    public ArrayList<Customer> getC() {
        return C;
    }
    public void setC(ArrayList<Customer> c) {
        C = c;
    }
    public int getAcc_count() {
        return acc_count;
    }
    public void setAcc_count(int acc_count) {
        this.acc_count = acc_count;
    }
    public int getEmp_count() {
        return emp_count;
    }
    public void setEmp_count(int emp_count) {
        this.emp_count = emp_count;
    }
    public boolean getStatus(Account a)
    {
        return a.getStatus();

    }
    public void updateemp(String name,String phone)
    {
        if(name!=null && phone!=null && name.equals(curr.getEmployeeName()) && phone.equals(curr.getEmployeePhone()))
        {
            return;
        }
        getCurr().setEmployeeName(name);
        getCurr().setEmployeePhone(phone);
        Dbcontroller d=Dbcontroller.get();
        d.update_emp(curr.getEmployeeCnic(),name,phone);
    }
    public void updatecust(String name,String phone)
    {
        if(name!=null && phone!=null && name.equals(curr_cust.getName()) && phone.equals(curr_cust.getPhone()))
        {
            return;
        }
        getCurr_cust().setName(name);
        getCurr_cust().setPhone(phone);
        Dbcontroller d=Dbcontroller.get();
        d.update_cust(curr_cust.getCnic(),name,phone);
    }
    public String get_curr_Emp_id()
    {
        return curr.getEmployeeId();
    }
    public String get_curr_Emp_name()
    {
        return curr.getEmployeeName();
    }
    public String get_curr_Emp_phone()
    {
        return curr.getEmployeePhone();
    }
    public String get_curr_emp_pass()
    {
        return curr.getEmployeePassword();
    }
    public String get_curr_loan_id()
    {
        return curr_l.getAcc_id();
    }
    public String get_curr_loan_amount()
    {
        return Double.toString(curr_l.getAmount());
    }
    public String get_curr_loan_rate()
    {
        return Double.toString(getCurr_l().getInterestRate());
    }

}
   