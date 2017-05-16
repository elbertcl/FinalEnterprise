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

public class ManageEmployeeController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("manage_employee");
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
            
            List <Employee> employeeList = session.createQuery("from Employee").list();
            List <Position> positionList = new ArrayList();
            for(int i=0; i<employeeList.size(); i++){
                positionList.addAll(session.createQuery("from Position where position_id=:position_id").setParameter("position_id", employeeList.get(i).getPosition_id()).list());
            }
            List <Division> divisionList = new ArrayList();
            for(int i=0; i<positionList.size(); i++){
                divisionList.addAll(session.createQuery("from Division where division_id=:division_id").setParameter("division_id", positionList.get(i).getDivision_id()).list());
            }
            List <Position> positionforeditList = session.createQuery("from Position").list();
            
            mv.addObject("employeelist", employeeList);
            mv.addObject("positionlist", positionList);
            mv.addObject("divisionlist", divisionList);
            mv.addObject("positionforeditlist", positionforeditList);
            
            session.getTransaction().commit();

        } catch(HibernateException e) {
            e.printStackTrace();
        }
        
        // Actions Controller
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String action = null;
        String employee_name = null;
        String phone_number = null;
        String email = null;
        Date date_of_birth = null;
        String gender = null;
        String marital_status = null;
        Integer employee_id = null;
        Integer position_id = null;
        
        if (hsr.getParameterMap().containsKey("action")) {
            action = hsr.getParameter("action");
        }
        if (hsr.getParameterMap().containsKey("employee_name")) {
            employee_name = hsr.getParameter("employee_name");
        }
        if (hsr.getParameterMap().containsKey("phone_number")) {
            phone_number = hsr.getParameter("phone_number");
        }
        if (hsr.getParameterMap().containsKey("email")) {
            email = hsr.getParameter("email");
        }
        if (hsr.getParameterMap().containsKey("date_of_birth")) {
            date_of_birth = format.parse(hsr.getParameter("date_of_birth"));
            
            Calendar cal = Calendar.getInstance();
            Date currentDate = cal.getTime();
            
            if(currentDate.before(date_of_birth))
            {
                mv = new ModelAndView("redirect:manage_employee.htm?invalid=invalidbirthdate");
                return mv;
            }
        }
        if (hsr.getParameterMap().containsKey("gender")) {
            gender = hsr.getParameter("gender");
        }
        if (hsr.getParameterMap().containsKey("marital_status")) {
            marital_status = hsr.getParameter("marital_status");
        }
        if (hsr.getParameterMap().containsKey("employee_id")) {
            employee_id = Integer.parseInt(hsr.getParameter("employee_id"));
        }
        if (hsr.getParameterMap().containsKey("position_id")) {
            position_id = Integer.parseInt(hsr.getParameter("position_id"));
        }
        
        if("Update".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query = session.createQuery("update Employee set employee_name = :employee_name, phone_number = :phone_number, email = :email, date_of_birth = :date_of_birth, "
                    + "gender = :gender, marital_status = :marital_status, position_id = :position_id where employee_id = :employee_id");
            query.setParameter("employee_name", employee_name);
            query.setParameter("phone_number", phone_number);
            query.setParameter("email", email);
            query.setParameter("date_of_birth", date_of_birth);
            query.setParameter("gender", gender);
            query.setParameter("marital_status", marital_status);
            query.setParameter("position_id", position_id);
            query.setParameter("employee_id", employee_id);
            query.executeUpdate();
            
            Activity_Log a=new Activity_Log();
            a.setUser_name((String) sample.getAttribute("currentHRM_name"));
            a.setActivity_log_desc("Updated employee ID " + employee_id  + " data in Manage Employee page");
            a.setUser_role("HRM");
            Calendar cal = Calendar.getInstance();
            a.setDatetime(cal.getTime());
            session.save(a);
            
            session.getTransaction().commit();
            
            mv = new ModelAndView("redirect:manage_employee.htm?invalid=updatesuccess");
        }
            
        return mv;
    }
    
}