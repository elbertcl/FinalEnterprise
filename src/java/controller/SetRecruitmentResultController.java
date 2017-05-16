/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Activity_Log;
import model.Applicant;
import model.HRM;
import model.HibernateUtil;
import model.Interview;
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

public class SetRecruitmentResultController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("set_recruitment_result");
        String action = null;
        Integer applicant_id = null;
        HttpSession sample = hsr.getSession();
        
        if (sample.getAttribute("currentHRM_name") == null) {
            mv = new ModelAndView("login_hrm");
            mv.addObject("message", "Please do login first.");
            return mv;
        }
        
        // View List
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            List <Applicant> applicantsList = session.createQuery("from Applicant where recruitment_status='FinishedInterview'").list();
            List <Test_Answer> answerList = new ArrayList();
            List <Test_Question> questionList = new ArrayList();
            
            mv.addObject("applicantslist", applicantsList);
            answerList.addAll(session.createQuery("from Test_Answer").list());
            for(int i=0; i<answerList.size(); i++){
                questionList.addAll(session.createQuery("from Test_Question where question_id=:question_id").setParameter("question_id", answerList.get(i).getQuestion_id()).list());
            }
            mv.addObject("answerlist", answerList);
            mv.addObject("questionlist", questionList);

            session.getTransaction().commit();

        } catch(HibernateException e) {
            e.printStackTrace();
        }
        
        // Actions Controller
        if (hsr.getParameterMap().containsKey("action")) {
            action = hsr.getParameter("action");
        }
        if (hsr.getParameterMap().containsKey("applicant_id")) {
            applicant_id = Integer.parseInt(hsr.getParameter("applicant_id"));
        }
        
        if("Accept".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Calendar cal = Calendar.getInstance();
            Date currentDate = cal.getTime();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query = session.createQuery("insert into Employee(employee_name, phone_number, email, date_of_birth, gender, marital_status, position_id, employee_start_date) "
                    + "select a.applicant_name, a.phone_number, a.email, a.date_of_birth, a.gender, a.marital_status, a.position_id, :currentDate from Applicant a where a.applicant_id=:applicant_id").setParameter("applicant_id", applicant_id).setParameter("currentDate", currentDate);
            query.executeUpdate();
            
            Query query2 = session.createQuery("delete from Applicant where applicant_id=:applicant_id").setParameter("applicant_id", applicant_id);
            query2.executeUpdate();
            
            Activity_Log a=new Activity_Log();
            a.setUser_name((String) sample.getAttribute("currentHRM_name"));
            a.setActivity_log_desc("Accepted applicant ID " + applicant_id + " as employee in Set Recruitment Result page");
            a.setUser_role("HRM");
            a.setDatetime(cal.getTime());
            session.save(a);
            
            session.getTransaction().commit();
            
            mv = new ModelAndView("redirect:set_recruitment_result.htm?invalid=acceptsuccess");
        }
        else if("Reject".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query = session.createQuery("delete from Applicant where applicant_id=:applicant_id").setParameter("applicant_id", applicant_id);
            query.executeUpdate();
            
            Activity_Log a=new Activity_Log();
            a.setUser_name((String) sample.getAttribute("currentHRM_name"));
            a.setActivity_log_desc("Rejected applicant ID " + applicant_id + " in Set Recruitment Result page");
            a.setUser_role("HRM");
            Calendar cal = Calendar.getInstance();
            a.setDatetime(cal.getTime());
            session.save(a);
            
            session.getTransaction().commit();
            
            mv = new ModelAndView("redirect:set_recruitment_result.htm?invalid=rejectsuccess");
        }
            
        return mv;
    }
    
}