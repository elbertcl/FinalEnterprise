package model;
// Generated Mar 15, 2017 12:52:28 AM by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "login_log")
public class Login_Log implements java.io.Serializable {
     private Integer login_log_id;
     private String user_name;
     private String user_role;
     private Date datetime;
     private String login_log_desc;

    public Login_Log() {
    }

    public Login_Log(Integer login_log_id, String user_name, String user_role, Date datetime, String login_log_desc) {
        this.login_log_id = login_log_id;
        this.user_name = user_name;
        this.user_role = user_role;
        this.datetime = datetime;
        this.login_log_desc = login_log_desc;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="login_log_id", unique = true, nullable = false)
    public Integer getLogin_log_id() {
        return login_log_id;
    }

    public void setLogin_log_id(Integer login_log_id) {
        this.login_log_id = login_log_id;
    }

    @Column(name="user_name")
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Column(name="user_role")
    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    @Column(name="datetime", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Column(name="login_log_desc")
    public String getLogin_log_desc() {
        return login_log_desc;
    }

    public void setLogin_log_desc(String login_log_desc) {
        this.login_log_desc = login_log_desc;
    }
    
}


