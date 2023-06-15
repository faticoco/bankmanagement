package sda;
import java.util.ArrayList;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import services.BankWrapperSingleton;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



public class approveController {
    @FXML
    private ImageView home_btn;

    @FXML
    private TableView<LoanRequest> table;

    @FXML
    private TableColumn<LoanRequest, Double> amount_col;
    
    @FXML
    private TableColumn<LoanRequest, String> id_col;
    
    @FXML
    private TableColumn<LoanRequest, Double> Rate_col;
    
    @FXML
    private TableColumn<LoanRequest, String> total_col;
    
    @FXML
    private TableColumn<LoanRequest, Void> action;


    @FXML
    private void initialize() {
        // Add event handler to the apply_bt button
        
        home_btn.setOnMouseClicked(e->
        {
            try {
                App.setRoot("employee/employeemain");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } );


        approve_loan();
    }

   
  private void approve_loan() {
  
    ArrayList<LoanRequest> l=null;
    String json=BankWrapperSingleton.getInstance().getB().getLoanReqlist();
    l=unpack(json, l);
    table.getItems().addAll(l);
  

    amount_col.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getAmount()).asObject());
    id_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAcc_id()));
    Rate_col.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getInterestRate()).asObject());
    total_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRepayment()));


    action.setCellFactory(column -> new TableCell<LoanRequest, Void>() {
        private final Button actionButton = new Button("Approve");
    
        {
            actionButton.setOnAction(event -> {
                LoanRequest loanRequestItem = getTableView().getItems().get(getIndex());
                String accountId = loanRequestItem.getAcc_id();
               
                
                BankWrapperSingleton.getInstance().getB().set_curr_loan(accountId);
                
                try {
                    
                    
                    App.setRoot("employee/Approve");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        }
    
        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(actionButton);
            }
        }
    });



  }
  private ArrayList<LoanRequest> unpack(String jsons,ArrayList<LoanRequest> L)
  {
    //unpack json string
    ObjectMapper m=new ObjectMapper();
    try {
        L=m.readValue(jsons,new TypeReference<ArrayList<LoanRequest>>() {});
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return L;
    

  }
}
@JsonIgnoreProperties(ignoreUnknown = true)
class LoanRequest
{
    int reqid;
    
    Double Amount;
    String acc_id;
    int duration;
    Double MonthlyInstallment;
    Double InterestRate;
    String repayment;

 public Double getAmount() {
 	return this.Amount;
 }
 public void setAmount(Double Amount) {
 	this.Amount = Amount;
}

   public int getReqid() {
       return this.reqid;
   }
   public void setReqid(int reqid) {
       this.reqid = reqid;
   }


public String getAcc_id() {
    	return this.acc_id;
    }
    public void setAcc_id(String acc_id) {
    	this.acc_id = acc_id;
    }


    public int getDuration() {
    	return this.duration;
    }
    public void setDuration(int duration) {
    	this.duration = duration;
    }


    public Double getMonthlyInstallment() {
    	return this.MonthlyInstallment;
    }
    public void setMonthlyInstallment(Double MonthlyInstallment) {
    	this.MonthlyInstallment = MonthlyInstallment;
    }


    public Double getInterestRate() {
    	return this.InterestRate;
    }
    public void setInterestRate(Double InterestRate) {
    	this.InterestRate = InterestRate;
    }


    public String getRepayment() {
    	return this.repayment;
    }
    public void setRepayment(String repayment) {
    	this.repayment = repayment;
    }
    public LoanRequest()
    {

    }

    public LoanRequest(Double Amount, String acc_id, int duration, Double MonthlyInstallment, Double InterestRate, String repayment)
    {
        this.Amount = Amount;
        this.acc_id = acc_id;
        this.duration = duration;
        this.MonthlyInstallment = MonthlyInstallment;
        this.InterestRate = InterestRate;
        this.repayment = repayment;
    }
    

}
   






