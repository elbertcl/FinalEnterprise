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
import model.Training_Record;
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

public class EmployeeOnTrainingController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("employee_on_training");
        HttpSession sample = hsr.getSession();
        
        // View List
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            List <Employee> employeeList = session.createQuery("from Employee where training_id is not null").list();
            List <Training> trainingList = new ArrayList();
            for(int i=0; i<employeeList.size(); i++){
                trainingList.addAll(session.createQuery("from Training where training_id=:training_id").setParameter("training_id", employeeList.get(i).getTraining_id()).list());
            }
            List <Position> positionList = new ArrayList();
            for(int i=0; i<trainingList.size(); i++){
                positionList.addAll(session.createQuery("from Position where position_id=:position_id").setParameter("position_id", trainingList.get(i).getPosition_id()).list());
            }
            
            mv.addObject("employeelist", employeeList);
            mv.addObject("traininglist", trainingList);
            mv.addObject("positionlist", positionList);
            
            session.getTransaction().commit();

        } catch(HibernateException e) {
            e.printStackTrace();
        }
        
        // Actions Controller
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        String action = null;
        Integer training_id = null;
        Integer employee_id = null;
        Date end_time = null;
        
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
            end_time = query.getEnd_time();
            
            Calendar cal = Calendar.getInstance();
            Date currentDate = cal.getTime();
            
            if(currentDate.before(query.getEnd_time()))
            {
                mv = new ModelAndView("redirect:employee_on_training.htm?invalid=invalidtime");
                return mv;
            }
            
            session.getTransaction().commit();
        }
        
        if("Pass".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Calendar cal = Calendar.getInstance();
            Date currentDate = cal.getTime();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Training query2 = (Training) session.createQuery("from Training where training_id = :training_id").setParameter("training_id", training_id).uniqueResult();
            Training_Record e = new Training_Record();
            e.setEmployee_id(employee_id);
            e.setEnd_time(end_time);
            e.setTraining_name(query2.getTraining_name());
            e.setTraining_result("Pass");
            session.save(e);
            
            Query query = session.createQuery("update Employee set training_id = :training_id where employee_id = :employee_id");
            query.setParameter("training_id", null);
            query.setParameter("employee_id", employee_id);
            query.executeUpdate();
            
            Activity_Log a=new Activity_Log();
            a.setUser_name((String) sample.getAttribute("currentHRM_name"));
            a.setActivity_log_desc("Submitted Pass result on employee ID " + employee_id + " in training ID " + training_id + " in Employee On Training page");
            a.setUser_role("HRM");
            a.setDatetime(cal.getTime());
            session.save(a);
            
            session.getTransaction().commit();
            
            mv = new ModelAndView("redirect:employee_on_training.htm?invalid=passtraining");
        }
        else if("Fail".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Training query2 = (Training) session.createQuery("from Training where training_id = :training_id").setParameter("training_id", training_id).uniqueResult();
            Training_Record e = new Training_Record();
            e.setEmployee_id(employee_id);
            e.setEnd_time(end_time);
            e.setTraining_name(query2.getTraining_name());
            e.setTraining_result("Fail");
            session.save(e);
            
            Query query = session.createQuery("update Employee set training_id = :training_id where employee_id = :employee_id");
            query.setParameter("training_id", null);
            query.setParameter("employee_id", employee_id);
            query.executeUpdate();
            
            Activity_Log a=new Activity_Log();
            a.setUser_name((String) sample.getAttribute("currentHRM_name"));
            a.setActivity_log_desc("Submitted Fail result on employee ID " + employee_id + " in training ID " + training_id + " in Employee On Training page");
            a.setUser_role("HRM");
            Calendar cal = Calendar.getInstance();
            a.setDatetime(cal.getTime());
            session.save(a);
            
            session.getTransaction().commit();
            
            mv = new ModelAndView("redirect:employee_on_training.htm?invalid=failtraining");
        }
            
        return mv;
    }
    
}