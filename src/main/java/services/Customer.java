package services;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    private String name;
    private String password;
    private String phone;
    private String cnic;
    private Account a;
    
  public String getName() {
  	return this.name;
  }
  public void setName(String name) {
  	this.name = name;
  }




    public String getPassword() {
    	return this.password;
    }
    public void setPassword(String password) {
    	this.password = password;
    }


    public String getPhone() {
    	return this.phone;
    }
    public void setPhone(String phone) {
    	this.phone = phone;
    }


    public String getCnic() {
    	return this.cnic;
    }
    public void setCnic(String cnic) {
    	this.cnic = cnic;
    }


    public Account getA() {
    	return this.a;
    }
    public void setA(Account a) {
    	this.a = a;
    }

    public Customer(String name,String password,String cnic)
    {
        this.name=name;
        this.password=password;
        this.cnic=cnic;
    }
    public Customer(String name,String password,String cnic,String phone)
    {
        this.name=name;
        this.password=password;
        this.cnic=cnic;
        this.phone=phone;
    }
    public Customer(String name,String password,String cnic,String phone,Account a)
    {
        this.name=name;
        this.password=password;
        this.cnic=cnic;
        this.phone=phone;
        this.a=a;
    }
    public Customer() {
    }
    public String transfer(ArrayList<Account> a,String id,Double amount,int PIN ,Customer curr)
    {
        for(Account acc:a)
        {
            if(acc.getAcc_id().equals(id))
            {
                if(acc.getPIN()==PIN)
                {
                    if(acc.getStatus())
                    {
                        if(curr.a.getBalance()>=amount)
                        {
                            acc.setBalance(acc.getBalance()+amount);
                            curr.a.setBalance(curr.a.getBalance()-amount);
                            return "Success";
                        }
                        else
                        {
                            return "Insufficient Balance";
                        }
                    }
                    else
                    {
                        return "Account is blocked";
                    }
                }
                else
                {
                    return "Incorrect PIN";
                }
            }
        }
        return "Account not found";
    }
    
    
    public LoanRequest Apply_for_loan(int req_id,double loanAmount ,  Double loanDuration , double interestRate ,String repay , String id)
    {
      LoanRequest loanRequest = new LoanRequest();
      loanRequest.setReqid(req_id);
      loanRequest.setAcc_id(id);
      loanRequest.setRepayment(repay);
      loanRequest.setAmount(loanAmount);
      loanRequest.setDuration(loanDuration);
      loanRequest.setInterestRate(interestRate);
      a.getLs().add(loanRequest);
      return loanRequest;
    }

}
