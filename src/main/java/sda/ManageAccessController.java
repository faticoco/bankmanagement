package sda;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import services.BankWrapperSingleton;


public class ManageAccessController {
    @FXML
    private TableView<EmpModel> table;

    @FXML
    private TableColumn<EmpModel, String> emp_id;

    @FXML
    private TableColumn<EmpModel, Boolean> delete;

    @FXML
    private TableColumn<EmpModel, Boolean> create;

    @FXML
    private TableColumn<EmpModel, Boolean> Deposit;

    @FXML
    private TableColumn<EmpModel, Boolean> Withdraw;

    @FXML
    private ImageView home_btn;

    @FXML
    private Rectangle rectangle;

    @FXML
    private Text text;

    @FXML
    private void initialize() {
        // Set up the table columns and cell values
        home_btn.setOnMouseClicked(e->
        {
            try {
                App.setRoot("Administrator_screens/ADMINmainSCREEN");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } );
        ArrayList<EmpModel> e=null;
        try {
            ObjectMapper m=new ObjectMapper();
            e=m.readValue(BankWrapperSingleton.getInstance().getB().EmpJson(),new TypeReference<ArrayList<EmpModel>>() {});
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        
    


        emp_id.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmployeeId()));
        delete.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getDelete()));
        create.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getCreate()));
        Deposit.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getDeposit()));
        Withdraw.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getWithdraw()));

    
    // Enable editing of boolean columns using checkboxes
    delete.setCellFactory(column -> new CheckBoxTableCell<EmpModel, Boolean>() {
        @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                CheckBox checkBox = new CheckBox();
                checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                    EmpModel e=getTableView().getItems().get(getIndex());
                    e.setDelete(newVal);
                    BankWrapperSingleton.getInstance().getB().setEmpstatus(e.getEmployeeId(),e.getCreate(),newVal,e.getDeposit(),e.getWithdraw());
                });
                checkBox.setSelected(item);
                setGraphic(checkBox);
            } else {
                setGraphic(null);
            }
        }
    });
    create.setCellFactory(column -> new CheckBoxTableCell<EmpModel, Boolean>() {
        @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                CheckBox checkBox = new CheckBox();
                checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                    EmpModel e=getTableView().getItems().get(getIndex());
                    e.setCreate(newVal);
                    BankWrapperSingleton.getInstance().getB().setEmpstatus(e.getEmployeeId(),newVal,e.getDelete(),e.getDeposit(),e.getWithdraw());
                });
                checkBox.setSelected(item);
                setGraphic(checkBox);
            } else {
                setGraphic(null);
            }
        }
    });
    Deposit.setCellFactory(column -> new CheckBoxTableCell<EmpModel, Boolean>() {
        @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                CheckBox checkBox = new CheckBox();
                checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                    EmpModel e=getTableView().getItems().get(getIndex());
                    e.setDeposit(newVal);
                    BankWrapperSingleton.getInstance().getB().setEmpstatus(e.getEmployeeId(),e.getCreate(),e.getDelete(),newVal,e.getWithdraw());});
                checkBox.setSelected(item);
                setGraphic(checkBox);
            } else {
                setGraphic(null);
            }
        }
    });
    Withdraw.setCellFactory(column -> new CheckBoxTableCell<EmpModel, Boolean>() {
        @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                CheckBox checkBox = new CheckBox();
                checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                    EmpModel e=getTableView().getItems().get(getIndex());
                    e.setWithdraw(newVal);
                    BankWrapperSingleton.getInstance().getB().setEmpstatus(e.getEmployeeId(),e.getCreate(),e.getDelete(),e.getDeposit(),newVal);
                });
                checkBox.setSelected(item);
                setGraphic(checkBox);
            } else {
                setGraphic(null);
            }
        }
    });
    
    
    // Add the EmpModel objects to the table
    table.getItems().addAll(e);
      
        
    }
}
@JsonIgnoreProperties(ignoreUnknown = true)

class EmpModel
{
    private String employeeId;
    private boolean delete;
    private boolean create;
    private boolean deposit;
    private boolean withdraw;
    public EmpModel()
    {

    }

    public EmpModel(String employeeId, boolean delete, boolean create, boolean deposit, boolean withdraw) {
        this.employeeId = employeeId;
        this.delete = delete;
        this.create = create;
        this.deposit = deposit;
        this.withdraw = withdraw;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public boolean getDelete() {
        return delete;
    }

    public boolean getCreate() {
        return create;
    }

    public boolean getDeposit() {
        return deposit;
    }

    public boolean getWithdraw() {
        return withdraw;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    public void setDeposit(boolean deposit) {
        this.deposit = deposit;
    }

    public void setWithdraw(boolean withdraw) {
        this.withdraw = withdraw;
    }
}
