package services;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TransactionLedger {
    
    ArrayList<Transaction> T;

public ArrayList<Transaction> getT() {
	return this.T;
}
public void setT(ArrayList<Transaction> T) {
	this.T = T;
}

    public TransactionLedger()
    {
        T = new ArrayList<Transaction>();
    }
    public void addTransaction(Transaction t)
    {
        T.add(t);
    }
    public ArrayList<Transaction> getTransactions(String acc_id)
    {
        ArrayList<Transaction> temp=new ArrayList<Transaction>();
        for(Transaction t: T)
        {
            if(t.getAcc_id().equals(acc_id))
            {
                temp.add(t);
            }
        }
        return temp;
    }
    public String toJson(String acc_id)
    {
        ArrayList<Transaction> temp=new ArrayList<Transaction>();
        for(Transaction t: T)
        {
            if(t.getAcc_id().equals(acc_id))
            {
                temp.add(t);
            }
        }
        ObjectMapper m=new ObjectMapper();
        String s="";
        try {
            s=m.writeValueAsString(temp);
        } catch (Exception e) {
            e.printStackTrace();
            }
        return s;
        
    }
}
