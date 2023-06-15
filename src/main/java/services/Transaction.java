package services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
    private String type;
    private Double amount;
    private String acc_id;
    private String date;


    public String getType() {
    	return this.type;
    }
    public void setType(String type) {
    	this.type = type;
    }


    public Double getAmount() {
    	return this.amount;
    }
    public void setAmount(Double amount) {
    	this.amount = amount;
    }


    public String getAcc_id() {
    	return this.acc_id;
    }
    public void setAcc_id(String acc_id) {
    	this.acc_id = acc_id;
    }


    public String getDate() {
    	return this.date;
    }
    public void setDate(String date) {
    	this.date = date;
    }



    public Transaction()
    {
        this.type = "0";
        this.amount = 0.0;
        this.acc_id = "0";
    
    }
    public Transaction(String type,Double amount,String acc_id,String date)
    {
        this.type = type;
        this.amount = amount;
        this.acc_id = acc_id;
        this.date=date;
       
    }

    
}
