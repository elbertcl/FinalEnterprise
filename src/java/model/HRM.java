package model;
// Generated Mar 15, 2017 12:52:28 AM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hrm")
public class HRM implements java.io.Serializable {
     private Integer hrm_id;
     private String hrm_username;
     private String hrm_password;
     private String hrm_name;
     private String hrm_picture;

    public HRM() {
    }

    public HRM(Integer hrm_id, String hrm_username, String hrm_password, String hrm_name, String hrm_picture) {
        this.hrm_id = hrm_id;
        this.hrm_username = hrm_username;
        this.hrm_password = hrm_password;
        this.hrm_name = hrm_name;
        this.hrm_picture = hrm_picture;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="hrm_id", unique = true, nullable = false)
    public Integer getHrm_id() {
        return this.hrm_id;
    }
    
    public void setHrm_id(Integer hrm_id) {
        this.hrm_id = hrm_id;
    }
    
    @Column(name="hrm_username")
    public String getHrm_username() {
        return this.hrm_username;
    }
    
    public void setHrm_username(String hrm_username) {
        this.hrm_username = hrm_username;
    }
    
    @Column(name="hrm_password")
    public String getHrm_password() {
        return this.hrm_password;
    }
    
    public void setHrm_password(String hrm_password) {
        this.hrm_password = hrm_password;
    }
    
    @Column(name="hrm_name")
    public String getHrm_name() {
        return this.hrm_name;
    }
    
    public void setHrm_name(String hrm_name) {
        this.hrm_name = hrm_name;
    }
    
    @Column(name="hrm_picture")
    public String getHrm_picture() {
        return hrm_picture;
    }

    public void setHrm_picture(String hrm_picture) {
        this.hrm_picture = hrm_picture;
    }
}


