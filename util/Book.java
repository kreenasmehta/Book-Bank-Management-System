package BookBankSystem.util;

/**
 * Created by kreenamehta on 11/29/16.
 * Book
 */
public class Book {

    private int id;
    private String title;
    private String author;
    private BookStatus bookStatus;
    private BookGenre bookGenre;

    public Book(){

    }

    public Book(int id,
                String title,
                String author,
                BookStatus bookStatus,
                BookGenre bookGenre){
        this.id = id;
        this.title = title;
        this.author = author;
        this.bookStatus = bookStatus;
        this.bookGenre = bookGenre;
    }

    /**
     * set book id
     * @param id
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * get book id
     * @return
     */
    public int getId(){
        return id;
    }

    /**
     * set title
     * @param title
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * get title
     * @return
     */
    public String getTitle(){
        return title;
    }

    /**
     * set author
     * @param author
     */
    public void setAuthor(String author){
        this.author = author;
    }

    /**
     * get author
     * @return
     */
    public String getAuthor(){
        return author;
    }

    /**
     * set book status
     * @param bookStatus
     */
    public void setBookStatus(BookStatus bookStatus){
        this.bookStatus = bookStatus;
    }

    /**
     * get book status
     * @return
     */
    public BookStatus getBookStatus(){
        return bookStatus;
    }

    /**
     * set book genre
     * @param bookGenre
     */
    public void setBookGenre(BookGenre bookGenre){
        this.bookGenre = bookGenre;
    }

    /**
     * get book genre
     * @return
     */
    public BookGenre getBookGenre(){
        return bookGenre;
    }
}
