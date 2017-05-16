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
import model.Position;
import model.Test_Question;
import model.Training;
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

public class ManageTrainingController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("manage_training");
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
            
            List <Training> trainingList = session.createQuery("from Training").list();
            List <Position> positionList = session.createQuery("from Position").list();
            List <Position> positionListForTraining = new ArrayList();
            for(int i=0; i<trainingList.size(); i++){
                positionListForTraining.addAll(session.createQuery("from Position where position_id=:position_id").setParameter("position_id", trainingList.get(i).getPosition_id()).list());
            }
            mv.addObject("traininglist", trainingList);
            mv.addObject("positionlist", positionList);
            mv.addObject("positionlistfortraining", positionListForTraining);
            
            session.getTransaction().commit();

        } catch(HibernateException e) {
            e.printStackTrace();
        }
        
        // Actions Controller
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        String action = null;
        Integer training_id = null;
        String training_name = null;
        Integer position_id = null;
        Date start_time = null;
        Date end_time = null;
        String pic = null;
        String speaker = null;
        
        
        if (hsr.getParameterMap().containsKey("action")) {
            action = hsr.getParameter("action");
        }
        if (hsr.getParameterMap().containsKey("training_id")) {
            training_id = Integer.parseInt(hsr.getParameter("training_id"));
        }
        if (hsr.getParameterMap().containsKey("training_name")) {
            training_name = hsr.getParameter("training_name");
        }
        if (hsr.getParameterMap().containsKey("position_id")) {
            position_id = Integer.parseInt(hsr.getParameter("position_id"));
        }
        if (hsr.getParameterMap().containsKey("start_time")) {
            start_time = format.parse(hsr.getParameter("start_time"));
            
            Calendar cal = Calendar.getInstance();
            Date currentDate = cal.getTime();
            
            if(currentDate.after(start_time))
            {
                mv = new ModelAndView("redirect:manage_training.htm?invalid=invalidstarttime");
                return mv;
            }
        }
        if (hsr.getParameterMap().containsKey("end_time")) {
            end_time = format.parse(hsr.getParameter("end_time"));
            
            if(start_time.after(end_time))
            {
                mv = new ModelAndView("redirect:manage_training.htm?invalid=invalidendtime");
                return mv;
            }
        }
        if (hsr.getParameterMap().containsKey("pic")) {
            pic = hsr.getParameter("pic");
        }
        if (hsr.getParameterMap().containsKey("speaker")) {
            speaker = hsr.getParameter("speaker");
        }
        
        if("Delete".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query = session.createQuery("delete from Training where training_id=:training_id").setParameter("training_id", training_id);
            query.executeUpdate();
            
            Activity_Log a=new Activity_Log();
            a.setUser_name((String) sample.getAttribute("currentHRM_name"));
            a.setActivity_log_desc("Deleted training ID " + training_id  + " in Manage Training page");
            a.setUser_role("HRM");
            Calendar cal = Calendar.getInstance();
            a.setDatetime(cal.getTime());
            session.save(a);
            
            session.getTransaction().commit();
            
            mv = new ModelAndView("redirect:manage_training.htm?invalid=deletesuccess");
        }
        else if("Add".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Training e=new Training();
            e.setTraining_name(training_name);
            e.setEnd_time(end_time);
            e.setPic(pic);
            e.setPosition_id(position_id);
            e.setSpeaker(speaker);
            e.setStart_time(start_time);
            session.save(e);
            
            Activity_Log a=new Activity_Log();
            a.setUser_name((String) sample.getAttribute("currentHRM_name"));
            a.setActivity_log_desc("Added new training in Manage Training page");
            a.setUser_role("HRM");
            Calendar cal = Calendar.getInstance();
            a.setDatetime(cal.getTime());
            session.save(a);
            
            session.getTransaction().commit();
            
            mv = new ModelAndView("redirect:manage_training.htm?invalid=addsuccess");
        }
            
        return mv;
    }
    
}