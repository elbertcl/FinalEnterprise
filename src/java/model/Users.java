package model;
// Generated Mar 15, 2017 12:52:28 AM by Hibernate Tools 4.3.1



/**
 * Users generated by hbm2java
 */
public class Users  implements java.io.Serializable {


     private Integer id;
     private String uname;
     private String pass;
     private String email;

    public Users() {
    }

    public Users(String uname, String pass, String email) {
       this.uname = uname;
       this.pass = pass;
       this.email = email;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUname() {
        return this.uname;
    }
    
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getPass() {
        return this.pass;
    }
    
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }




}


