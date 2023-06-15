package sda;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
public class MainpageController {
    
    @FXML
    private Button cust_bt;

    @FXML
    private Button emp_bt;

    @FXML
    private Button adm_bt;

    @FXML
    private void initialize() {
        // Add initialization code if needed
        cust_bt.setOnAction(e->handleCustomerButton());
        emp_bt.setOnAction(e->handleEmployeeButton());
        adm_bt.setOnAction(e->handleAdministratorButton());
    }

    @FXML
    private void handleCustomerButton() {
        // Handle the customer button click event
       try 
       {
              App.setRoot("Customer/cust_login");
       }
         catch(Exception e)
            {
                e.printStackTrace();
            }
        // Add your logic here for handling the customer button click
    }

    @FXML
    private void handleEmployeeButton() {
        // Handle the employee button click event
        try{
            App.setRoot("Employee/emp_login");
        }
        catch(Exception e){
            e.printStackTrace();

        }
        // Add your logic here for handling the employee button click
    }

    @FXML
    private void handleAdministratorButton() {
        // Handle the administrator button click event
        System.out.println("Administrator button clicked");
        try
        {
            App.setRoot("Administrator_screens/admin_login");

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        // Add your logic here for handling the administratort button click
    }
    
}
