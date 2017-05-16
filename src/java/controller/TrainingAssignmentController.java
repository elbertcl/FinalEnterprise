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
import model.Division;
import model.Employee;
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

public class TrainingAssignmentController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("training_assignment");
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
            
            List <Employee> employeeList = session.createQuery("from Employee where training_id is null").list();
            List <Training> trainingList = session.createQuery("from Training").list();
            List <Position> positionList = new ArrayList();
            for(int i=0; i<employeeList.size(); i++){
                positionList.addAll(session.createQuery("from Position where position_id=:position_id").setParameter("position_id", employeeList.get(i).getPosition_id()).list());
            }
            List <Division> divisionList = new ArrayList();
            for(int i=0; i<positionList.size(); i++){
                divisionList.addAll(session.createQuery("from Division where division_id=:division_id").setParameter("division_id", positionList.get(i).getDivision_id()).list());
            }
            
            mv.addObject("employeelist", employeeList);
            mv.addObject("traininglist", trainingList);
            mv.addObject("positionlist", positionList);
            mv.addObject("divisionlist", divisionList);
            
            session.getTransaction().commit();

        } catch(HibernateException e) {
            e.printStackTrace();
        }
        
        // Actions Controller
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        String action = null;
        Integer training_id = null;
        Integer employee_id = null;
        Date start_time = null;
        
        if (hsr.getParameterMap().containsKey("action")) {
            action = hsr.getParameter("action");
        }
        if (hsr.getParameterMap().containsKey("employee_id")) {
            employee_id = Integer.parseInt(hsr.getParameter("employee_id"));
        }
        if (hsr.getParameterMap().containsKey("training_id")) {
            training_id = Integer.parseInt(hsr.getParameter("training_id"));
            
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
//            Query query = session.createQuery("delete from Training where training_id=:training_id").setParameter("training_id", training_id);
            Training query = (Training) session.createQuery("from Training where training_id = :training_id").setParameter("training_id", training_id).uniqueResult();
            
            Calendar cal = Calendar.getInstance();
            Date currentDate = cal.getTime();
            
            if(currentDate.after(query.getStart_time()))
            {
                mv = new ModelAndView("redirect:training_assignment.htm?invalid=invalidtime");
                return mv;
            }
            
            session.getTransaction().commit();
        }
        
//        if (hsr.getParameterMap().containsKey("start_time")) {
//            start_time = format.parse(hsr.getParameter("start_time"));
//            
//            Calendar cal = Calendar.getInstance();
//            Date currentDate = cal.getTime();
//            
//            if(currentDate.after(start_time))
//            {
//                mv = new ModelAndView("redirect:training_assignment.htm?invalid=invalidtime");
//                return mv;
//            }
//        }
        
        if("Assign".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
//            Query query = session.createQuery("delete from Training where training_id=:training_id").setParameter("training_id", training_id);
            Query query = session.createQuery("update Employee set training_id = :training_id where employee_id = :employee_id");
            query.setParameter("training_id", training_id);
            query.setParameter("employee_id", employee_id);
            query.executeUpdate();
            
            Activity_Log a=new Activity_Log();
            a.setUser_name((String) sample.getAttribute("currentHRM_name"));
            a.setActivity_log_desc("Assigned employee ID " + employee_id  + " to training ID " + training_id + " in Employee Training Assignment page");
            a.setUser_role("HRM");
            Calendar cal = Calendar.getInstance();
            a.setDatetime(cal.getTime());
            session.save(a);
            
            session.getTransaction().commit();
            
            mv = new ModelAndView("redirect:training_assignment.htm?invalid=assignsuccess");
        }
            
        return mv;
    }
    
}