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
@Table(name = "activity_log")
public class Activity_Log implements java.io.Serializable {
     private Integer activity_log_id;
     private String user_name;
     private String role;
     private Date datetime;
     private String desc;

    public Activity_Log() {
    }

    public Activity_Log(Integer activity_log_id, String user_name, String role, Date datetime, String desc) {
        this.activity_log_id = activity_log_id;
        this.user_name = user_name;
        this.role = role;
        this.datetime = datetime;
        this.desc = desc;
    }
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="activity_log_id", unique = true, nullable = false)
    public Integer getActivity_log_id() {
        return activity_log_id;
    }

    public void setActivity_log_id(Integer activity_log_id) {
        this.activity_log_id = activity_log_id;
    }

    @Column(name="user_name")
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Column(name="role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name="datetime", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Column(name="desc")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
}


