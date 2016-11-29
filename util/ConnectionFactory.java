package BookBankSystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by kreenamehta on 11/29/16.
 * Connection Factory to instantiate a connection to the Book Bank Database
 */
public class ConnectionFactory {

    private static ConnectionFactory instance = new ConnectionFactory();
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/BookBank";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    private ConnectionFactory(){
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found.");
            e.printStackTrace();
        }
    }


    /**
     * creates connection
     * @return  -   connection
     */
    private Connection createConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Unable to connect to the database.");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * gets a connection instance
     * @return  - connection
     */
    public static Connection getConnection(){
        return instance.createConnection();
    }



}
