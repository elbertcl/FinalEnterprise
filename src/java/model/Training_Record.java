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
@Table(name = "training_record")
public class Training_Record implements java.io.Serializable {
     private Integer record_id;
     private String training_name;
     private Date end_time;
     private String training_result;
     private Integer employee_id;

    public Training_Record() {
    }

    public Training_Record(Integer record_id, String training_name, Date end_time, String training_result, Integer employee_id) {
        this.record_id = record_id;
        this.training_name = training_name;
        this.end_time = end_time;
        this.training_result = training_result;
        this.employee_id = employee_id;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="record_id", unique = true, nullable = false)
    public Integer getRecord_id() {
        return record_id;
    }

    public void setRecord_id(Integer record_id) {
        this.record_id = record_id;
    }

    @Column(name="training_name")
    public String getTraining_name() {
        return training_name;
    }

    public void setTraining_name(String training_name) {
        this.training_name = training_name;
    }

    @Column(name="end_time", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    @Column(name="training_result")
    public String getTraining_result() {
        return training_result;
    }

    public void setTraining_result(String training_result) {
        this.training_result = training_result;
    }

    @Column(name="employee_id")
    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }
    
}


