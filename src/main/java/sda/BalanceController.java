package sda;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.BankWrapperSingleton;

public class BalanceController {

    @FXML
    private Text acc_id;

    @FXML
    private Text trans;

    @FXML
    private Text balance;

    @FXML
    private ImageView home_btn;

    // Add your controller logic here
    @FXML
    public void initialize()
    {
        home_btn.setOnMouseClicked(e->
        {
            try {
                App.setRoot("Customer/customer_main_page");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        acc_id.setText(BankWrapperSingleton.getInstance().getB().get_id_curr_customer());
        trans.setText(BankWrapperSingleton.getInstance().getB().get_last_active_curr_customer());
        balance.setText(BankWrapperSingleton.getInstance().getB().get_balance_curr_customer());

    }
    
}
