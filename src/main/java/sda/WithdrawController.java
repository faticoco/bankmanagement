package sda;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.BankWrapperSingleton;

public class WithdrawController {

    @FXML
    private TextField field_id;

    @FXML
    private TextField field_pin;

    @FXML
    private TextField field_amount;

    @FXML
    private Button bt_confirm;

    @FXML
    private Text status;
    @FXML
    private ImageView home_btn;
    
    // Method to withdraw cash from an account (placeholder im
    @FXML
    public void initialize()
    {
        home_btn.setOnMouseClicked(arg0 ->{ 
            try {
                App.setRoot("employee/employeemain");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        bt_confirm.setOnAction(e -> {
            try {
                
                String accountId = field_id.getText();
                String accountPin = field_pin.getText();
                String withdrawAmount = field_amount.getText();
                if (accountId.isEmpty() || accountPin.isEmpty() || withdrawAmount.isEmpty()) {
                status.setText("Please fill in all the fields.");
                return;
            }
            String st=BankWrapperSingleton.getInstance().getB().withdraw(accountId,Double.parseDouble(withdrawAmount),Integer.parseInt(accountPin));
            if(status.getText().equals("Success"))
            {
                clearFields();
            }
            status.setText(st);
        } catch (Exception ee) {
            status.setText("Invalid Input");
        }
        });

    }
    // Method to clear the input fields
    private void clearFields() {
        field_id.clear();
        field_pin.clear();
        field_amount.clear();
    }
}

