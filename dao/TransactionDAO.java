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

    public Transaction getTransactionById(Connection connection, int transactionId) throws SQLException {
        Transaction transaction = new Transaction();
        PreparedStatement getTransactionById = connection.prepareStatement(
                "SELECT * FROM Transaction t WHERE t.id=?;"
        );
        SQLWarning warning = getTransactionById.getWarnings();
        while(warning != null){
            System.err.println("Database warning: " + warning);
        }
        try{
            getTransactionById.setInt(1, transactionId);
            ResultSet rs = getTransactionById.executeQuery();
            SQLWarning queryWarning = getTransactionById.getWarnings();
            while(queryWarning != null){
                System.err.println("Query warning: " + queryWarning);
            }
            while (rs.next()){
                transaction.setId(rs.getInt(1));
                transaction.setMemberId(rs.getInt(2));
                transaction.setBookId(rs.getInt(3));
                transaction.setDateOfIssue(rs.getString(4));
                transaction.setDateOfReturn(rs.getString(5));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            getTransactionById.close();
        }
        return transaction;
    }
}
