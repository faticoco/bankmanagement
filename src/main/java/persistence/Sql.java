package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Sql {
    private String user;
    private String pass;


   public String getUser() {
   	return this.user;
   }
   public void setUser(String user) {
   	this.user = user;
   }


    public String getPass() {
    	return this.pass;
    }
    public void setPass(String pass) {
    	this.pass = pass;
    }

    public void add_employee_to_db(String emp_id, String emp_name, String password_, String cnic_id, Integer withdraw,
            Integer deposit, Integer creates, Integer deletes) {

        Connection myconn = null;
        PreparedStatement mystmt = null;
        ResultSet myRs = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user, pass);

            String query = "insert into employee (emp_id , emp_name , password_ ,cnic_id, withdraw , deposit , creates , deletes) values( ? , ? ,  ?,? , ? , ? ,  ? , ?); ";

            mystmt = myconn.prepareStatement(query);

            // Set the values of the variables in the statement
            mystmt.setString(1, emp_id);
            mystmt.setString(2, emp_name);
            mystmt.setString(3, password_);
            mystmt.setString(4, cnic_id);
            mystmt.setInt(5, withdraw);
            mystmt.setInt(6, deposit);
            mystmt.setInt(7, creates);
            mystmt.setInt(8, deletes);

            // Execute the statement
            mystmt.executeUpdate();
            query="Update counts Set emp_count=emp_count+1";
            mystmt = myconn.prepareStatement(query);
            mystmt.executeUpdate();
            // while (myRs.next())
            // {
            // System.out.println(myRs.getString("ID"));

            // }
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myRs != null) {
                try {
                    myRs.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }
    public void update_emp_permission(String emp_id,boolean create,boolean delete,boolean withdraw,boolean deposit)
    {

        Connection myconn = null;
        PreparedStatement mystmt = null;
        ResultSet myRs = null;

        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user, pass);

            String query = "update employee set withdraw = ? , deposit = ? , creates = ? , deletes = ? where emp_id = ?; ";

            mystmt = myconn.prepareStatement(query);

            // Set the values of the variables in the statement
            mystmt.setBoolean(1, withdraw);
            mystmt.setBoolean(2, deposit);
            mystmt.setBoolean(3, create);
            mystmt.setBoolean(4, delete);
            mystmt.setString(5, emp_id);

            // Execute the statement
            mystmt.executeUpdate();
            // while (myRs.next())
            // {
            // System.out.println(myRs.getString("ID"));

            // }
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myRs != null) {
                try {
                    myRs.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    public void remove_employee_from_db(String cnic) {  

        Connection myconn = null;

        ResultSet myRs = null;
    
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user, pass);

            PreparedStatement stmt = myconn.prepareStatement("DELETE FROM employee WHERE cnic_id = ?");
            stmt.setString(1, cnic);
            stmt.executeUpdate();

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myRs != null) {
                try {
                    myRs.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    public String get_emp()
    {

        Connection myconn = null;

        ResultSet myRs = null;
     
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user, pass);

            PreparedStatement stmt = myconn.prepareStatement("SELECT * FROM employee");
            myRs=stmt.executeQuery();
            ArrayList<Emp_modal> e=new ArrayList<Emp_modal>();
            while(myRs.next())
            {
                Emp_modal emp=new Emp_modal();
                emp.setEmployeeName(myRs.getString("emp_name"));
                emp.setEmployeeId(myRs.getString("emp_id"));
                emp.setEmployeePassword(myRs.getString("password_"));
                emp.setEmployeeCnic(myRs.getString("cnic_id"));
                emp.setWithdraw(myRs.getBoolean("withdraw"));
                emp.setDeposit(myRs.getBoolean("deposit"));
                emp.setCreate(myRs.getBoolean("creates"));
                emp.setDelete(myRs.getBoolean("deletes"));
                e.add(emp);
                
            }
            ObjectMapper m= new ObjectMapper();
            String json=m.writeValueAsString(e);
            return json;
            

        }
        catch(Exception e)
        {
            e.printStackTrace();

        }
        return "error";

    }
    public Boolean emp_login(String cnic,String password)
    {
        
        Connection myconn = null;

        ResultSet myRs = null;
     
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user, pass);

            PreparedStatement stmt = myconn.prepareStatement("SELECT * FROM employee where cnic_id= ? and password_=?");
            stmt.setString(1,cnic);
            stmt.setString(2,password);
            myRs=stmt.executeQuery();
            if(myRs.next())
            {
                return true;
            }


        }
        catch(Exception e)
        {

        }
        return false;

    }
    public Boolean cust_login(String cnic,String password)
    {
        
        Connection myconn = null;

        ResultSet myRs = null;
     
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user, pass);

            PreparedStatement stmt = myconn.prepareStatement("SELECT * FROM Customer where cnic= ? and password=?");
            stmt.setString(1,cnic);
            stmt.setString(2,password);
            myRs=stmt.executeQuery();
            if(myRs.next())
            {
                return true;
            }


        }
        catch(Exception e)
        {

        }
        return false;

    }
    public String get_emp(String cnic)
    {

        Connection myconn = null;

        ResultSet myRs = null;
     
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user, pass);

            PreparedStatement stmt = myconn.prepareStatement("SELECT * FROM employee");
            myRs=stmt.executeQuery();
            Emp_modal emp=new Emp_modal();
            while(myRs.next())
            {
                
                emp.setEmployeeName(myRs.getString("emp_name"));
                emp.setEmployeeId(myRs.getString("emp_id"));
                emp.setEmployeePassword(myRs.getString("password_"));
                emp.setEmployeeCnic(myRs.getString("cnic_id"));
                emp.setWithdraw(myRs.getBoolean("withdraw"));
                emp.setDeposit(myRs.getBoolean("deposit"));
                emp.setCreate(myRs.getBoolean("creates"));
                emp.setDelete(myRs.getBoolean("deletes"));
                
                
            }
            ObjectMapper m= new ObjectMapper();
            String json=m.writeValueAsString(emp);
            return json;
            

        }
        catch(Exception e)
        {
            e.printStackTrace();

        }
        return "error";

    }

    public void add_transaction_to_db(String type,Double amount,String acc_id,String date)
    {
        Connection myconn = null;
        PreparedStatement mystmt = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user, pass);

            String query = "insert into transaction (type_ , amount , acc_id ,dates) values( ? , ? ,  ?,? ); ";

            mystmt = myconn.prepareStatement(query);
            //convert date string to mysql date
            LocalDate l=LocalDate.parse(date);
            java.sql.Date sqlDate = java.sql.Date.valueOf(l);
            // DateFormat formatter = new SimpleDateFormat("yyyy/mm/dd");
            // java.util.Date myDate = formatter.parse(date);
            // java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());




            // Set the values of the variables in the statement
            mystmt.setString(1, type);
            mystmt.setDouble(2, amount);
            mystmt.setString(3, acc_id);
            mystmt.setDate(4,sqlDate);
            // Execute the statement
            mystmt.executeUpdate();
            // while (myRs.next())
            // {
            // System.out.println(myRs.getString("ID"));

            // }
        } catch (Exception exc) {
            exc.printStackTrace();
        } 

    }
    public String get_transaction()
    {

        Connection myconn = null;
        PreparedStatement mystmt = null;
        ResultSet myRs = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda",user, pass);
            String query = "select * from transaction";
            mystmt = myconn.prepareStatement(query);
            myRs = mystmt.executeQuery();
            ArrayList<Transaction_Modal> t = new ArrayList<Transaction_Modal>();
            while (myRs.next()) {
                Transaction_Modal t_m = new Transaction_Modal();
                //t_m.setTransactionId(myRs.getInt("transaction_id"));
                t_m.setType(myRs.getString("type_"));
                t_m.setAmount(myRs.getDouble("amount"));
                t_m.setAcc_id(myRs.getString("acc_id"));
                t_m.setDate((myRs.getDate("dates")).toLocalDate().toString());
                t.add(t_m);
            }
            ObjectMapper m = new ObjectMapper();
            String json = m.writeValueAsString(t);
            return json;
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            return "error";




    }
    public void update_emp_pass(String cnic,String pass)
    {
        Connection myconn = null;
        PreparedStatement mystmt = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user, pass);

            String query = "update employee set password_ = ? where cnic_id = ?";

            mystmt = myconn.prepareStatement(query);
            // Set the values of the variables in the statement
            mystmt.setString(1, pass);
            mystmt.setString(2, cnic);
            // Execute the statement
            mystmt.executeUpdate();
            // while (myRs.next())
            // {
            // System.out.println(myRs.getString("ID"));

            // }
        } catch (Exception exc) {
            exc.printStackTrace();
        } 
    }
    public void update_emp(String cnic,String name,String phone)
    {
        Connection myconn = null;
        PreparedStatement mystmt = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user, pass);
            String query = "update employee set emp_name = ?, phone = ? where cnic_id = ?";


            

            mystmt = myconn.prepareStatement(query);
            // Set the values of the variables in the statement
            mystmt.setString(1, name);
            mystmt.setString(3, cnic);
            mystmt.setString(2, phone);
            // Execute the statement
            mystmt.executeUpdate();
            // while (myRs.next())
            // {
            // System.out.println(myRs.getString("ID"));

            // }
        } catch (Exception exc) {
            exc.printStackTrace();
        } 
    }
    public void add_account_to_db(String acc_id,String name,String Cnic,Double balance,int PIN,Boolean status,String lastactive)
    {
        Connection myconn = null;
        PreparedStatement mystmt = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user,pass);

            String query = "insert into accounts (acc_id , name , cnic , balance , pin , status , lastactive) values( ? , ? ,  ?,? , ? , ? , ? ); ";

            mystmt = myconn.prepareStatement(query);
            //convert date string to mysql date
            LocalDate l=LocalDate.parse(lastactive);
            java.sql.Date sqlDate = java.sql.Date.valueOf(l);
            // Set the values of the variables in the statement
            mystmt.setString(1, acc_id);
            mystmt.setString(2, name);
            mystmt.setString(3, Cnic);
            mystmt.setDouble(4, balance);
            mystmt.setInt(5, PIN);
            mystmt.setBoolean(6, status);
            mystmt.setDate(7,sqlDate);
            // Execute the statement
            mystmt.executeUpdate();
            query="update counts Set acc_count=acc_count+1 ";
            mystmt = myconn.prepareStatement(query);
            mystmt.executeUpdate();

            // while (myRs.next()

            // DateFormat formatter = new SimpleDateFormat("yyyy/mm/dd");
            // java.util.Date myDate = formatter.parse(date);
            // java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
    public void remove_account_from_db(String acc_id)
    {
        Connection myconn = null;
        PreparedStatement mystmt = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user,pass);

            String query = "delete from accounts where acc_id = ?";

            mystmt = myconn.prepareStatement(query);
            // Set the values of the variables in the statement
            mystmt.setString(1, acc_id);
            // Execute the statement
            mystmt.executeUpdate();
            // while (myRs.next())
            // {
            // System.out.println(myRs.getString("ID"));

            // }
        } catch (Exception exc) {
            exc.printStackTrace();
        } 
    }
    public String get_all_accounts()
    {
        Connection myconn = null;
        PreparedStatement mystmt = null;
        ResultSet myRs = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user,pass);
            String query = "select * from accounts";
            mystmt = myconn.prepareStatement(query);
            myRs = mystmt.executeQuery();
            ArrayList<Account_modal> a = new ArrayList<Account_modal>();
            while (myRs.next()) {
                Account_modal a_m = new Account_modal();
                a_m.setAcc_id(myRs.getString("acc_id"));
                a_m.setName(myRs.getString("name"));
                a_m.setCnic(myRs.getString("cnic"));
                a_m.setBalance(myRs.getDouble("balance"));
                a_m.setPIN(myRs.getInt("pin"));
                a_m.setStatus(myRs.getBoolean("status"));
                a_m.setLastactive((myRs.getDate("lastactive")).toLocalDate().toString());
                a.add(a_m);
            }
            ObjectMapper m = new ObjectMapper();
            String json = m.writeValueAsString(a);
            return json;
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            return "error";

    }
    public void update_balance(String acc_id,Double amount,String last_active)

    {

        Connection myconn = null;
        PreparedStatement mystmt = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user,pass);
            String query = "update accounts set balance = balance + ? , lastactive = ? where acc_id = ?";

            mystmt = myconn.prepareStatement(query);
            //convert date string to mysql date
            LocalDate l=LocalDate.parse(last_active);
            java.sql.Date sqlDate = java.sql.Date.valueOf(l);
            // Set the values of the variables in the statement
            mystmt.setDouble(1, amount);
            mystmt.setDate(2,sqlDate);
            mystmt.setString(3, acc_id);
            // Execute the statement
            mystmt.executeUpdate();
            // while (myRs.next())
            // {
            // System.out.println(myRs.getString("ID"));

            // }
        } catch (Exception exc) {
            exc.printStackTrace();
        } 

    }
    public void update_acc_status(String acc_id,Boolean status)
    {
        Connection myconn = null;
        PreparedStatement mystmt = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user,pass);
            String query = "update accounts set status = ? where acc_id = ?";

            mystmt = myconn.prepareStatement(query);
            // Set the values of the variables in the statement
            mystmt.setBoolean(1, status);
            mystmt.setString(2, acc_id);
            // Execute the statement
            mystmt.executeUpdate();
            // while (myRs.next())
            // {
            // System.out.println(myRs.getString("ID"));

            // }
        } catch (Exception exc) {
            exc.printStackTrace();
        } 
    }    
    public void set_acc_PIN(String id,int PIN)
    {
        Connection myconn = null;
        PreparedStatement mystmt = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user,pass);
            String query = "update accounts set pin = ? where acc_id = ?";

            mystmt = myconn.prepareStatement(query);
            // Set the values of the variables in the statement
            mystmt.setInt(1, PIN);
            mystmt.setString(2, id);
            // Execute the statement
            mystmt.executeUpdate();
            // while (myRs.next())
            // {
            // System.out.println(myRs.getString("ID"));

            // }
        } catch (Exception exc) {
            exc.printStackTrace();
        } 
    }
    public void add_cust_to_db(String cnic,String acc_id,String password,String name)
    {
        Connection myconn = null;
        PreparedStatement mystmt = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user,pass);

            String query = "insert into customer (cnic,acc_id,password,name) values (?,?,?,?)";

            mystmt = myconn.prepareStatement(query);
            // Set the values of the variables in the statement
            mystmt.setString(1, cnic);
            mystmt.setString(2, acc_id);
            mystmt.setString(3, password);
            mystmt.setString(4, name);
            // Execute the statement
            mystmt.executeUpdate();
            // while (myRs.next())
            // {
            // System.out.println(myRs.getString("ID"));

            // }
        } catch (Exception exc) {
            exc.printStackTrace();
        } 
    }
    public void remove_cust(String acc_id)
    {
        Connection myconn = null;
        PreparedStatement mystmt = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user,pass);

            String query = "delete from  Customer where acc_id = ?";

            mystmt = myconn.prepareStatement(query);
            // Set the values of the variables in the statement
            mystmt.setString(1, acc_id);
            // Execute the statement
            mystmt.executeUpdate();
            // while (myRs.next())
            // {
            // System.out.println(myRs.getString("ID"));

            // }
        } catch (Exception exc) {
            exc.printStackTrace();
        } 
    }
    public Map<String,Integer> get_counts()
    
    {
        Map<String,Integer> m=new HashMap<String,Integer>();
        Connection myconn = null;
        PreparedStatement mystmt = null;
        ResultSet myRs = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user,pass);
            String query="Select * from counts";
            mystmt = myconn.prepareStatement(query);
            myRs = mystmt.executeQuery();
            while (myRs.next()) {
                m.put("emp",myRs.getInt("emp_count"));
                m.put("acc",myRs.getInt("acc_count"));
                m.put("loan",myRs.getInt("loan_count"));
                m.put("lonr_req",myRs.getInt("loanr_count"));
            }


           
        } catch (Exception exc) {
            exc.printStackTrace();
        } 
        return m;
    }
    public String get_cust(String cnic)
    {
        Customer_modal cm=new Customer_modal();
        Connection myconn = null;
        String json="";
        PreparedStatement mystmt = null;
        ResultSet myRs = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user,pass);
            String query="Select * from customer";
            mystmt = myconn.prepareStatement(query);
            myRs = mystmt.executeQuery();
            while (myRs.next()) {
                
                cm.setAcc_id(myRs.getString("acc_id"));
                cm.setCnic(myRs.getString("cnic"));
                cm.setName(myRs.getString("name"));
                cm.setPassword(myRs.getString("password"));
            }
            ObjectMapper m= new ObjectMapper();
            json=m.writeValueAsString(cm);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return json;
    }
    public String get_all_customers()
    {
        ArrayList<Customer_modal> c=new ArrayList<>();
        Connection myconn = null;
        String json="";
        PreparedStatement mystmt = null;
        ResultSet myRs = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user,pass);
            String query="Select * from customer";
            mystmt = myconn.prepareStatement(query);
            myRs = mystmt.executeQuery();
            while (myRs.next()) {
                Customer_modal cm=new Customer_modal();
                cm.setAcc_id(myRs.getString("acc_id"));
                cm.setCnic(myRs.getString("cnic"));
                cm.setName(myRs.getString("name"));
                cm.setPassword(myRs.getString("password"));
                c.add(cm);
                
            }
            ObjectMapper m= new ObjectMapper();
            json=m.writeValueAsString(c);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return json;
    }
    public void update_cust_pass(String cnic,String password)
    {
        Connection myconn = null;
        PreparedStatement mystmt = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user,pass);

            String query = "update customer set password = ? where cnic = ?";

            mystmt = myconn.prepareStatement(query);
            // Set the values of the variables in the statement
            mystmt.setString(1, password);
            mystmt.setString(2, cnic);
            // Execute the statement
            mystmt.executeUpdate();
            // while (myRs.next())
            // {
            // System.out.println(myRs.getString("ID"));

            // }
        } catch (Exception exc) {
            exc.printStackTrace();
        } 

    }
    public void update_cust(String cnic,String name,String phone)
    {
        Connection myconn = null;
        PreparedStatement mystmt = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user,pass);

            String query = "update customer set name = ?,phone = ? where cnic = ?";

            mystmt = myconn.prepareStatement(query);
            // Set the values of the variables in the statement
            mystmt.setString(1, name);
            mystmt.setString(2, phone);
            mystmt.setString(3, cnic);
            // Execute the statement
            mystmt.executeUpdate();
            query="update accounts set name=? where cnic=?";
            mystmt = myconn.prepareStatement(query);
            mystmt.setString(1, name);
            mystmt.setString(2, cnic);
            mystmt.executeUpdate();

            // while (myRs.next())
            // {
            // System.out.println(myRs.getString("ID"));

            // }
        } catch (Exception exc) {
            exc.printStackTrace();
        } 

    }
    public void add_loan_req_to_db(int id,Double amount,Double repayment,Double interest,Double duration,String acc_id)
    {
        Connection myconn = null;
        PreparedStatement mystmt = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user,pass);

            String query = "insert into Loanreq (id,amount,repay,interest,duration,acc_id) values (?,?,?,?,?,?)";

            mystmt = myconn.prepareStatement(query);
            // Set the values of the variables in the statement
            mystmt.setInt(1, id);
            mystmt.setDouble(2, amount);
            mystmt.setDouble(3, repayment);
            mystmt.setDouble(4, interest);
            mystmt.setDouble(5, duration);
            mystmt.setString(6, acc_id);
            // Execute the statement
            mystmt.executeUpdate();
            query="Update counts set loanr_count=loanr_count+1";
            mystmt = myconn.prepareStatement(query);
            mystmt.executeUpdate();
            // while (myRs.next())
            // {
            // System.out.println(myRs.getString("ID"));

            // }
        } catch (Exception exc) {
            exc.printStackTrace();
        } 
    }
    public void add_loan_to_db(String acc_id,int id,Double amount,Double paid,Double interest,Double duration)
    {
        Connection myconn = null;
        PreparedStatement mystmt = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user,pass);

            String query = "insert into loan (id,amount,paid,interest,duration,acc_id) values (?,?,?,?,?,?)";

            mystmt = myconn.prepareStatement(query);
            // Set the values of the variables in the statement
            mystmt.setInt(1, id);
            mystmt.setDouble(2, amount);
            mystmt.setDouble(3, paid);
            mystmt.setDouble(4, interest);
            mystmt.setDouble(5, duration);
            mystmt.setString(6, acc_id);
            // Execute the statement
            mystmt.executeUpdate();
            query="Update counts set loan_count=loan_count+1";
            // while (myRs.next())
            // {
            // System.out.println(myRs.getString("ID"));

            // }
        } catch (Exception exc) {
            exc.printStackTrace();
        } 
    }
    public String get_all_loan_requests()
    {
        ArrayList<LoanRequest_Modal> lr=new ArrayList<>();
        String json=null;
        Connection myconn = null;
        PreparedStatement mystmt = null;
        ResultSet myRs = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user,pass);
            String query="Select * from loanreq";
            mystmt = myconn.prepareStatement(query);
            myRs = mystmt.executeQuery();
            while (myRs.next()) {
                

                LoanRequest_Modal cm=new LoanRequest_Modal();
                cm.setAcc_id(myRs.getString("acc_id"));
                cm.setReqid(myRs.getInt("id"));
                cm.setAmount(myRs.getDouble("amount"));
                cm.setRepayment((myRs.getDouble("repay"))+"");
                cm.setInterestRate(myRs.getDouble("interest"));
                cm.setDuration(myRs.getDouble("duration"));
                lr.add(cm);
                
            }
            ObjectMapper m= new ObjectMapper();
             json=m.writeValueAsString(lr);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
         return json;
    }
    public String get_all_loans()
    {
        ArrayList<Loan_Modal> lr=new ArrayList<>();
        String json=null;
        Connection myconn = null;
        PreparedStatement mystmt = null;
        ResultSet myRs = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user,pass);
            String query="Select * from loan";
            mystmt = myconn.prepareStatement(query);
            myRs = mystmt.executeQuery();
            while (myRs.next()) {
                

                Loan_Modal cm=new Loan_Modal();
                cm.setLoanid(myRs.getInt("id"));
                cm.setAmount(myRs.getDouble("amount"));
                cm.setPaid(myRs.getDouble("paid"));
                cm.setInterest(myRs.getDouble("interest"));
                cm.setDuration(myRs.getDouble("duration"));
                cm.setAcc_id(myRs.getString("acc_id"));
                lr.add(cm);
                
            }
            ObjectMapper m= new ObjectMapper();
             json=m.writeValueAsString(lr);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
         return json;

    }
    public void remove_loan_req_from_db(Integer id)
    {
        Connection myconn = null;
        PreparedStatement mystmt = null;
        try {
            myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sda", user,pass);

            String query = "delete from loanreq where id=?";

            mystmt = myconn.prepareStatement(query);
            // Set the values of the variables in the statement
            mystmt.setInt(1, id);
            // Execute the statement
            mystmt.executeUpdate();
            query="Update counts set loanr_count=loanr_count-1";
            mystmt = myconn.prepareStatement(query);
            mystmt.executeUpdate();
            // while (myRs.next())
            // {
            // System.out.println(myRs.getString("ID"));

            // }
        } catch (Exception exc) {
            exc.printStackTrace();
        } 

    }
    public void setpass(String string) {
    }
    public void setuser(String string) {
    }
}


