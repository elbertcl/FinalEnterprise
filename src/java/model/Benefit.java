package model;
// Generated Mar 15, 2017 12:52:28 AM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "benefit")
public class Benefit implements java.io.Serializable {
    private Integer benefit_id;
    private Integer position_id;
    private String benefit_desc;
     
    public Benefit() {
    }

    public Benefit(Integer benefit_id, Integer position_id, String benefit_desc) {
        this.benefit_id = benefit_id;
        this.position_id = position_id;
        this.benefit_desc = benefit_desc;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="benefit_id", unique = true, nullable = false)
    public Integer getBenefit_id() {
        return benefit_id;
    }

    public void setBenefit_id(Integer benefit_id) {
        this.benefit_id = benefit_id;
    }

    @Column(name="position_id")
    public Integer getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Integer position_id) {
        this.position_id = position_id;
    }

    @Column(name="benefit_desc")
    public String getBenefit_desc() {
        return benefit_desc;
    }

    public void setBenefit_desc(String benefit_desc) {
        this.benefit_desc = benefit_desc;
    }
    
}


