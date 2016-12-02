package BookBankSystem.dao;

import BookBankSystem.util.Book;
import BookBankSystem.util.BookGenre;
import BookBankSystem.util.BookStatus;

import java.sql.*;
import java.util.HashMap;

/**
 * Created by kreenamehta on 11/29/16.
 * Book DAO
 */
public class BookDAO {

    public BookDAO(){

    }

    /**
     * search book by title
     * @param connection
     * @param bookName
     * @return
     * @throws SQLException
     */
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


    /**
     * update the given book to be unavailable
     * @param connection
     * @param book
     * @throws SQLException
     */
    public void updateBookToBeUnavailable(Connection connection, Book book) throws SQLException {
        PreparedStatement updateBook = connection.prepareStatement(
                "UPDATE Book b set b.status=? WHERE b.id=?;"
        );
        SQLWarning warning = updateBook.getWarnings();
        while (warning != null){
            System.err.println("Database warning: " + warning);
        }
        try{
            updateBook.setString(1, "unavailable");
            updateBook.setInt(2, book.getId());
            int updateCount = updateBook.executeUpdate();
            SQLWarning queryWarning = updateBook.getWarnings();
            while (queryWarning != null){
                System.err.println("Query warning: " + queryWarning);
            }
            if(updateCount != 1){
                throw new Exception("Error in changing the book status to be unavailable.");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            updateBook.close();
        }
    }


    /**
     * update the given book to be available
     * @param connection
     * @param bookId
     * @throws SQLException
     */
    public void updateBookToBeAvailable(Connection connection, int bookId) throws SQLException {
        PreparedStatement updateBook = connection.prepareStatement(
                "UPDATE Book b set b.status=? WHERE b.id=?;"
        );
        SQLWarning warning = updateBook.getWarnings();
        while (warning != null){
            System.err.println("Database warning: " + warning);
        }
        try{
            updateBook.setString(1, "available");
            updateBook.setInt(2, bookId);
            int updateCount = updateBook.executeUpdate();
            SQLWarning queryWarning = updateBook.getWarnings();
            while (queryWarning != null){
                System.err.println("Query warning: " + queryWarning);
            }
            if(updateCount != 1){
                throw new Exception("Error in changing the book status to be available.");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            updateBook.close();
        }
    }

    /**
     * selects all the available books with their genre
     * @param connection
     * @return
     * @throws SQLException
     */
    public HashMap<String, String> getAvailableBooks(Connection connection) throws SQLException {
        HashMap<String, String> availableBooks = new HashMap<>();
        PreparedStatement getAvailableBooks = connection.prepareStatement(
                "SELECT b.title, b.genre FROM Book b where b.status=?;"
        );
        SQLWarning warning = getAvailableBooks.getWarnings();
        while (warning != null){
            System.err.println("Database warning: " + warning);
        }
        try{
            getAvailableBooks.setString(1, "available");
            ResultSet rs = getAvailableBooks.executeQuery();
            SQLWarning queryWarning = getAvailableBooks.getWarnings();
            while (queryWarning != null){
                System.err.println("Query warning: " + queryWarning);
            }
            while(rs.next()){
//                availableBooks.add(rs.getString(1));
                availableBooks.put(rs.getString(1), rs.getString(2));
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            getAvailableBooks.close();
        }

        return availableBooks;
    }
}
