package BookBankSystem.util;

/**
 * Created by kreenamehta on 11/29/16.
 * Status of the Book
 */
public enum BookStatus {

    available("available"),
    unavailable("unavailable");

    private String bookStatus;

    /**
     * Book Status
     * @param bookStatus
     */
    BookStatus(String bookStatus){
        this.bookStatus = bookStatus;
    }

    /**
     * gets the book status
     * @return
     */
    public String getBookStatus(){
        return this.bookStatus;
    }

    /**
     * returns a book status
     * @param bookStatus
     * @return
     */
    public static BookStatus fromString(String bookStatus){
        if(bookStatus != null){
            for(BookStatus status: BookStatus.values()){
                if(bookStatus.equalsIgnoreCase(status.toString())){
                    return status;
                }
            }
        }
        return null;
    }
}
