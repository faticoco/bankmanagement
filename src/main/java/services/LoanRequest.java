
package services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanRequest {
    private int reqid;

    
    private Double Amount;
    private String acc_id;
    private Double duration;
    private Double InterestRate;
    private String repayment;
    
    public int getReqid() {
        return this.reqid;
    }
    public void setReqid(int reqid) {
        this.reqid = reqid;
    }
    
    public Double getAmount() {
        return Amount;
    }
    public void setAmount(Double amount) {
        Amount = amount;
    }
    public String getAcc_id() {
        return acc_id;
    }
    public void setAcc_id(String acc_id) {
        this.acc_id = acc_id;
    }
    public Double getDuration() {
        return duration;
    }
    public void setDuration(Double duration) {
        this.duration = duration;
    }
    public Double getInterestRate() {
        return InterestRate;
    }
    public void setInterestRate(Double interestRate) {
        InterestRate = interestRate;
    }
    public String getRepayment() {
        return repayment;
    }
    public void setRepayment(String repayment) {
        this.repayment = repayment;
    }
  

}
