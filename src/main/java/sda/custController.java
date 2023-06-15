package sda;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;


public class custController {
    @FXML
    private Button edi_bt;

    @FXML
    private Button loan_bt;

    @FXML
    private Button transfer_bt;

    @FXML
    private Button balance_bt;

    @FXML
    private Button statement_bt;

    @FXML
    private Button logout;

    @FXML
    private ImageView home_btn;
    @FXML
    private void initialize()
    {
        edi_bt.setOnAction(event -> {
            // handle edit button action
            try {
                App.setRoot("Customer/edit_profile");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        loan_bt.setOnAction(event -> {
            // handle loan button action
            try {
                App.setRoot("Customer/apply_for_loan");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        transfer_bt.setOnAction(event -> {
            // handle transfer button action
            try {
                App.setRoot("Customer/transfer_cash");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        balance_bt.setOnAction(event -> {
            // handle balance button action
            try {
                App.setRoot("Customer/balance_info");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        statement_bt.setOnAction(event -> {
            // handle statement button action
            try {
                App.setRoot("Customer/bank_statement");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        logout.setOnAction(event -> {
            // handle logout button action
            try {
                App.setRoot("mainpage");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }
    // Add event handlers or any necessary logic here
}

