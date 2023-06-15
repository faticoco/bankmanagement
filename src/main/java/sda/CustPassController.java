package sda;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.BankWrapperSingleton;

public class CustPassController {
 
    @FXML
    private Button update_bt;

    @FXML
    private TextField old;

    @FXML
    private TextField news;

    @FXML
    private TextField confirm;

    @FXML
    private ImageView home_btn;
    @FXML
    private Text status;

    @FXML
    private void handleUpdateButtonAction() {

            if (BankWrapperSingleton.getInstance().getB().pass(old.getText())) {
                if (news.getText().equals(confirm.getText())) {
                    if(news.getText().length()>=8)
                    {
                        BankWrapperSingleton.getInstance().getB().updatecustpass(news.getText());
                         status.setText("Password Updated");
                    }
                    else
                    {
                        status.setText("Password must be at least 8 characters long");
                    }
                } else {
                    status.setText("Passwords do not match");
                }
            } else {
                status.setText("Incorrect Password");
            }
    
    
            
        

        // Perform actions with the retrieved data

    }

    @FXML
    private void initialize() {
        // Code to initialize the controller goes here
        home_btn.setOnMouseClicked(arg0 -> 
        {
            try {
                App.setRoot("Customer/customer_main_page");
            } catch (Exception e) {
                e.printStackTrace();}
        });
        update_bt.setOnMouseClicked(e-> handleUpdateButtonAction());
    }
}
