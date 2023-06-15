package sda;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import services.BankWrapperSingleton;

public class DepositController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField field_id;

    @FXML
    private TextField field_pin;

    @FXML
    private TextField field_amount;

    @FXML
    private Button confirm_bt;

    @FXML
    private ImageView home_btn;
    @FXML 
    private Text status;

    // initialize method is called after loading the FXML file
    public void initialize() {
        // set up event handlers for the buttons and other controls
        confirm_bt.setOnAction(e -> handleConfirm());
        home_btn.setOnMouseClicked(e -> handleHome());
    }

    private void handleConfirm() {
        // retrieve values from text fields and perform deposit operation
        String id = field_id.getText();
        String pin = field_pin.getText();
        double amount = Double.parseDouble(field_amount.getText());
        if (id.isEmpty() || pin.isEmpty() || amount <= 0) 
            {
                // show error message
                // ...
                
                status.setText("Invalid Input");


            }
        else
        {
            String st=BankWrapperSingleton.getInstance().getB().deposit(id, amount, Integer.parseInt(pin));
            status.setText(st);
            
        }

        // perform deposit operation with given account id, pin, and amount
        // ...
    }

    private void handleHome() {
        // go back to home screen
        // ...
        try {
            App.setRoot("employee/employeemain");
        } catch (Exception e) {}
    }

}

