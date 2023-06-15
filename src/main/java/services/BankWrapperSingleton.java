package services;

public class BankWrapperSingleton {
    private Bank b;
    
    private static BankWrapperSingleton bs;
    private BankWrapperSingleton() {
        b = new Bank();
    }
    public static BankWrapperSingleton getInstance() {
        if (bs == null) {
            bs = new BankWrapperSingleton();
        }
        return bs;
    }
    
    public Bank getB() {
        return this.b;
    }
    public void setB(Bank b) {
        this.b = b;
    }
    
}
