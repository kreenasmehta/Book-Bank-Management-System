package BookBankSystem.main;

import BookBankSystem.dao.BookDAO;
import BookBankSystem.dao.MemberDAO;
import BookBankSystem.util.Book;
import BookBankSystem.util.BookStatus;
import BookBankSystem.util.ConnectionFactory;
import BookBankSystem.util.Member;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by kreenamehta on 11/29/16.
 * main class
 */
public class BookBank {

    static Scanner sc = new Scanner(System.in);

    /**
     * main method
     * @param args
     * @throws SQLException
     */
    public static void main (String args[]) throws SQLException {
        System.out.println("Welcome to the Book Bank Management System. \n " +
                "Enter 1 if you are a member. " +
                "\n Enter 2 if you would like to sign up for the Book Bank. \n " +
                "Enter 3 to exit.");
        int entry = sc.nextInt();
        if(entry == 1){
            login();
        } else if (entry == 2){
            register();
        } else if(entry == 3){
            System.out.println("Exiting Book Bank...");
            System.exit(0);
         } else {
            System.err.println("Incorrect entry. Please start the Book Bank again!");
        }
    }

    /**
     * login an existing member
     * @throws SQLException
     */
    private static void login() throws SQLException {
        System.out.println("Please enter your username.");
        String username = sc.next();
        System.out.println("Please enter your password.");
        String password = sc.next();
        Connection connection = ConnectionFactory.getConnection();
        MemberDAO memberDAO = new MemberDAO();
        // Login
        Member member = memberDAO.getMemberByCredentials(connection, username, password);
        if(member.getFirstname() == null){
            System.out.println("Incorrect username or password. \n" +
                    "Enter 1 to re-try login. \n " +
                    "Enter any other number to exit.");
            int incorrectLoginOption = sc.nextInt();
            if(incorrectLoginOption == 1){
                login();
            } else {
                System.out.println("Exiting Book Bank...");
                System.exit(0);
            }
        }
        System.out.println("Login successful!");
        bookBankOptions(member, connection);

    }

    /**
     * registers a new member
     * @throws SQLException
     */
    private static void register() throws SQLException {
        System.out.println("Please enter your First name.");
        String firstname = sc.next();
        System.out.println("Please enter your Last name.");
        String lastname = sc.next();
        System.out.println("Please enter your Address.");
        String address = sc.next();
        System.out.println("Please enter your E-mail address.");
        String email = sc.next();
        System.out.println("Please enter your username.");
        String username = sc.next();
        System.out.println("Please enter your password.");
        String password = sc.next();

        Connection connection = ConnectionFactory.getConnection();
        MemberDAO memberDAO = new MemberDAO();

        // Check is the given username is unique
        if(memberDAO.isUsernameUnique(connection, username)){
            int memberId = memberDAO.createMember(connection, firstname, lastname, address, email, username, password);
            if(memberId != 0){
                Member member = new Member(memberId, firstname, lastname, address, email, username, password);
                bookBankOptions(member, connection);
            }

        } else{
            System.out.println("The given username already exists. Please try again with another username.");
            System.exit(0);
        }
    }

    /**
     * gives book bank options
     * 1 - read a new book
     * 2 - return a book
     * 3 - check bill
     * 4 - logout
     * @param member
     * @param connection
     * @throws SQLException
     */
    private static void bookBankOptions(Member member, Connection connection) throws SQLException {
        System.out.println("Enter 1 to get a new book to read. \n" +
                "Enter 2 to return a book. \n" +
                "Enter 3 to check your bill. \n" +
                "Enter 4 to logout.");
        int bookBankOption = sc.nextInt();
        if(bookBankOption == 1){
            readNewBook(member, connection);
        } else if (bookBankOption == 2){

        } else if (bookBankOption == 3){

        } else if(bookBankOption == 4){
            System.out.println("Logging out of Book Bank...");
            System.exit(0);
        } else{
            System.out.println("Please enter the correct option.");
            bookBankOptions(member, connection);
        }
    }

    /**
     * searches a book to read
     * @param member
     * @param connection
     * @throws SQLException
     */
    private static void readNewBook(Member member, Connection connection) throws SQLException {
        System.out.println("");
        String buffer = sc.nextLine();
        System.out.println("Enter the name of the book you would like to read.");
        String bookName = sc.nextLine();
        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.searchBook(connection, bookName);
        if(book.getId() != 0){
            if(book.getBookStatus() == BookStatus.unavailable){
                System.out.println("This book is currently unavailable. \n" +
                        "Please search for another book title.");
                readNewBook(member, connection);
            } else{
                updateBookToBeUnavailable(book, bookDAO, connection);
                assignBookToMember(member.getId(), book.getId(), connection);
            }
        } else{
            System.out.println("This book is not present in the Book Bank. \n" +
                    "Please search for another book title.");
            readNewBook(member, connection);
        }
    }

    /**
     * update the book to be unavailable
     * @param book
     * @param connection
     */
    private static void updateBookToBeUnavailable(Book book, BookDAO bookDAO, Connection connection) throws SQLException {
        // update book to be unavailable
        bookDAO.updateBookToBeUnavailable(connection, book);
    }

    /**
     * assign the book to currently logged in member by creating a new transaction
     * @param memberId
     * @param bookId
     * @param connection
     */
    private static void assignBookToMember(int memberId, int bookId, Connection connection){

    }
}
