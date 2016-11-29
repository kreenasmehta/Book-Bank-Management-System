package BookBankSystem.main;

import BookBankSystem.dao.MemberDAO;
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

    public static void main (String args[]) throws SQLException {
        System.out.println("Welcome to the Book Bank Management System. \n " +
                "Enter 1 if you are a member. " +
                "\n Enter 2 if you would like to sign up for the Book Bank.");
        int entry = sc.nextInt();
        if(entry == 1){
            login();
        } else if (entry == 2){
            register();
        } else {
            System.err.println("Incorrect entry. Please start the Book Bank again!");
        }
    }


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
            System.err.println("Incorrect username or password");
            System.exit(0);
        }
        System.out.println("Login successful!");

    }

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
            }

        }
    }
}
