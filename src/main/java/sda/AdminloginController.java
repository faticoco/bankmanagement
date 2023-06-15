package sda;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.BankWrapperSingleton;

public class AdminloginController {
    @FXML
    private TextField username_field;

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
        String username = username_field.getText();
        String password = pass_field.getText();
        if(BankWrapperSingleton.getInstance().getB().adminlogin(username, password))
        {
            try {
                App.setRoot("Administrator_screens/ADMINmainSCREEN");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        else
        {
            //set textfield to red
            username_field.setStyle("-fx-border-color: red");
            pass_field.setStyle("-fx-border-color: red");
            //autmatically resize text
            status.setText("Invalid username or password");
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

