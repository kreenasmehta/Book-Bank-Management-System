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

    /**
     * set transaction id
     * @param id
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * get transaction id
     * @return
     */
    public int getId(){
        return id;
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
     * set book id
     * @param bookId
     */
    public void setBookId(int bookId){
        this.bookId = bookId;
    }

    /**
     * get book id
     * @return
     */
    public int getBookId(){
        return bookId;
    }

    /**
     * set date if issue
     * @param dateOfIssue
     */
    public void setDateOfIssue(String dateOfIssue){
        this.dateOfIssue = dateOfIssue;
    }

    /**
     * get date of issue
     * @return
     */
    public String getDateOfIssue(){
        return dateOfIssue;
    }

    /**
     * set date of return
     * @param dateOfReturn
     */
    public void setDateOfReturn(String dateOfReturn){
        this.dateOfReturn = dateOfReturn;
    }

    /**
     * get date of return
     * @return
     */
    public String getDateOfReturn(){
        return dateOfReturn;
    }
}
