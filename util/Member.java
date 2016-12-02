package BookBankSystem.util;


/**
 * Created by kreenamehta on 11/29/16.
 * Member of the Book Bank System
 */
public class Member {

    private int id;
    private String firstname;
    private String lastname;
    private String address;
    private String email;
    private String username;
    private String password;

    public Member(){

    }

    public Member(int id, String firstname, String lastname,
                  String address, String email,
                  String username, String password){

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /**
     * set member id
     * @param id
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * get member id
     * @return
     */
    public int getId(){
        return id;
    }

    /**
     * set first name
     * @param firstname
     */
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    /**
     * get first name
     * @return
     */
    public String getFirstname(){
        return firstname;
    }

    /**
     * set last name
     * @param lastname
     */
    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    /**
     * get last name
     * @return
     */
    public String getLastname(){
        return lastname;
    }

    /**
     * set address
     * @param address
     */
    public void setAddress(String address){
        this.address = address;
    }

    /**
     * get address
     * @return
     */
    public String getAddress(){
        return address;
    }

    /**
     * set email
     * @param email
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * get email
     * @return
     */
    public String getEmail(){
        return email;
    }

    /**
     * set username
     * @param username
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * get username
     * @return
     */
    public String getUsername(){
        return username;
    }

    /**
     * set password
     * @param password
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * get password
     * @return
     */
    public String getPassword(){
        return password;
    }

}