class Emp_modal
{
  
    public Emp_modal()
    {

    }
    private String employeeName;
    private String employeeId;
    private String employeePassword;
    private String employeePhone;
    private String employeeCnic;
    private Boolean withdraw;
    private Boolean deposit;
    private Boolean create;
    private Boolean delete;

    public String getEmployeeName() {
    	return this.employeeName;
    }
    public void setEmployeeName(String employeeName) {
    	this.employeeName = employeeName;
    }


    public String getEmployeeId() {
    	return this.employeeId;
    }
    public void setEmployeeId(String employeeId) {
    	this.employeeId = employeeId;
    }


    public String getEmployeePassword() {
    	return this.employeePassword;
    }
    public void setEmployeePassword(String employeePassword) {
    	this.employeePassword = employeePassword;
    }


    public String getEmployeePhone() {
    	return this.employeePhone;
    }
    public void setEmployeePhone(String employeePhone) {
    	this.employeePhone = employeePhone;
    }


    public String getEmployeeCnic() {
    	return this.employeeCnic;
    }
    public void setEmployeeCnic(String employeeCnic) {
    	this.employeeCnic = employeeCnic;
    }


    public Boolean getWithdraw() {
    	return this.withdraw;
    }
    public void setWithdraw(Boolean withdraw) {
    	this.withdraw = withdraw;
    }


