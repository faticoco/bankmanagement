package sda;


    import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.BankWrapperSingleton;

public class DeleteAccountController {

    @FXML
    private TextField field_id;

    @FXML
    private Button del_bt;

    @FXML
    private ImageView home_btn;
    @FXML
    private Text status;
    @FXML
    void initialize() {

        home_btn.setOnMouseClicked(arg0 -> 
        {
            try {
                App.setRoot("employee/employeemain");
            } catch (Exception e) {
            }
        } );
        del_bt.setOnAction(arg0 -> 
        {
            if(field_id.getText().isEmpty())
            {
                status.setText("Please enter ID");
                return;

            }
            try {
                if(BankWrapperSingleton.getInstance().getB().remove_account(field_id.getText()))
                {
                    status.setText("Account Deleted");
                }
                else{
                    status.setText("Account not found");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } );
        // TODO: add event handlers and other initialization code here
    }

    // add additional methods as needed

}

