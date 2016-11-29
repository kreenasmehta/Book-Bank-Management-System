package BookBankSystem.util;

import java.util.Date;

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

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getFirstname(){
        return firstname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getLastname(){
        return lastname;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

}
