package BookBankSystem.util;

/**
 * Created by kreenamehta on 11/30/16.
 * Bill
 */
public class Bill {

    int id;
    String dateOfBilling;
    int memberId;
    int transactionId;
    int amount;
    boolean isPaid;

    public Bill(){

    }

    public Bill (int id,
                 String dateOfBilling,
                 int memberId,
                 int transactionId,
                 int amount,
                 boolean isPaid){
        this.id = id;
        this.dateOfBilling = dateOfBilling;
        this.memberId = memberId;
        this.transactionId = transactionId;
        this.amount = amount;
        this.isPaid = isPaid;
    }

    /**
     * set bill id
     * @param id
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * get bill id
     * @return
     */
    public int getId(){
        return id;
    }

    /**
     * set date of billing
     * @param dateOfBilling
     */
    public void setDateOfBilling(String dateOfBilling){
        this.dateOfBilling = dateOfBilling;
    }

    /**
     * get date of billing
     * @return
     */
    public String getDateOfBilling(){
        return dateOfBilling;
    }

    /**
     * set member id
     * @param memberId
     */
    public void setMemberId(int memberId){
        this.memberId = memberId;
    }

    /**
     * get member id
     * @return
     */
    public int getMemberId(){
        return memberId;
    }

    /**
     * set transaction id
     * @param transactionId
     */
    public void setTransactionId(int transactionId){
        this.transactionId = transactionId;
    }

    /**
     * get transaction id
     * @return
     */
    public int getTransactionId(){
        return transactionId;
    }

    /**
     * set amount
     * @param amount
     */
    public void setAmount(int amount){
        this.amount = amount;
    }

    /**
     * get bill amount
     * @return
     */
    public int getAmount(){
        return amount;
    }

    /**
     * set is paid
     * @param isPaid
     */
    public void setPaid(boolean isPaid){
        this.isPaid = isPaid;
    }

    /**
     * get is paid
     * @return
     */
    public boolean isPaid(){
        return isPaid;
    }

}
