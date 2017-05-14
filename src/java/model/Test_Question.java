package model;
// Generated Mar 15, 2017 12:52:28 AM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_question")
public class Test_Question implements java.io.Serializable {
     private Integer question_id;
     private String question;

    public Test_Question() {
    }

    public Test_Question(Integer question_id, String question) {
        this.question_id = question_id;
        this.question = question;
    }
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="question_id", unique = true, nullable = false)
    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }
    
    @Column(name="question")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    
}


