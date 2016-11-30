package BookBankSystem.util;

/**
 * Created by kreenamehta on 11/29/16.
 * Transaction
 */
public class Transaction {

    private int id;
    private int memberId;
    private int bookId;
    private String dateOfIssue;
    private String dateOfReturn;

    public Transaction(){

    }

    public Transaction(int id,
                       int memberId,
                       int bookId,
                       String dateOfIssue,
                       String dateOfReturn){
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.dateOfIssue = dateOfIssue;
        this.dateOfReturn = dateOfReturn;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setMemberId(int memberId){
        this.memberId = memberId;
    }

    public int getMemberId(){
        return memberId;
    }

    public void setBookId(int bookId){
        this.bookId = bookId;
    }

    public int getBookId(){
        return bookId;
    }

    public void setDateOfIssue(String dateOfIssue){
        this.dateOfIssue = dateOfIssue;
    }

    public String getDateOfIssue(){
        return dateOfIssue;
    }

    public void setDateOfReturn(String dateOfReturn){
        this.dateOfReturn = dateOfReturn;
    }

    public String getDateOfReturn(){
        return dateOfReturn;
    }
}
