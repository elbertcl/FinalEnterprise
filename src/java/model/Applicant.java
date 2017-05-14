package model;
// Generated Mar 15, 2017 12:52:28 AM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "applicant")
public class Applicant implements java.io.Serializable {
    private Integer applicant_id;
    private String applicant_name;
    private String phone_number;
    private String email;
    private Date register_date;
    private Date date_of_birth;
    private String gender;
    private Integer position_id;
    private String marital_status;
    private String cv;
    private String recruitment_status;
    private String username;
    private String password;
//    private Position position;
//    private String position_name;

    public Applicant() {
    }
    
//    @Transient
//    public String getPosition_name() {
//        return position_name;
//    }
//
//    public void setPosition_name(String position_name) {
//        this.position_name = position_name;
//    }

    public Applicant(Integer applicant_id, String applicant_name, String phone_number, String email, Date register_date, Date date_of_birth, String gender, Integer position_id, String marital_status, String cv, String recruitment_status, String username, String password) {
        this.applicant_id = applicant_id;
        this.applicant_name = applicant_name;
        this.phone_number = phone_number;
        this.email = email;
        this.register_date = register_date;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.position_id = position_id;
        this.marital_status = marital_status;
        this.cv = cv;
        this.recruitment_status = recruitment_status;
        this.username = username;
        this.password = password;
    }
    
//    public Applicant(Integer applicant_id, String applicant_name, String phone_number, String email, Date register_date, Date date_of_birth, String gender, String marital_status, String cv, String recruitment_status, String username, String password, Position position) {
//        this.applicant_id = applicant_id;
//        this.applicant_name = applicant_name;
//        this.phone_number = phone_number;
//        this.email = email;
//        this.register_date = register_date;
//        this.date_of_birth = date_of_birth;
//        this.gender = gender;
//        this.marital_status = marital_status;
//        this.cv = cv;
//        this.recruitment_status = recruitment_status;
//        this.username = username;
//        this.password = password;
//        this.position = position;
//    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="applicant_id", unique = true, nullable = false)
    public Integer getApplicant_id() {
        return applicant_id;
    }

    public void setApplicant_id(Integer applicant_id) {
        this.applicant_id = applicant_id;
    }
    
    @Column(name="position_id")
    public Integer getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Integer position_id) {
        this.position_id = position_id;
    }
    
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="position_id", insertable = false, updatable = false, nullable = false)
//    public Position getPosition() {
//        return position;
//    }
//
//    public void setPosition(Position position) {
//        this.position = position;
//    }
    
    @Column(name="applicant_name")
    public String getApplicant_name() {
        return applicant_name;
    }

    public void setApplicant_name(String applicant_name) {
        this.applicant_name = applicant_name;
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
    
    @Column(name="register_date")
    @Temporal(TemporalType.DATE)
    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    @Column(name="date_of_birth")
    @Temporal(TemporalType.DATE)
    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
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

    @Column(name="cv")
    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    @Column(name="recruitment_status")
    public String getRecruitment_status() {
        return recruitment_status;
    }

    public void setRecruitment_status(String recruitment_status) {
        this.recruitment_status = recruitment_status;
    }

    @Column(name="username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name="password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


