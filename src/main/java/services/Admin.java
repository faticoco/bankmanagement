package services;

import java.util.ArrayList;


public class Admin {
    private String name;
    private String password;

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

    //Singelton
    private static Admin admin = null;
    private Admin(String name, String password){
        this.name = name;
        this.password = password;
    }
    public Admin() {
        this.name = "admin";
        this.password = "admin";
    }
    public static Admin getInstance(String name, String password){
        if(admin == null){
            admin = new Admin(name, password);
        }
        return admin;
    }
    public void create_employee(ArrayList<Employee> e,String name,String password,String id,String cnic)
    {
        e.add(new Employee(name,password,id,cnic));
    
    }
    public Boolean remove_Employee(ArrayList<Employee> e,String cn )
    {
        for(Employee es:e)
        {
            
            if(es.getEmployeeCnic().equals(cn))
            {
                e.remove(es);
                return true;
            }
        }
        return false;

    }
    public boolean login(String name,String password)
    {
        if(this.name.equals(name) && this.password.equals(password))
        {
            return true;
        }
        return false;

    }
    
}
