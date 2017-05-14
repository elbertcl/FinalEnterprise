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
     private String user_role;
     private Date datetime;
     private String activity_log_desc;

    public Activity_Log() {
    }

    public Activity_Log(Integer activity_log_id, String user_name, String user_role, Date datetime, String activity_log_desc) {
        this.activity_log_id = activity_log_id;
        this.user_name = user_name;
        this.user_role = user_role;
        this.datetime = datetime;
        this.activity_log_desc = activity_log_desc;
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

    @Column(name="activity_log_desc")
    public String getActivity_log_desc() {
        return activity_log_desc;
    }

    public void setActivity_log_desc(String activity_log_desc) {
        this.activity_log_desc = activity_log_desc;
    }
    
}


