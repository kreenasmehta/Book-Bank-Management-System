package BookBankSystem.dao;

import BookBankSystem.util.Member;

import java.sql.*;


/**
 * Created by kreenamehta on 11/29/16.
 * MemberDAO
 */
public class MemberDAO {

    public MemberDAO(){};

    /**
     * get member by username, password
     * @param connection
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public Member getMemberByCredentials(Connection connection, String username, String password) throws SQLException {
        Member member = new Member();
        PreparedStatement getMemberByCredentials = connection.prepareStatement(
                "SELECT * FROM Member m WHERE m.username=? and m.password=?;"
        );
        SQLWarning warning = getMemberByCredentials.getWarnings();
        while(warning != null){
            System.err.println("Database warning: " + warning);
        }
        try{
            getMemberByCredentials.setString(1, username);
            getMemberByCredentials.setString(2, password);
            ResultSet rs = getMemberByCredentials.executeQuery();
            SQLWarning queryWarning = getMemberByCredentials.getWarnings();
            while(queryWarning != null){
                System.err.println("Query warning: " + queryWarning);
            }
            while(rs.next()){
                member.setId(rs.getInt(1));
                member.setFirstname(rs.getString(2));
                member.setLastname(rs.getString(3));
                member.setAddress(rs.getString(4));
                member.setEmail(rs.getString(5));
                member.setUsername(rs.getString(6));
                member.setPassword(rs.getString(7));
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            getMemberByCredentials.close();
        }
        return member;
    }

    /**
     * returns true if the given username does not already exist in the database, else false
     * @param connection
     * @param username
     * @return
     * @throws SQLException
     */
    public boolean isUsernameUnique(Connection connection, String username) throws SQLException {
        boolean isUnique = false;
        PreparedStatement isUsernameUnique = connection.prepareStatement(
                "SELECT m.id FROM Member m WHERE m.username=?;"
        );
        SQLWarning warning = isUsernameUnique.getWarnings();
        while(warning != null){
            System.err.println("Database warning: " + warning);
        }
        try{
            isUsernameUnique.setString(1, username);
            ResultSet rs = isUsernameUnique.executeQuery();
            SQLWarning queryWarning = isUsernameUnique.getWarnings();
            while(queryWarning != null){
                System.err.println("Query warning: " + queryWarning);
            }
            if(!rs.next()){
                isUnique = true;
            }
        }catch(Exception e){
            e.printStackTrace();;
        }finally {
            isUsernameUnique.close();
        }
        return isUnique;
    }

    /**
     * creates a new member for Book Bank
     * @param connection
     * @param firstname
     * @param lastname
     * @param address
     * @param email
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public int createMember(Connection connection,
                                String firstname,
                                String lastname,
                                String address,
                                String email,
                                String username,
                                String password) throws SQLException {

        int memberId = 0;
        PreparedStatement createMember = connection.prepareStatement(
                "INSERT INTO Member (firstname, lastname, address, email, username, password) " +
                        "VALUES (?,?,?,?,?,?);"
                ,Statement.RETURN_GENERATED_KEYS);
        SQLWarning warning = createMember.getWarnings();
        while(warning != null){
            System.err.println("Database warning: " + warning);
        }
        try{
            createMember.setString(1, firstname);
            createMember.setString(2, lastname);
            createMember.setString(3, address);
            createMember.setString(4, email);
            createMember.setString(5, username);
            createMember.setString(6, password);
            int createCount = createMember.executeUpdate();
            SQLWarning queryWarning = createMember.getWarnings();
            while(queryWarning != null){
                System.err.println("Query warning: " + queryWarning);
            }
            ResultSet rs = createMember.getGeneratedKeys();
            while(rs.next()){
                memberId = rs.getInt(1);

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            createMember.close();
        }
        return memberId;
    }

    public void deleteMembership(Connection connection, int memberId) throws SQLException {
        PreparedStatement deleteMembership = connection.prepareStatement(
                "DELETE FROM Member where id=?;"
        );
        SQLWarning warning = deleteMembership.getWarnings();
        while(warning != null){
            System.err.println("Database warning: " + warning);
        }
        try{
            deleteMembership.setInt(1, memberId);
            int deleteCount = deleteMembership.executeUpdate();
            SQLWarning queryWarning = deleteMembership.getWarnings();
            while(queryWarning != null){
                System.err.println("Query warning: " + queryWarning);
            }
            if(deleteCount != 1){
                throw new Exception("Error in deleting member.");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            deleteMembership.close();
        }
    }
}
