package sda;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.BankWrapperSingleton;

public class CustLoginController {
    @FXML
    private TextField username_field;

    @FXML
    private TextField pass_field;

    @FXML
    private TextField cnic_field;

    @FXML
    private TextField id_field;

    @FXML
    private TextField pass_field1;

    @FXML
    private Button login_bt;

    @FXML
    private Button signup_bt;

    @FXML
    private Text status;

    @FXML
    private ImageView home_btn;

    @FXML
    private void handleLoginButtonAction() {
        String username = username_field.getText();
        String password = pass_field.getText();
        if(BankWrapperSingleton.getInstance().getB().custlogin(username, password))
        {
            try {
                App.setRoot("Customer/customer_main_page");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            status.setText("Invalid Credentials");
        }

        // Perform login authentication and logic here

        // Set status text based on login result

    }

    @FXML
    private void handleSignupButtonAction() {
        String cnic = cnic_field.getText();
        String id = id_field.getText();
        String password = pass_field1.getText();
        if(password.length()<8)
        {
            status.setText("Password must be at least 8 characters long");
            return;
        }
        String result=BankWrapperSingleton.getInstance().getB().Custsignup(cnic, id, password);
        if(result.equals("Success"))
        {
            status.setText("Signup Successful");
        }
        else{
            status.setText(result);
        }

        // Perform signup logic here

        // Set status text based on signup result
       
    }
    @FXML
    public void initialize() {
        home_btn.setOnMouseClicked(arg0 ->
        {
            try {
                App.setRoot("mainpage");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } );
        login_bt.setOnAction(e->handleLoginButtonAction());
        signup_bt.setOnAction(e->handleSignupButtonAction());
    }
}
