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

public class ManageTestQuestionsController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("manage_test_questions");
        String action = null;
        Integer question_id = null;
        String question = null;
        HttpSession sample = hsr.getSession();
        
        if (sample.getAttribute("currentHRM_name") == null) {
            mv = new ModelAndView("login_hrm");
            mv.addObject("message", "Please do login first.");
            return mv;
        }
        
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            List <Test_Question> testquestionlist = session.createQuery("from Test_Question").list();
            mv.addObject("testquestionlist", testquestionlist);
            
//            mv = new ModelAndView("login_hrm");
            session.getTransaction().commit();

        } catch(HibernateException e) {
            e.printStackTrace();
        }
        
        // Actions Controller
        if (hsr.getParameterMap().containsKey("action")) {
            action = hsr.getParameter("action");
        }
        if (hsr.getParameterMap().containsKey("question_id")) {
            question_id = Integer.parseInt(hsr.getParameter("question_id"));
        }
        if (hsr.getParameterMap().containsKey("question")) {
            question = hsr.getParameter("question");
        }
        
        if("Delete".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query = session.createQuery("delete from Test_Question where question_id=:question_id").setParameter("question_id", question_id);
            query.executeUpdate();
            
            Activity_Log a=new Activity_Log();
            a.setUser_name((String) sample.getAttribute("currentHRM_name"));
            a.setActivity_log_desc("Deleted question ID " + question_id  + " in Manage Test Questions page");
            a.setUser_role("HRM");
            Calendar cal = Calendar.getInstance();
            a.setDatetime(cal.getTime());
            session.save(a);
            
            session.getTransaction().commit();
            
            mv = new ModelAndView("redirect:manage_test_questions.htm?invalid=deletesuccess");
        }
        else if("Add".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
//            Query query = session.createQuery("delete from Test_Question where question_id=:question_id").setParameter("question_id", question_id);
//            Query query = session.createQuery("insert into Test_Question (question) select t.question from Test_Question t");
//            query.executeUpdate();
            
            Test_Question e=new Test_Question();
            e.setQuestion(question);
            session.save(e);
            
            Activity_Log a=new Activity_Log();
            a.setUser_name((String) sample.getAttribute("currentHRM_name"));
            a.setActivity_log_desc("Added new question in Manage Test Questions page");
            a.setUser_role("HRM");
            Calendar cal = Calendar.getInstance();
            a.setDatetime(cal.getTime());
            session.save(a);
            
            session.getTransaction().commit();
            
            mv = new ModelAndView("redirect:manage_test_questions.htm?invalid=addsuccess");
        }
        else if("Edit".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query = session.createQuery("update Test_Question set question = :question where question_id = :question_id");
            query.setParameter("question_id", question_id);
            query.setParameter("question", question);
            query.executeUpdate();
            
            Activity_Log a=new Activity_Log();
            a.setUser_name((String) sample.getAttribute("currentHRM_name"));
            a.setActivity_log_desc("Edited question ID " + question_id  + " in Manage Test Questions page");
            a.setUser_role("HRM");
            Calendar cal = Calendar.getInstance();
            a.setDatetime(cal.getTime());
            session.save(a);
            
            session.getTransaction().commit();
            
            mv = new ModelAndView("redirect:manage_test_questions.htm?invalid=editsuccess");
        }
            
        return mv;
    }
    
}