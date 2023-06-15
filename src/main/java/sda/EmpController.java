package sda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EmpController {

    @FXML
    private Button deposit_btn;

    @FXML
    private Button withdraw_btn;

    @FXML
    private Button approve_btn; 

    @FXML
    private Button edit_btn;

    @FXML
    private Button manage_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private Label messageLabel;

    @FXML
    void handleDeposit(ActionEvent event) {
        try {
            App.setRoot("employee/Deposit");
        } catch (Exception e) {
            e.printStackTrace(
            );
            // TODO: handle exception
        }
    }

    @FXML
    void handleWithdraw(ActionEvent event) {
        try {
            App.setRoot("employee/Withdraw");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @FXML
    void handleApprove(ActionEvent event) {
        try {
            App.setRoot("employee/Manageloans");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handleEdit(ActionEvent event) {
        try {
            App.setRoot("employee/profile");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
    }

    @FXML
    void handleManage(ActionEvent event) {
        try {
            App.setRoot("employee/manageAccounts");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @FXML
    void handleLogout(ActionEvent event) {
        try{
            App.setRoot("mainpage");
        }
        catch(Exception e)
        {
            e.printStackTrace();

        }
    }
    @FXML
    void initialize()
    {
        deposit_btn.setOnAction(e->handleDeposit(e));
        withdraw_btn.setOnAction(e->handleWithdraw(e));
        approve_btn.setOnAction(e->handleApprove(e));
        edit_btn.setOnAction(e->handleEdit(e));
        manage_btn.setOnAction(e->handleManage(e));
        logout_btn.setOnAction(e->handleLogout(e));
    }

}
