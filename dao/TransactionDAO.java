package BookBankSystem.dao;

import BookBankSystem.util.Transaction;

import java.sql.*;

/**
 * Created by kreenamehta on 11/29/16.
 * TransactionDAO
 */
public class TransactionDAO {

    public TransactionDAO(){

    }

    public int createTransaction(Connection connection,
                                         int memberId,
                                         int bookId,
                                         String dateOfIssue,
                                         String dateOfReturn) throws SQLException {
        int transactionId = 0;
        PreparedStatement createTransaction = connection.prepareStatement(
                "INSERT INTO Transaction (member_id, book_id, dateOfIssue, dateOfReturn) " +
                        "VALUES (?,?,?,?);"
        , Statement.RETURN_GENERATED_KEYS);
        SQLWarning warning = createTransaction.getWarnings();
        while(warning != null){
            System.err.println("Database warning: " + warning);
        }
        try{
            createTransaction.setInt(1, memberId);
            createTransaction.setInt(2, bookId);
            createTransaction.setString(3, dateOfIssue);
            createTransaction.setString(4, dateOfReturn);
            int createCount = createTransaction.executeUpdate();
            SQLWarning queryWarning = createTransaction.getWarnings();
            while(queryWarning != null){
                System.err.println("Query warning: " + queryWarning);
            }
            ResultSet rs = createTransaction.getGeneratedKeys();
            while(rs.next()){
                transactionId = rs.getInt(1);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            createTransaction.close();
        }

        return transactionId;
    }
}
