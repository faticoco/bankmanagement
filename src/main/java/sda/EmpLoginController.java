package sda;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.BankWrapperSingleton;

public class EmpLoginController {
    @FXML
    private TextField cnic_field;

    @FXML
    private TextField pass_field;

    @FXML
    private Button login_bt;

    @FXML
    private Text status;

    @FXML
    private ImageView home_btn;

    @FXML
    private void handleLoginButtonAction() {
        String cnic = cnic_field.getText();
        String password = pass_field.getText();
        if(BankWrapperSingleton.getInstance().getB().emplogin(cnic, password))
        {
            try {
                App.setRoot("employee/employeemain");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        else
        {
            //set textfield to red
            cnic_field.setStyle("-fx-border-color: red");
            pass_field.setStyle("-fx-border-color: red");
            //autmatically resize text
            status.setText("Invalid cnic or password");
            status.autosize();
        }

        // Perform login authentication and logic here
        

        // Set status text based on login result
       
    }
    @FXML
    public void initialize()
    {
        home_btn.setOnMouseClicked(e->{
            try {
                App.setRoot("mainpage");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        login_bt.setOnAction(e->handleLoginButtonAction());

    }
    
}
