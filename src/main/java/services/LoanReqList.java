package services;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LoanReqList {
    private ArrayList<LoanRequest> L;




    public LoanReqList() {
        L = new ArrayList<LoanRequest>();
    }

    public ArrayList<LoanRequest> getL() {
        return L;
    }

    public void setL(ArrayList<LoanRequest> l) {
        L = l;
    }
    public String toJson()
    {
        //use object mapper
        ObjectMapper o=new ObjectMapper();
        String s="";
        try {
            s=o.writeValueAsString(this.L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;

    }
    public void add(LoanRequest l)
    {
        L.add(l);
    }
    public void remove(LoanRequest l)
    {
        L.remove(l);
    }
    
    
}
