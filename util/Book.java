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

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return author;
    }

    public void setBookStatus(BookStatus bookStatus){
        this.bookStatus = bookStatus;
    }

    public BookStatus getBookStatus(){
        return bookStatus;
    }

    public void setBookGenre(BookGenre bookGenre){
        this.bookGenre = bookGenre;
    }

    public BookGenre getBookGenre(){
        return bookGenre;
    }
}
