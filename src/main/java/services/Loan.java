package services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Loan {
    int loanid;
    double Amount;
    double Interest;
    Double duration;
    float paid;

public int getLoanid() {
	return this.loanid;
}
public void setLoanid(int loanid) {
	this.loanid = loanid;
}


    public double getAmount() {
    	return this.Amount;
    }
    public void setAmount(double Amount) {
    	this.Amount = Amount;
    }


    public double getInterest() {
    	return this.Interest;
    }
    public void setInterest(double Interest) {
    	this.Interest = Interest;
    }


    public double getDuration() {
    	return this.duration;
    }
    public void setDuration(Double duration) {
    	this.duration = duration;
    }


    public float getPaid() {
    	return this.paid;
    }
    public void setPaid(float paid) {
    	this.paid = paid;
    }

    public Loan(double amount, double interest) {
        Amount = amount;
        Interest = interest;
    }
    Loan()
    {
        
        


    }
    //parameterized constructor
    public Loan(int id,double amount, double interest, Double time) {
        Amount = amount;
        loanid=id;
        Interest = interest;
        duration = time;
        paid=0;

    }

   

}
