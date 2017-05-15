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
@Table(name = "training")
public class Training implements java.io.Serializable {
     private Integer training_id;
     private String training_name;
     private String pic;
     private Date start_time;
     private Date end_time;
     private String speaker;
     private Integer position_id;

    public Training() {
    }

    public Training(Integer training_id, String training_name, String pic, Date start_time, Date end_time, String speaker, Integer position_id) {
        this.training_id = training_id;
        this.training_name = training_name;
        this.pic = pic;
        this.start_time = start_time;
        this.end_time = end_time;
        this.speaker = speaker;
        this.position_id = position_id;
    }

    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="training_id", unique = true, nullable = false)
    public Integer getTraining_id() {
        return training_id;
    }

    public void setTraining_id(Integer training_id) {
        this.training_id = training_id;
    }

    @Column(name="training_name")
    public String getTraining_name() {
        return training_name;
    }

    public void setTraining_name(String training_name) {
        this.training_name = training_name;
    }

    @Column(name="pic")
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Column(name="start_time", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    @Column(name="end_time", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    @Column(name="speaker")
    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    @Column(name="position_id")
    public Integer getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Integer position_id) {
        this.position_id = position_id;
    }
    
}


