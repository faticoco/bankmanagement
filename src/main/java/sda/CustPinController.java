package sda;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.BankWrapperSingleton;

public class CustPinController {
    @FXML
    private Button update_bt;

    @FXML
    private TextField old;

    @FXML
    private TextField newpi;

    @FXML
    private TextField newpin;

    @FXML
    private Text status;

    @FXML
    private ImageView home_btn;

    @FXML
    private void handleUpdateButtonAction() {
        Integer oldPin = Integer.parseInt(old.getText());
        String newPin = newpi.getText();
        String confirmPin = newpin.getText();
        if (BankWrapperSingleton.getInstance().getB().validate_pin(oldPin)) {
            if (newPin.equals(confirmPin)) {
                BankWrapperSingleton.getInstance().getB().updatepin(Integer.parseInt(newPin));
                status.setText("Pin Updated");
            } else {
                status.setText("Pins do not match");
            }
        } else {
            status.setText("Incorrect Pin");
        }


    
    }

    @FXML
    private void initialize() {
        home_btn.setOnMouseClicked(arg0 ->
        {
            try {
                App.setRoot("Customer/customer_main_page");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } );
        update_bt.setOnAction(e->handleUpdateButtonAction());
        // Code to initialize the controller goes here
    }
}
