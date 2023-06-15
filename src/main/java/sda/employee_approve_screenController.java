package sda;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.BankWrapperSingleton;

public class employee_approve_screenController {
    
 @FXML
private Text acc_id;

@FXML
private Button approve_bt;


@FXML
private ImageView home_btn;


@FXML
private Button rej_bt;



@FXML
private Text loan_amount;

@FXML
private Text rate_text;


@FXML
public void initialize()
{
    home_btn.setOnMouseClicked(e->{
        try {
            App.setRoot("employee/employeemain");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    });

    //setting the vales equal to current loan request values
    acc_id.setText(BankWrapperSingleton.getInstance().getB().get_curr_loan_id());
    loan_amount.setText(BankWrapperSingleton.getInstance().getB().get_curr_loan_amount());
    rate_text.setText(BankWrapperSingleton.getInstance().getB().get_curr_loan_rate());


    approve_bt.setOnMouseClicked(e->{
        try {
            BankWrapperSingleton.getInstance().getB().app_loan();
            App.setRoot("employee/employeemain");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    });


    rej_bt.setOnMouseClicked(e->{
        try {
            BankWrapperSingleton.getInstance().getB().rej_loan();
            App.setRoot("employee/employeemain");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    });
}




}
