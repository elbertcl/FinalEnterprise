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

public class ManageInterviewTimeController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("manage_interview_time");
        String action = null;
        Integer applicant_id = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date datetime = null;
        HttpSession sample = hsr.getSession();
        
        // View List
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            List <Applicant> applicantsList = session.createQuery("from Applicant where recruitment_status='TestQuestionDone' OR recruitment_status='OnInterview' ").list();
            List <Interview> interviewList =  session.createQuery("from Interview").list();
            List <Test_Answer> answerList = new ArrayList();
            List <Test_Question> questionList = new ArrayList();
            
//            for(int i=0; i<applicantsList.size(); i++){
//                if(applicantsList.get(i).getInterview_id() == null)
//                {
//                    interviewList.addAll(session.createQuery("from Interview where interview_id=:interview_id").setParameter("interview_id", 1).list());
//                }
//                else
//                {
//                    interviewList.addAll(session.createQuery("from Interview where interview_id=:interview_id").setParameter("interview_id", applicantsList.get(i).getInterview_id()).list());
//                }
//            }
            
            mv.addObject("applicantslist", applicantsList);
            mv.addObject("interviewlist", interviewList);
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
        if (hsr.getParameterMap().containsKey("datetime")) {
            datetime = format.parse(hsr.getParameter("datetime"));
            
            Calendar cal = Calendar.getInstance();
            Date currentDate = cal.getTime();
            
            if(currentDate.after(datetime))
            {
                mv = new ModelAndView("redirect:manage_interview_time.htm?invalid=invalidinterviewtime");
                return mv;
            }
        }
        
        if("Submit Time".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query = session.createQuery("update Applicant set recruitment_status = 'OnInterview' where applicant_id=:applicant_id").setParameter("applicant_id", applicant_id);
            query.executeUpdate();
            
            Interview e=new Interview();
            e.setApplicant_id(applicant_id);
            e.setDatetime(datetime);
            e.setInterviewer((String) sample.getAttribute("currentHRM_name"));
            session.save(e);
            
            Activity_Log a=new Activity_Log();
            a.setUser_name((String) sample.getAttribute("currentHRM_name"));
            a.setActivity_log_desc("Submitted interview time for applicant ID " + applicant_id + " in Manage Interview Time page");
            a.setUser_role("HRM");
            Calendar cal = Calendar.getInstance();
            a.setDatetime(cal.getTime());
            session.save(a);
            
            session.getTransaction().commit();
            
            mv = new ModelAndView("redirect:manage_interview_time.htm?invalid=interviewtimesuccess");
        }
        else if("Interview Completed".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query = session.createQuery("delete from Interview where applicant_id=:applicant_id").setParameter("applicant_id", applicant_id);
            query.executeUpdate();
            
            Query query2 = session.createQuery("update Applicant set recruitment_status = 'FinishedInterview' where applicant_id=:applicant_id").setParameter("applicant_id", applicant_id);
            query2.executeUpdate();
            
            Activity_Log a=new Activity_Log();
            a.setUser_name((String) sample.getAttribute("currentHRM_name"));
            a.setActivity_log_desc("Completed interview for applicant ID " + applicant_id  + " in Manage Interview Time page");
            a.setUser_role("HRM");
            Calendar cal = Calendar.getInstance();
            a.setDatetime(cal.getTime());
            session.save(a);
            
            session.getTransaction().commit();
            
            mv = new ModelAndView("redirect:manage_interview_time.htm?invalid=interviewcompletedsuccess");
        }
            
        return mv;
    }
    
}