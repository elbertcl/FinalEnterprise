package model;
// Generated Mar 15, 2017 12:52:28 AM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "division")
public class Division implements java.io.Serializable {
     private Integer division_id;
     private String division_name;

    public Division() {
    }

    public Division(Integer division_id, String division_name) {
        this.division_id = division_id;
        this.division_name = division_name;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="division_id", unique = true, nullable = false)
    public Integer getDivision_id() {
        return division_id;
    }

    public void setDivision_id(Integer division_id) {
        this.division_id = division_id;
    }

    @Column(name="division_name")
    public String getDivision_name() {
        return division_name;
    }

    public void setDivision_name(String division_name) {
        this.division_name = division_name;
    }
    
}


