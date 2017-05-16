/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Activity_Log;
import model.Applicant;
import model.HRM;
import model.HibernateUtil;
import model.Position;
import model.Test_Answer;
import model.Test_Question;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class TestQuestionApplicantController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("test_question_applicant");
        HttpSession sample = hsr.getSession();
        Integer currentApplicantID = (Integer) sample.getAttribute("currentApplicant_id");
        
        if (sample.getAttribute("currentApplicant_name") == null) {
            mv = new ModelAndView("login_applicant");
            mv.addObject("message", "Please do login first.");
            return mv;
        }
        
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Applicant a = (Applicant) session.createQuery("from Applicant where applicant_id=:applicant_id").setParameter("applicant_id", currentApplicantID).uniqueResult();
            if(a.getRecruitment_status().equals("TestQuestionDone"))
            {
                mv.addObject("testdone", "You have finished the test, please log out and wait for further notice regarding your interview time.");
                return mv;
            }
            
            List <Test_Question> testquestionlist = session.createQuery("from Test_Question").list();
            mv.addObject("testquestionlist", testquestionlist);
            
//            mv = new ModelAndView("login_hrm");
            session.getTransaction().commit();

        } catch(HibernateException e) {
            e.printStackTrace();
        }
        
        String action = null;
        Integer applicant_id = null;
        Integer[] question_id = null;
        
        // Actions Controller
        if (hsr.getParameterMap().containsKey("action")) {
            action = hsr.getParameter("action");
        }
        if (hsr.getParameterMap().containsKey("applicant_id")) {
            applicant_id = Integer.parseInt(hsr.getParameter("applicant_id"));
        }
//        if (hsr.getParameterMap().containsKey("question_id")) {
            String question_id_string[] = hsr.getParameterValues("question_id");
//        }
//        if (hsr.getParameterMap().containsKey("answer")) {
            String answer[] = hsr.getParameterValues("answer");
//        }
        
        if(question_id_string != null)
        {
            question_id = new Integer[question_id_string.length];

            for (int i = 0; i < question_id_string.length; i++) {
                try {
                    question_id[i] = Integer.parseInt(question_id_string[i]);
                } catch (NumberFormatException nfe) {
                    //NOTE: write something here if you need to recover from formatting errors
                };
            }
        }
        
        if("Submit".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query = session.createQuery("update Applicant set recruitment_status = 'TestQuestionDone' where applicant_id = :applicant_id");
            query.setParameter("applicant_id", applicant_id);
            query.executeUpdate();
            
            Test_Answer e=new Test_Answer();
            for (int i = 0; i < question_id_string.length; i++) {
                e=new Test_Answer();
                e.setApplicant_id(applicant_id);
                e.setQuestion_id(question_id[i]);
                e.setAnswer(answer[i]);
                session.save(e);
            }
            
            Activity_Log a=new Activity_Log();
            a.setUser_name((String) sample.getAttribute("currentApplicant_name"));
            a.setActivity_log_desc("Applicant ID " + applicant_id + " finished test questions in Applicant Test Question page");
            a.setUser_role("Applicant");
            Calendar cal = Calendar.getInstance();
            a.setDatetime(cal.getTime());
            session.save(a);
            
            session.getTransaction().commit();
            
            mv = new ModelAndView("redirect:test_question_applicant.htm?invalid=submitsuccess");
        }
            
        return mv;
    }
    
}