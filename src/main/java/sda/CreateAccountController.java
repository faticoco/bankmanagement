package sda;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.BankWrapperSingleton;

public class CreateAccountController {

    @FXML
    private TextField field_name;

    @FXML
    private TextField field_acc_type;

    @FXML
    private TextField field_cnic;

    @FXML
    private Button create_bt;
    @FXML
    private Text status;
    @FXML
    private ImageView home_btn;

    @FXML
    private void initialize() {
        // Set the logo image

        // Set the title text

        // Set an event handler for the Create button

        home_btn.setOnMouseClicked(e -> home());
        create_bt.setOnAction(e -> createAccount());
    }

    private void home() {
        try {
            App.setRoot("employee/employeemain");
        } catch (Exception e) {
        }
    }

    private void createAccount() {
        try {
            String name = field_name.getText();
            String PIN = field_acc_type.getText();
            String cnic = field_cnic.getText();
            if (name.equals("") || PIN.equals("") || cnic.equals("")) {
                status.setText("Please fill all the fields");
            } else if (cnic.length() != 13 || !cnic.matches("\\d{13}")) {
                status.setText("Invalid CNIC format. CNIC should be a 13-digit number.");
            } else if (PIN.length() != 4) {
                status.setText("Invalid PIN format. PIN should be a 4-digit number.");
            } else {
                String s = BankWrapperSingleton.getInstance().getB().create_account(name, cnic, Integer.parseInt(PIN));
                status.setText(s);
            }
        } catch (Exception e) {
            status.setText("Invalid input");

        }
    }

    // Add any additional methods or event handlers as needed
}
