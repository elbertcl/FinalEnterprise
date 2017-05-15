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
@Table(name = "dismiss_record")
public class Dismiss_Record implements java.io.Serializable {
     private Integer dismiss_record_id;
     private String employee_name;
     private String division_name;
     private String position_name;
     private Date datetime;
     private Integer total_dismissal_fee;

    public Dismiss_Record() {
    }

    public Dismiss_Record(Integer dismiss_record_id, String employee_name, String division_name, String position_name, Date datetime, Integer total_dismissal_fee) {
        this.dismiss_record_id = dismiss_record_id;
        this.employee_name = employee_name;
        this.division_name = division_name;
        this.position_name = position_name;
        this.datetime = datetime;
        this.total_dismissal_fee = total_dismissal_fee;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="dismiss_record_id", unique = true, nullable = false)
    public Integer getDismiss_record_id() {
        return dismiss_record_id;
    }

    public void setDismiss_record_id(Integer dismiss_record_id) {
        this.dismiss_record_id = dismiss_record_id;
    }

    @Column(name="employee_name")
    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    @Column(name="division_name")
    public String getDivision_name() {
        return division_name;
    }

    public void setDivision_name(String division_name) {
        this.division_name = division_name;
    }
    
    @Column(name="position_name")
    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    @Column(name="datetime", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Column(name="total_dismissal_fee")
    public Integer getTotal_dismissal_fee() {
        return total_dismissal_fee;
    }

    public void setTotal_dismissal_fee(Integer total_dismissal_fee) {
        this.total_dismissal_fee = total_dismissal_fee;
    }
    
}


