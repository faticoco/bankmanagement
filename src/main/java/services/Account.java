package services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
    private String acc_id;
    private String name;
    private String cnic;
    private Double balance;
    private int PIN;
    private Loan L;
    private ArrayList<LoanRequest> Ls=new ArrayList<>();
    
    private Boolean status;
    private String lastactive;
    
        public Loan getL() {
            return this.L;
        }
        public void setL(Loan L) {
            this.L = L;
        }
    
    
        public ArrayList<LoanRequest> getLs() {
            return this.Ls;
        }
        public void setLs(ArrayList<LoanRequest> Ls) {
            this.Ls = Ls;
        }
    
    public String getCnic() {
        return this.cnic;
    }
    public void setCnic(String cnic) {
        this.cnic = cnic;
    }
  public String getLastactive() {
  	return this.lastactive;
  }
  public void setLastactive(String lastactive) {
  	this.lastactive = lastactive;
  }


 public Boolean getStatus() {
 	return this.status;
 }
 public void setStatus(Boolean status) {
 	this.status = status;
 }


public int getPIN() {
	return this.PIN;
}
public void setPIN(int PIN) {
	this.PIN = PIN;
}


   public String getAcc_id() {
   	return this.acc_id;
   }
   public void setAcc_id(String acc_id) {
   	this.acc_id = acc_id;
   }


    public String getName() {
    	return this.name;
    }
    public void setName(String name) {
    	this.name = name;
    }


  


    public Double getBalance() {
    	return this.balance;
    }
    public void setBalance(Double balance) {
    	this.balance = balance;
    }

    public Account()
    {
        this.acc_id = "0";
        this.name = "0";
        status=true;
        this.balance = 0.0;
        
    
    }
    public Account(String acc_id,String name,String Cnic,Double balance,int x)
    {
        this.acc_id = acc_id;
        this.name = name;
        this.cnic = Cnic;
        this.balance = balance;
        this.PIN = x;
        status=true;
        LocalDate currentDate = LocalDate.now();

        // Format the date as a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        lastactive = currentDate.format(formatter);
        Ls=new ArrayList<>();
    }
    public void deposit(Double amount)
    {
        this.balance += amount;
        LocalDate currentDate = LocalDate.now();
        
        // Format the date as a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        lastactive = currentDate.format(formatter);
    }
    public String withdraw(Double amount)
    {
        if(this.balance >= amount)
        {
            this.balance -= amount;
            LocalDate currentDate = LocalDate.now();
        
            // Format the date as a string
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            lastactive = currentDate.format(formatter);
            return "Success";
        }
        return "Not enough balance";
       
    }
    public void transfer(Account a,Double amount)
    {
        if(this.withdraw(amount).equals("Success"))
        {
            a.deposit(amount);
        }
    }
    public boolean validate_pin(int x)
    {
        if(this.PIN == x)
        {
            return true;
        }
        return false;
    }

}
