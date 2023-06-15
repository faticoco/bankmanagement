package sda;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.BankWrapperSingleton;

public class ApplyController {
    @FXML
    private TextField amount;

    @FXML
    private TextField duration;

    @FXML
    private TextField interest_rate;

    @FXML
    private TextField repayment;

    @FXML
    private Button apply_bt;

    @FXML
    private Text message;

    @FXML
    private ImageView home_btn;
      

    @FXML
    private void initialize() {
        // Add event handler to the apply_bt button
        home_btn.setOnMouseClicked(e->home());
        apply_bt.setOnAction(event -> apply());
    }

    private void home()
    {
        try {
            App.setRoot("Customer/customer_main_page");
        } catch (Exception e) {
        }   
    }
    
    private void apply() {
        // Get the loan amount, duration, and interest rate from the text fields
        double loanAmount = Double.parseDouble(amount.getText());
        Double loanDuration = Double.parseDouble(duration.getText());
        double interestRate = Double.parseDouble(interest_rate.getText());
        //check if fields are empty
        if (loanAmount == 0 || loanDuration == 0 || interestRate == 0) {
            repayment.setText("Please fill in all fields");
            return;
        }
        //check if fields are null
        if (amount.getText().isEmpty() || duration.getText().isEmpty() || interest_rate.getText().isEmpty()) {
            repayment.setText("Please fill in all fields");
            return;
        }
        //check if fields are valid
        if (loanAmount < 0 || loanDuration < 0 || interestRate < 0) {
            repayment.setText("Please enter valid values");
            return;
        }
        //calculate the repayment
        

        // Perform the calculation
        double totalRepayment = loanAmount * (1 + (interestRate / 100)) * loanDuration;
        

        // Set the calculated repayment amount in the repayment text field
        repayment.setText(String.format("%.2f", totalRepayment));

        BankWrapperSingleton.getInstance().getB().apply_for_loan(loanAmount , loanDuration , interestRate , repayment.getText());
        message.setText("Loan request sent");
    }

    // You can add other methods and event handlers as needed
}
