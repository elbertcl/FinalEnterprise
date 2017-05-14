package model;
// Generated Mar 15, 2017 12:52:28 AM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_answer")
public class Test_Answer implements java.io.Serializable {
     private Integer answer_id;
     private Integer applicant_id;
     private Integer question_id;
     private String answer;

    public Test_Answer() {
    }

    public Test_Answer(Integer answer_id, Integer applicant_id, Integer question_id, String answer) {
        this.answer_id = answer_id;
        this.applicant_id = applicant_id;
        this.question_id = question_id;
        this.answer = answer;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="answer_id", unique = true, nullable = false)
    public Integer getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(Integer answer_id) {
        this.answer_id = answer_id;
    }
    
    @Column(name="applicant_id")
    public Integer getApplicant_id() {
        return applicant_id;
    }

    public void setApplicant_id(Integer applicant_id) {
        this.applicant_id = applicant_id;
    }

    @Column(name="question_id")
    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }
    
    @Column(name="answer")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
}


