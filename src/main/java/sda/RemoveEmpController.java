package sda;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import services.BankWrapperSingleton;

public class RemoveEmpController {
    @FXML
    private TextField emp_id_field;

    @FXML
    private Button del_bt;

    @FXML
    private Text status;
    @FXML
    private Button home_bt;
    public void initialize() {
        // Add code for initializing the controller
        home_bt.setOnAction(e->{
            try {
                App.setRoot("Administrator_screens/ADMINmainSCREEN");
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        del_bt.setOnAction(e -> {
            if(emp_id_field.getText().isEmpty()) {
                // Add code for handling the empty field
                //add text that says field is empty
                //add red border to the field
                //add red background to the field
                status.setText("Field is empty");
                emp_id_field.setStyle("-fx-border-color: red ; -fx-background-color: #FFCDD2;");
                
            }

            else {
                // Add code for handling the non-empty field
                boolean success = BankWrapperSingleton.getInstance().getB().remove_Employee(emp_id_field.getText());
                if(success) {
                    //add text that says employee was deleted
                    //add green border to the field
                    //add green background to the field
                    status.setText("Employee deleted successfully!");
                    

                }
                else {
                    //add text that says employee was not found
                    //add red border to the field
                    //add red background to the field
                    status.setText("Employee Not Found");
                    emp_id_field.setStyle("-fx-border-color: red ; -fx-background-color: #FFCDD2;");
                }
            }
        });
    }
}
