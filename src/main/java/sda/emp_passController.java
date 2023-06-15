package sda;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.BankWrapperSingleton;

public class emp_passController {
    @FXML
    private TextField field_1;

    @FXML
    private TextField field_2;

    @FXML
    private TextField field_3;

    @FXML
    private Button update_bt;

    @FXML
    private Text status;

    @FXML
    private ImageView home_btn;
    @FXML
    public void initialize() {
        
        update_bt.setOnAction(e->update());
        home_btn.setOnMouseClicked(arg0 ->
        {
            try {
                App.setRoot("employee/profile");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } );
    }
    public void update() {

        if (field_1.getText().equals(BankWrapperSingleton.getInstance().getB().get_curr_emp_pass())) {
            if (field_2.getText().equals(field_3.getText())) {
                if(field_2.getText().length()<8)
                {
                    status.setText("Password must be atleast 8 characters long");
                    return;
                }
                BankWrapperSingleton.getInstance().getB().updateemppass(field_2.getText());
                status.setText("Password Updated");
            } else {
                status.setText("Passwords do not match");
            }
        } else {
            status.setText("Incorrect Password");
        }


        
    }

    // Add event handlers or any necessary logic here
}

