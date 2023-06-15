package sda;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import services.BankWrapperSingleton;
import javafx.scene.control.Button;

public class accounts_status_Controller {
    @FXML
    private ImageView home_btn;

    @FXML
    private TableView<Account> table;

    @FXML
    private TableColumn<Account, String> name_col;
    
    @FXML
    private TableColumn<Account, String> id_col;
    
    @FXML
    private TableColumn<Account, String> last_active;
    
    @FXML
    private TableColumn<Account, Void> action;

    @FXML
    public void initialize() {
        // initialize the controller
    

        home_btn.setOnMouseClicked(e->{
            try {
                App.setRoot("employee/employeemain");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });


        view_all_accounts();

    }

@FXML
    private void view_all_accounts() {

        
        ArrayList<Account> accounts=null;
        try
        {
            ObjectMapper m=new ObjectMapper();
            accounts=m.readValue(BankWrapperSingleton.getInstance().getB().AccJson(),new TypeReference<ArrayList<Account>>() {});
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        name_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        id_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAcc_id()));
        last_active.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastactive()));
        action.setCellFactory(column -> new TableCell<Account, Void>() {
            private final Button deleteButton = new Button();
            
            {
                deleteButton.setOnAction(event -> {
                    Account account = getTableRow().getItem();
                    if (account != null) {
                        boolean newStatus = !account.getStatus();
                        BankWrapperSingleton.getInstance().getB().set_Acc_status(newStatus, account.getAcc_id());
                        account.setStatus(newStatus);
                        updateItem(null, false); // Update the cell
                    }
                });
            }
            
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                
                if (empty || getTableRow().getItem() == null) {
                    setGraphic(null);
                } else {
                    Account account = getTableRow().getItem();
                    if (account.getStatus()) {
                        deleteButton.setText("Deactivate");
                    } else {
                        deleteButton.setText("Activate");
                    }
                    setGraphic(deleteButton);
                }
            }
        });
    table.getItems().addAll(accounts);




    }
}
@JsonIgnoreProperties(ignoreUnknown = true)
class Account
{
    private String acc_id;
    private String name;
    private Boolean status;
    private String lastactive;

    public String getAcc_id() {
    	return this.acc_id;
    }
    public void setAcc_id(String acc_id) {
    	this.acc_id = acc_id;
    }


    public String getName() {
    	return this.name;
    }
    public void setName(String name) {
    	this.name = name;
    }


    public Boolean getStatus() {
    	return this.status;
    }
    public void setStatus(Boolean status) {
    	this.status = status;
    }


    public String getLastactive() {
    	return this.lastactive;
    }
    public void setLastactive(String lastactive) {
    	this.lastactive = lastactive;
    }

    Account()
    {

    }
}