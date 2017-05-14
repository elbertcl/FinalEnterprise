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
@Table(name = "interview")
public class Interview implements java.io.Serializable {
     private Integer interview_id;
     private Integer applicant_id;
     private String interviewer;
     private Date datetime;

    public Interview() {
    }

    public Interview(Integer interview_id, Integer applicant_id, String interviewer, Date datetime) {
        this.interview_id = interview_id;
        this.applicant_id = applicant_id;
        this.interviewer = interviewer;
        this.datetime = datetime;
    }
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="interview_id", unique = true, nullable = false)
    public Integer getInterview_id() {
        return interview_id;
    }

    public void setInterview_id(Integer interview_id) {
        this.interview_id = interview_id;
    }
    
    @Column(name="applicant_id")
    public Integer getApplicant_id() {
        return applicant_id;
    }
    
    public void setApplicant_id(Integer applicant_id) {
        this.applicant_id = applicant_id;
    }
    
    @Column(name="interviewer")
    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }
    
    @Column(name="datetime", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}


