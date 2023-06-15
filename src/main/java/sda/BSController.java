package sda;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import services.BankWrapperSingleton;

public class BSController {
    @FXML
    private TableView<Transaction> table;
    @FXML
    private ImageView home_btn;

    @FXML
    private TableColumn<Transaction, String> type_col;

    @FXML
    private TableColumn<Transaction, String> Date_col;

    @FXML
    private TableColumn<Transaction, Double> Amount_col;

    public void initialize() {
        // Set up the table columns
        ArrayList<Transaction> t=null;
        try {
            ObjectMapper m= new ObjectMapper();
            t=m.readValue(BankWrapperSingleton.getInstance().getB().TransactionJson(),new TypeReference<ArrayList<Transaction>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        home_btn.setOnMouseClicked(arg0 ->
        {
            try {
                App.setRoot("Customer/customer_main_page");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } );
        
        type_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        Date_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));
        Amount_col.setCellValueFactory(cellData -> new SimpleDoubleProperty((cellData.getValue().getAmount())).asObject());



        // Add sample data to the table (you can replace this with your own logic)
        table.getItems().addAll(t);
       
    }

    // You can add other methods and event handlers as needed
}
@JsonIgnoreProperties(ignoreUnknown = true)
class Transaction
{
    String type;
    String date;
    double amount;
    public Transaction()
    {

    }
    public Transaction(String type, String date, double amount) {
        this.type = type;
        this.date = date;
        this.amount = amount;
    }
    public String getType() {
        return type;
    }
    public String getDate() {
        return date;
    }
    public double getAmount() {
        return amount;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
