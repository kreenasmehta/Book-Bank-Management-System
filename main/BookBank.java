package BookBankSystem.main;

import BookBankSystem.dao.BillDAO;
import BookBankSystem.dao.BookDAO;
import BookBankSystem.dao.MemberDAO;
import BookBankSystem.dao.TransactionDAO;
import BookBankSystem.util.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
        System.out.println("Enter 1 to get a new book to read (Search any book by the title name). \n" +
                "Enter 2 to see the list of all the available books. \n" +
                "Enter 3 to return a book. \n" +
                "Enter 4 to logout. \n" +
                "Enter 5 to delete your membership");
        int bookBankOption = sc.nextInt();
        if(bookBankOption == 1){
            readNewBook(member, connection);
        } else if(bookBankOption == 2){
            getAvailableBooks(connection, member);
        }else if (bookBankOption == 3){
            returnBook(member, connection);
        } else if(bookBankOption == 4){
            System.out.println("Logging out of Book Bank...");
            System.exit(0);
        } else if (bookBankOption == 5){
            deleteMembership(member, connection);
        }else{
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
                assignBookToMember(member, book, connection);
            }
        } else{
            System.out.println("This book is not present in the Book Bank. \n" +
                    "Please search for another book title.");
            readNewBook(member, connection);
        }
    }

    private static void getAvailableBooks(Connection connection, Member member) throws SQLException {
        BookDAO bookDAO = new BookDAO();
        List<String> availableBooks = bookDAO.getAvailableBooks(connection);
        if(availableBooks.size()>0){
            System.out.println();
            System.out.println("Following books are currently available");
            for(String availableBook: availableBooks){
                System.out.println(availableBook);
            }
        } else{
            System.out.println();
            System.out.println("No books are currently avaiable");
        }
        System.out.println("Redirecting back to the main menu...");
        System.out.println();
        bookBankOptions(member, connection);

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
     * @param member
     * @param book
     * @param connection
     * @throws SQLException
     */
    private static void assignBookToMember(Member member, Book book, Connection connection) throws SQLException {
        TransactionDAO transactionDAO = new TransactionDAO();
        Calendar c = Calendar.getInstance();
        String dateOfIssue = c.getTime().toString();
        c.add(Calendar.MINUTE, 1);
        String dateOfReturn = c.getTime().toString();
        int transactionId = transactionDAO.createTransaction(connection, member.getId(), book.getId(), dateOfIssue, dateOfReturn);
        if(transactionId != 0){
            System.out.println("Successful transaction. \n" +
                    "Return date of your book " + book.getTitle() + " is: " + dateOfReturn +". \n" +
                    "Your transaction ID is " + transactionId);
            System.out.println("Returning back to the banking options....");
            bookBankOptions(member, connection);
        }
    }


    /**
     * return a book
     * @param member
     * @param connection
     * @throws SQLException
     */
    private static void returnBook(Member member, Connection connection) throws SQLException {
        System.out.println("Enter your transaction id");
        int transactionId = sc.nextInt();
        TransactionDAO transactionDAO = new TransactionDAO();
        Transaction transaction = transactionDAO.getTransactionById(connection, transactionId);
        updateBookToBeAvailable(transaction.getBookId(), connection);
        updateTransaction(transactionDAO, transactionId, connection);
        String dateOfReturn = transaction.getDateOfReturn();
        try{
            DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
            Date dueDate = (Date)formatter.parse(dateOfReturn);
            Calendar c = Calendar.getInstance();
            Date today = c.getTime();
            int compare = today.compareTo(dueDate);
            if(compare == 0 || compare < 0){
                System.out.println("Return successful. \n" +
                        "Returning back to the banking options....");
                bookBankOptions(member, connection);
            } else{
                createBill(dueDate, today, member.getId(), transactionId, connection);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * update the book to be avialable after return
     * @param bookId
     * @param connection
     * @throws SQLException
     */
    private static void updateBookToBeAvailable(int bookId, Connection connection) throws SQLException {
        BookDAO bookDAO = new BookDAO();
        try {
            bookDAO.updateBookToBeAvailable(connection, bookId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * update the transaction to have book returned to be true
     * @param transactionDAO
     * @param transactionId
     * @param connection
     * @throws SQLException
     */
    private static void updateTransaction(TransactionDAO transactionDAO, int transactionId, Connection connection) throws SQLException {
        transactionDAO.updateTransaction(connection, transactionId);
    }

    /**
     * create a new bill
     * @param dueDate
     * @param today
     * @param memberId
     * @param transactionId
     * @param connection
     * @throws SQLException
     */
    private static void createBill(Date dueDate,
                                   Date today,
                                   int memberId,
                                   int transactionId,
                                   Connection connection) throws SQLException {
        long dueDateLong = dueDate.getTime();
        long todayLong = today.getTime();
        int noOfDaysDue = (int) TimeUnit.DAYS.convert(todayLong - dueDateLong, TimeUnit.MILLISECONDS);
        int amount = (noOfDaysDue+1)*10;
        boolean isPad = false;
        BillDAO billDAO = new BillDAO();
        int billId = billDAO.createBill(connection, today.toString(), memberId, transactionId, amount, isPad);
        if(billId != 0){
            System.out.println("The billing details are as follows: \n" +
                    "Bill No.: " + billId +"\n" +
                    "Bill amount: " + amount);
        }

    }

    /**
     * deletes the membership of the logged in person
     * @param member
     * @param connection
     * @throws SQLException
     */
    private static void deleteMembership(Member member, Connection connection) throws SQLException {
        MemberDAO memberDAO = new MemberDAO();
        memberDAO.deleteMembership(connection, member.getId());
        System.out.println("Your membership from Book Bank has been terminated. \n" +
                "Logging out of the system...");
        System.exit(0);
    }
}
