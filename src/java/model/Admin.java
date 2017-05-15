package model;
// Generated Mar 15, 2017 12:52:28 AM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin implements java.io.Serializable {
     private Integer admin_id;
     private String admin_username;
     private String admin_password;

    public Admin() {
    }

    public Admin(Integer admin_id, String admin_username, String admin_password) {
        this.admin_id = admin_id;
        this.admin_username = admin_username;
        this.admin_password = admin_password;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="admin_id", unique = true, nullable = false)
    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

   @Column(name="admin_username")
    public String getAdmin_username() {
        return admin_username;
    }

    public void setAdmin_username(String admin_username) {
        this.admin_username = admin_username;
    }

    @Column(name="admin_password")
    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }
    
}


