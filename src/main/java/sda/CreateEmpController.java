package sda;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.BankWrapperSingleton;


public class CreateEmpController {
    
    @FXML
    private TextField username;
    @FXML
    private TextField Cnic;
    
    @FXML
    private TextField password_field;
    
    @FXML
    private Button create_bt;
    @FXML
    private Text status;
    @FXML
    private Button home_bt;
    @FXML
    private ImageView home_btn;
    
    @FXML
    public void initialize() {
        // Add code for initializing the controller
        home_btn.setOnMouseClicked(e->{
            try {
                App.setRoot("Administrator_screens/ADMINmainSCREEN");
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        home_bt.setOnAction(e->{
            try {
                App.setRoot("Administrator_screens/ADMINmainSCREEN");
            } catch (IOException e1) {
               
                e1.printStackTrace();
            }
        });
        create_bt.setOnAction(e -> {
           try{
            if(Cnic.getText().isEmpty() || username.getText().isEmpty() || password_field.getText().isEmpty()) {
                username.setStyle("-fx-border-color: red ; -fx-background-color: #FFCDD2;");
                password_field.setStyle("-fx-border-color: red ; -fx-background-color: #FFCDD2;");
                Cnic.setStyle("-fx-border-color: red ; -fx-background-color: #FFCDD2;");

                status.setText("Please fill in all fields.");
            }
            else if (Cnic.getText().length() != 13 || !Cnic.getText().matches("\\d{13}")) {
                status.setText("Invalid CNIC format. CNIC should be a 13-digit number.");
            } 
            else if (password_field.getText().length() < 8) {
                status.setText("Password should be at least 8 characters long.");
            }
            else {
                
                if(BankWrapperSingleton.getInstance().getB().add_employee(username.getText(),password_field.getText(),Cnic.getText()))
                {
             
                    username.setStyle("-fx-border-color: green ; -fx-background-color: #C8E6C9;");
                    password_field.setStyle("-fx-border-color: green ; -fx-background-color: #C8E6C9;");
                    status.setText("Employee created successfully!");
                }
                else{
                    username.setStyle("-fx-border-color: red ; -fx-background-color: #FFCDD2;");
                    password_field.setStyle("-fx-border-color: red ; -fx-background-color: #FFCDD2;");
                    status.setText("Employee with the same cnic already exists!");
                }

            }
        }
        catch(Exception ee)
        {
            status.setText("Invalid Input");

        }

            
            // Add code for handling the create button click event
        });
    }
}