    public Boolean getDeposit() {
    	return this.deposit;
    }
    public void setDeposit(Boolean deposit) {
    	this.deposit = deposit;
    }


    public Boolean getCreate() {
    	return this.create;
    }
    public void setCreate(Boolean create) {
    	this.create = create;
    }


    public Boolean getDelete() {
    	return this.delete;
    }
    public void setDelete(Boolean delete) {
    	this.delete = delete;
    }




}
class Transaction_Modal
{
    private String type;
    private Double amount;
    private String acc_id;
    private String date;

    public Transaction_Modal()
    {

    }

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


}
class LoanRequest_Modal
{
    private int reqid; 
    private Double Amount;
    private String acc_id;
    private Double duration;
    private Double InterestRate;
    private String repayment;

public int getReqid() {
	return this.reqid;
}
public void setReqid(int reqid) {
	this.reqid = reqid;
}


    public Double getAmount() {
    	return this.Amount;
    }
    public void setAmount(Double Amount) {
    	this.Amount = Amount;
    }


    public String getAcc_id() {
    	return this.acc_id;
    }
    public void setAcc_id(String acc_id) {
    	this.acc_id = acc_id;
    }


    public Double getDuration() {
    	return this.duration;
    }
    public void setDuration(double d) {
    	this.duration = d;
    }


