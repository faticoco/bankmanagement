package services;

import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import persistence.Sql;

public class Dbcontroller {
    Sql s;
    private static final Dbcontroller d = new Dbcontroller();

    public static Dbcontroller get() {

        return d;

    }

    private Dbcontroller() {
        s = new Sql();
        s.setUser("root");
        s.setPass("Patakha1!");
    }

    public boolean emp_login(String cnic, String password) {
        return s.emp_login(cnic, password);
    
    }
    public Boolean cust_login(String cnic, String password)
    {
        return s.cust_login(cnic,password);

    }
    public Customer getCustomer(String cnic)
    {
        String S=s.get_cust(cnic);
        ObjectMapper m =new ObjectMapper();
        Customer e=null;
        try
        { e=m.readValue(S,Customer.class);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();


        }
        return e;

    }
    public Employee getEmployee(String cnic)
    {
        String S=s.get_emp(cnic);
        ObjectMapper m =new ObjectMapper();
        Employee e=null;
        try
        { e=m.readValue(S,Employee.class);
        }
        catch(Exception ee)
        {
            ee.printStackTrace();


        }
        return e; 
        

    }
    public void add_employee_to_db(String emp_id, String emp_name, String password_, String cnic_id, Integer withdraw,
            Integer deposit, Integer creates, Integer deletes) {
        try {
            s.add_employee_to_db(emp_id, emp_name, password_, cnic_id, withdraw, deposit, creates, deletes);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Employee> get_emp() {
        ArrayList<Employee> e = new ArrayList<>();
        String S = "";
        try {
            S = s.get_emp();
            ObjectMapper m = new ObjectMapper();
            e = m.readValue(S, m.getTypeFactory().constructCollectionType(ArrayList.class, Employee.class));
        } catch (Exception ew) {
            ew.printStackTrace();
        }
        return e;

    }

    public void remove_employee_from_db(String cnic) {
        try {
            s.remove_employee_from_db(cnic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add_transaction_to_db(String type, Double amount, String acc_id, String date) {
        try {
            s.add_transaction_to_db(type, amount, acc_id, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Transaction> get_transaction() {
        ArrayList<Transaction> e = new ArrayList<>();
        String S = "";
        try {
            S = s.get_transaction();
            ObjectMapper m = new ObjectMapper();
            e = m.readValue(S, m.getTypeFactory().constructCollectionType(ArrayList.class, Transaction.class));
        } catch (Exception ew) {
            ew.printStackTrace();
        }
        return e;
    }

    public void update_emp_pass(String cnic, String pass) {
        try {
            s.update_emp_pass(cnic, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update_emp(String cnic, String name, String phone) {
        try {
            s.update_emp(cnic, name, phone);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update_emp_status(String emp_id, boolean create, boolean delete, boolean withdraw, boolean deposit) {
        try {
            s.update_emp_permission(emp_id, create, delete, withdraw, deposit);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void add_account_to_db(String acc_id, String name, String Cnic, Double balance, int PIN, Boolean status,
            String lastactive)

    {
        try {
            s.add_account_to_db(acc_id, name, Cnic, balance, PIN, status, lastactive);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void remove_account_from_db(String acc_id) {
        try {
            s.remove_account_from_db(acc_id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Account> get_all_accounts(ArrayList<LoanRequest> l) {
        ArrayList<Account> e = new ArrayList<>();
        String S = "";
        try {
            S = s.get_all_accounts();
            ObjectMapper m = new ObjectMapper();
            e = m.readValue(S, new TypeReference<ArrayList<Account>>() {
            });
            for (Account A : e) {
                for (LoanRequest L : l) {
                    if (A.getAcc_id().equals(L.getAcc_id())) {
                        A.getLs().add(L);
                    }
                }
               
            }

        } catch (Exception ew) {
            ew.printStackTrace();
        }
        return e;
    }
    public ArrayList<Account> get_all_Accounts()
    {
        
            ArrayList<Account> e = new ArrayList<>();
            String S = "";
            try {
                S = s.get_all_accounts();
                ObjectMapper m = new ObjectMapper();
                e = m.readValue(S, new TypeReference<ArrayList<Account>>() {
                });
               
    
            } catch (Exception ew) {
                ew.printStackTrace();
            }
            return e;
    }
    
    public ArrayList<Loan> get_all_loans(ArrayList<Account> a)
    {
        ArrayList<Loan> e = new ArrayList<>();
        String S = "";
        try {
            S = s.get_all_loans();
            ObjectMapper m = new ObjectMapper();
            e = m.readValue(S, new TypeReference<ArrayList<Loan>>() {
            });

            for(Account A:a)
            {
                
                JsonNode ms=m.readTree(S);
                if(ms.isArray())
                {
                    for(JsonNode n:ms)
                    {
                       if(n.get("acc_id").asText().equals(A.getAcc_id()))
                       {
                            for(Loan ls:e)
                            {
                                if(ls.getLoanid()==(n.get("loanid").asInt()))
                                {
                                    A.setL(ls);
                                }
                            }


                       }
                    }
                }

            }
            

        } catch (Exception ew) {
            ew.printStackTrace();
        }
        return e;

    }
    public void update_balance(String acc_id, Double balance, String last_active) {
        try {
            s.update_balance(acc_id, balance, last_active);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void update_acc_status(String id,Boolean status)
    {
        try {
            s.update_acc_status(id, status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void update_acc_pin(String id,Integer pin)
    {
        try {
            s.set_acc_PIN(id, pin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void add_customer_to_db(String cnic,String acc_id,String password,String name)
    {
        try {
            s.add_cust_to_db(cnic, acc_id, password, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Map<String,Integer> get_counts()
    {
        return s.get_counts();
        
    }
    public void remove_cust(String id)
    {
        s.remove_cust(id);
    }
    public ArrayList<Customer> get_allCustomers(ArrayList<Account> a)
    {
        ArrayList<Customer> c=null;

        try
        {
            String S=s.get_all_customers();
            ObjectMapper m=new ObjectMapper();
            
            c= m.readValue(S, new TypeReference<ArrayList<Customer>>(){});
            for(Customer C:c )
            {
                for(Account A:a)
                {
                    JsonNode n=m.readTree(S);
                    if(n.isArray())
                    {
                        for(JsonNode j:n)
                        {
                            if(j.get("acc_id").asText().equals(A.getAcc_id()))
                            {
                                C.setA(A);
                            }
                        }
                    }
                }
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
        return c;
    }
    public void update_cust_pass(String cnic,String pass)
    {
        try {
            s.update_cust_pass(cnic, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void update_cust(String cnic,String name,String phone)
    {
        try {
            s.update_cust(cnic, name, phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void add_loan_req_to_db(int id,Double amount,Double repayment,Double interest,Double duration,String acc_id)
    {
        try {
            s.add_loan_req_to_db(id, amount, repayment, interest, duration,acc_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<LoanRequest> get_all_loan_requests()
    {
        try {
            String S=s.get_all_loan_requests();
            ObjectMapper m=new ObjectMapper();
            return m.readValue(S, new TypeReference<ArrayList<LoanRequest>>(){});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    public void remove_loan_req_from_db(Integer id)
    {
        try {
            s.remove_loan_req_from_db(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void add_loan_to_db(String acc_id,int id,Double amount,Double paid,Double interest,Double duration)
    {
        s.add_loan_to_db(acc_id, id, amount, paid, interest, duration);
    }
}