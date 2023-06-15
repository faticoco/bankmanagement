package sda;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.BankWrapperSingleton;

public class CustProfileController {
   
    @FXML
    private TextField name_field;

    @FXML
    private TextField Phone;

    @FXML
    private Text cnic;


    @FXML
    private Button confirm_bt;

    @FXML
    private Button change_pin_bt;

    @FXML
    private Button change_pass_bt;

    @FXML
    private ImageView home_btn;
    @FXML
    private Text status;
    @FXML
    private void handleConfirmButtonAction() {
        // Code to perform when button is clicked
        //get the text from fields
        String name=name_field.getText();
        if(name==null)
        {
            status.setText("Please fill all the fields");
            return;
        }

        String phone=Phone.getText();
        if(name.isEmpty())
        {
            status.setText("Please fill all the fields");
            return;
        }

        else
        {
            BankWrapperSingleton.getInstance().getB().updatecust(name, phone);
            status.setText("Profile Updated Successfully");
        }



        // Perform actions with the retrieved data


    }

    @FXML
    private void initialize() {
        cnic.setText(BankWrapperSingleton.getInstance().getB().get_curr_cust_cnic());
        name_field.setText(BankWrapperSingleton.getInstance().getB().get_curr_cust_name());
        Phone.setText(BankWrapperSingleton.getInstance().getB().get_curr_cust_Phone());
        home_btn.setOnMouseClicked(e->
        {
            try {
                App.setRoot("Customer/customer_main_page");
            } catch (Exception ioException) {
                ioException.printStackTrace();
            }
        });
        confirm_bt.setOnAction(e->handleConfirmButtonAction());
        change_pin_bt.setOnAction(arg0 ->
        {
            try {
                App.setRoot("Customer/change_pin");
            } catch (Exception ioException) {
                ioException.printStackTrace();
            }
        });
        change_pass_bt.setOnAction(arg0 ->
        {
            try {
                App.setRoot("Customer/change_password");
            } catch (Exception ioException) {
                ioException.printStackTrace();
            }
        });
    } 
        // Code to initialize the controller goes here
}