    public Double getInterestRate() {
    	return this.InterestRate;
    }
    public void setInterestRate(Double InterestRate) {
    	this.InterestRate = InterestRate;
    }


    public String getRepayment() {
    	return this.repayment;
    }
    public void setRepayment(String repayment) {
    	this.repayment = repayment;
    }

    public LoanRequest_Modal()
    {

    }


}

class Loan_Modal
{
    int loanid;
    double Amount;
    double Interest;
    Double duration;
    double paid;
    String acc_id;

public String getAcc_id() {
	return this.acc_id;
}
public void setAcc_id(String acc_id) {
	this.acc_id = acc_id;
}


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


    public Double getDuration() {
    	return this.duration;
    }
    public void setDuration(Double duration) {
    	this.duration = duration;
    }


    public double getPaid() {
    	return this.paid;
    }
    public void setPaid(double paid) {
    	this.paid = paid;
    }



    public Loan_Modal()
    {

    }

 
}

class Account_modal
{
    private String acc_id;
    private String name;
    private String cnic;
    private Double balance;
    private int PIN;
    private int LL;
    private ArrayList<Integer> Lss;

  public int getLL() {
  	return this.LL;
  }
  public void setLL(int LL) {
  	this.LL = LL;
  }


    public ArrayList<Integer> getLss() {
    	return this.Lss;
    }
    public void setLss(ArrayList<Integer> Ls) {
    	this.Lss = Ls;
    }
 
    private Boolean status;
    private String lastactive;

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


    public String getCnic() {
    	return this.cnic;
    }
    public void setCnic(String cnic) {
    	this.cnic = cnic;
    }


    public Double getBalance() {
    	return this.balance;
    }
    public void setBalance(Double balance) {
    	this.balance = balance;
    }


    public int getPIN() {
    	return this.PIN;
    }
    public void setPIN(int PIN) {
    	this.PIN = PIN;
    }


   


    public Boolean getStatus() {
    	return this.status;
    }
    public void setStatus(Boolean status) {
    	this.status = status;
    }


    public String getLastactive() {
    	return this.lastactive;
    }
    public void setLastactive(String lastactive) {
    	this.lastactive = lastactive;
    }

    public Account_modal()
    {

    }


}
@JsonIgnoreProperties
class Customer_modal
{
    private String name;
    private String password;
    private String phone;
    private String cnic;
    private String acc_id;

public String getAcc_id() {
	return this.acc_id;
}
public void setAcc_id(String acc_id) {
	this.acc_id = acc_id;
}







    public Customer_modal()
    {

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


  
    
  public String getName() {
  	return this.name;
  }
  public void setName(String name) {
  	this.name = name;
  }

}