package sda;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class AdminController {



    @FXML
    private Button add_user_bt;

    @FXML
    private Button rem_user_bt;

    @FXML
    private Button Logout;

    @FXML
    private Button users_access_bt;

    public void initialize() {
        // initialize the controller
    

        add_user_bt.setOnAction(event -> {
            // handle add button action
            try {
                App.setRoot("Administrator_screens/createEMPLOYEE");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        rem_user_bt.setOnAction(event -> {
            // handle remove button action
            try {
                App.setRoot("Administrator_screens/delete_EMPLOYEE");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

       Logout.setOnAction(event -> {
            // handle view all button action
            try {
                App.setRoot("mainpage");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

            users_access_bt.setOnAction(event -> {
            // handle manage access button action
            try {
                App.setRoot("Administrator_screens/EmpAccess");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

}
