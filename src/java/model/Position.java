package model;
// Generated Mar 15, 2017 12:52:28 AM by Hibernate Tools 4.3.1

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "position")
public class Position implements java.io.Serializable {
    private Integer position_id;
    private String position_name;
    private Integer division_id;
    private Integer monthly_dismissal_fee;
//    private List<Applicant> applicant;

    public Position() {
    }

//    public Position(Integer position_id, String position_name, Integer division_id, Integer monthly_dismissal_fee, List<Applicant> applicant) {
//        this.position_id = position_id;
//        this.position_name = position_name;
//        this.division_id = division_id;
//        this.monthly_dismissal_fee = monthly_dismissal_fee;
//        this.applicant = applicant;
//    }
    
    public Position(Integer position_id, String position_name, Integer division_id, Integer monthly_dismissal_fee) {
        this.position_id = position_id;
        this.position_name = position_name;
        this.division_id = division_id;
        this.monthly_dismissal_fee = monthly_dismissal_fee;
    }
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="position_id", unique = true, nullable = false)
    public Integer getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Integer position_id) {
        this.position_id = position_id;
    }
    
//    @OneToMany(fetch= FetchType.EAGER ,targetEntity=Applicant.class, mappedBy="position", cascade=CascadeType.ALL ) 
//    public List<Applicant> getApplicant() {
//        return applicant;
//    }
//
//    public void setApplicant(List<Applicant> applicant) {
//        this.applicant = applicant;
//    }

    @Column(name="position_name")
    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    @Column(name="division_id")
    public Integer getDivision_id() {
        return division_id;
    }

    public void setDivision_id(Integer division_id) {
        this.division_id = division_id;
    }

    @Column(name="monthly_dismissal_fee")
    public Integer getMonthly_dismissal_fee() {
        return monthly_dismissal_fee;
    }

    public void setMonthly_dismissal_fee(Integer monthly_dismissal_fee) {
        this.monthly_dismissal_fee = monthly_dismissal_fee;
    }
    
    
}


