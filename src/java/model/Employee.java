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
@Table(name = "employee")
public class Employee implements java.io.Serializable {
     private Integer employee_id;
     private String employee_name;
     private String phone_number;
     private String email;
     private Date date_of_birth;
     private Integer training_id;
     private String gender;
     private String marital_status;
     private Integer position_id;
     private Date employee_start_date;

    public Employee() {
    }

    public Employee(Integer employee_id, String employee_name, String phone_number, String email, Date date_of_birth, Integer training_id, String gender, String marital_status, Integer position_id, Date employee_start_date) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.phone_number = phone_number;
        this.email = email;
        this.date_of_birth = date_of_birth;
        this.training_id = training_id;
        this.gender = gender;
        this.marital_status = marital_status;
        this.position_id = position_id;
        this.employee_start_date = employee_start_date;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="employee_id", unique = true, nullable = false)
    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }
    
    @Column(name="employee_name")
    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }
    
    @Column(name="phone_number")
    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    
    @Column(name="email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="date_of_birth")
    @Temporal(TemporalType.DATE)
    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    @Column(name="training_id")
    public Integer getTraining_id() {
        return training_id;
    }

    public void setTraining_id(Integer training_id) {
        this.training_id = training_id;
    }

    @Column(name="gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name="marital_status")
    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    @Column(name="position_id")
    public Integer getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Integer position_id) {
        this.position_id = position_id;
    }
    
    @Column(name="employee_start_date")
    @Temporal(TemporalType.DATE)
    public Date getEmployee_start_date() {
        return employee_start_date;
    }

    public void setEmployee_start_date(Date employee_start_date) {
        this.employee_start_date = employee_start_date;
    }
    
    
}


