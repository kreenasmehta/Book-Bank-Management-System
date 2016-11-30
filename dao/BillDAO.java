package BookBankSystem.dao;

import java.sql.*;

/**
 * Created by kreenamehta on 11/30/16.
 * Bill DAO
 */
public class BillDAO {

    public BillDAO(){

    }

    /**
     * create a new bill
     * @param connection
     * @param dateOfBilling
     * @param memberId
     * @param transactionId
     * @param amount
     * @param isPaid
     * @return
     * @throws SQLException
     */
    public int createBill(Connection connection,
                          String dateOfBilling,
                          int memberId,
                          int transactionId,
                          int amount,
                          boolean isPaid) throws SQLException {
        int billId = 0;
        PreparedStatement createBill = connection.prepareStatement(
                "INSERT INTO Bill (dateOfBilling, member_id, transaction_id, amount, paid) " +
                        "VALUES (?,?,?,?,?);"
        , Statement.RETURN_GENERATED_KEYS);
        SQLWarning warning = createBill.getWarnings();
        while(warning != null){
            System.err.println("Database warning: " + warning);
        }
        try{
            createBill.setString(1, dateOfBilling);
            createBill.setInt(2, memberId);
            createBill.setInt(3, transactionId);
            createBill.setInt(4, amount);
            createBill.setBoolean(5, isPaid);
            int createCount = createBill.executeUpdate();
            SQLWarning queryWarning = createBill.getWarnings();
            while(queryWarning != null){
                System.err.println("Query warning: " + queryWarning);
            }
            ResultSet rs = createBill.getGeneratedKeys();
            while(rs.next()){
                billId = rs.getInt(1);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            createBill.close();
        }
        return billId;
    }
}
