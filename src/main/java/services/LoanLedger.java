package services;

import java.util.ArrayList;

public class LoanLedger {
    ArrayList<Loan> L;

    public LoanLedger() {
        L = new ArrayList<Loan>();
    }
    public void add(Loan l)
{
    L.add(l);
}
    public ArrayList<Loan> getL() {
        return this.L;
    }

    public void setL(ArrayList<Loan> L) {
        this.L = L;
    }
    public void remove(Loan L)
    {
        this.L.remove(L);
    }
    

}
 

