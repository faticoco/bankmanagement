package sda;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.BankWrapperSingleton;

public class empprofilecontroller {
    @FXML
    private Text emp_id;

    @FXML
    private TextField name_field;

    @FXML
    private TextField email_field;

    @FXML
    private Button confirm_bt;

    @FXML
    private Button change_pass_bt;
    @FXML
    private ImageView home_btn;

    @FXML
    public void initialize()
    {
        emp_id.setText(BankWrapperSingleton.getInstance().getB().get_curr_Emp_id());
        name_field.setText(BankWrapperSingleton.getInstance().getB().get_curr_Emp_name());
        email_field.setText(BankWrapperSingleton.getInstance().getB().get_curr_Emp_phone());
        confirm_bt.setOnAction(e->update());
        home_btn.setOnMouseClicked(arg0 ->
        {
            try {
                App.setRoot("employee/employeemain");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } );
        change_pass_bt.setOnAction(e ->
        {
            try {
                App.setRoot("employee/changepass");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } );

    }
    public void update() {
        
        BankWrapperSingleton.getInstance().getB().updateemp(name_field.getText(),email_field.getText());

        
    }

    // Add event handlers or any necessary logic here
}
