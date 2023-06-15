package sda;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class manageController {
    @FXML
    private Button create_bt;

    @FXML
    private Button del_bt;

    @FXML
    private Button view_all_bt;
    @FXML
    private ImageView home_btn;

    @FXML
    private void handleCreateButton(ActionEvent event) throws IOException {
      
            App.setRoot("employee/create_account");
       
        // Implement functionality for create button
    }

    @FXML
    private void handleDeleteButton(ActionEvent event) {
        // Implement functionality for delete button
        try {
            App.setRoot("employee/delete_account");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @FXML
    private void handleViewAllButton(ActionEvent event) {
        try {
            App.setRoot("employee/ManageAccountsStatus");
        } catch (Exception e) {
            // TODO: handle exception
        }
      
    }
    
    @FXML
    private void initialize() throws IOException
    {
        // Implement functionality for initialize
        create_bt.setOnAction(e->{
            try {
                handleCreateButton(e);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        del_bt.setOnAction(e->handleDeleteButton(e));
        view_all_bt.setOnAction(e->handleViewAllButton(e));
        home_btn.setOnMouseClicked(e->{
            try {
                App.setRoot("employee/employeemain");
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
    }
}
