package BookBankSystem.dao;

import BookBankSystem.util.Book;
import BookBankSystem.util.BookGenre;
import BookBankSystem.util.BookStatus;

import java.sql.*;

/**
 * Created by kreenamehta on 11/29/16.
 * Book DAO
 */
public class BookDAO {

    public BookDAO(){

    }

    public Book searchBook(Connection connection, String bookName) throws SQLException {

        Book book = new Book();
        PreparedStatement searchBook = connection.prepareStatement(
                "SELECT * FROM Book b WHERE b.title=?;"
        );
        SQLWarning warning = searchBook.getWarnings();
        while(warning != null){
            System.err.println("Database warning: " + warning);
        }
        try{
            searchBook.setString(1, bookName);
            ResultSet rs = searchBook.executeQuery();
            SQLWarning queryWarning = searchBook.getWarnings();
            while(queryWarning != null){
                System.err.println("Query warning: " + queryWarning);
            }
            while(rs.next()){
                book.setId(rs.getInt(1));
                book.setTitle(rs.getString(2));
                book.setAuthor(rs.getString(3));
                String status = rs.getString(4);
                BookStatus bookStatus = BookStatus.fromString(status);
                book.setBookStatus(bookStatus);
                String genre = rs.getString(5);
                BookGenre bookGenre = BookGenre.fromString(genre);
                book.setBookGenre(bookGenre);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            searchBook.close();
        }

        return book;

    }
}
