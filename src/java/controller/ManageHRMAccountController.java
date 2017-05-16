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
import model.Benefit;
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

public class ManageHRMAccountController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("manage_hrm_account");
        HttpSession sample = hsr.getSession();
        
        if (sample.getAttribute("currentAdmin_name") == null) {
            mv = new ModelAndView("login_admin");
            mv.addObject("message", "Please do login first.");
            return mv;
        }
        
        // View List
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            List <HRM> hrmList = session.createQuery("from HRM").list();
            
            mv.addObject("hrmlist", hrmList);
            
            session.getTransaction().commit();

        } catch(HibernateException e) {
            e.printStackTrace();
        }
        
        // Actions Controller
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String action = null;
        Integer hrm_id = null;
        String hrm_name = null;
        String hrm_password = null;
        String hrm_username = null;
        
        if (hsr.getParameterMap().containsKey("action")) {
            action = hsr.getParameter("action");
        }
        if (hsr.getParameterMap().containsKey("hrm_password")) {
            hrm_password = hsr.getParameter("hrm_password");
        }
        if (hsr.getParameterMap().containsKey("hrm_username")) {
            hrm_username = hsr.getParameter("hrm_username");
        }
        if (hsr.getParameterMap().containsKey("hrm_name")) {
            hrm_name = hsr.getParameter("hrm_name");
        }
        if (hsr.getParameterMap().containsKey("hrm_id")) {
            hrm_id = Integer.parseInt(hsr.getParameter("hrm_id"));
        }
        
        if("Delete".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query = session.createQuery("delete from HRM where hrm_id=:hrm_id");
            query.setParameter("hrm_id", hrm_id);
            query.executeUpdate();
            
            Activity_Log a=new Activity_Log();
            a.setUser_name((String) sample.getAttribute("currentAdmin_username"));
            a.setActivity_log_desc("Deleted HRM ID " + hrm_id  + " in Manage HRM Accounts page");
            a.setUser_role("Admin");
            Calendar cal = Calendar.getInstance();
            a.setDatetime(cal.getTime());
            session.save(a);
            
            session.getTransaction().commit();
            
            mv = new ModelAndView("redirect:manage_hrm_account.htm?invalid=deletesuccess");
        }
        else if("Update".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query2 = (Query) session.createQuery("from HRM where hrm_username = :hrm_username").setParameter("hrm_username", hrm_username);
            if(!query2.list().isEmpty())
            {
                mv = new ModelAndView("redirect:manage_hrm_account.htm?invalid=usernameexist");
                return mv;
            }
            else
            {
                Query query = session.createQuery("update HRM set hrm_username = :hrm_username where hrm_id = :hrm_id");
                query.setParameter("hrm_id", hrm_id);
                query.setParameter("hrm_username", hrm_username);
                query.executeUpdate();

                Activity_Log a=new Activity_Log();
                a.setUser_name((String) sample.getAttribute("currentAdmin_username"));
                a.setActivity_log_desc("Updated HRM ID " + hrm_id  + "'s username in Manage HRM Accounts page");
                a.setUser_role("Admin");
                Calendar cal = Calendar.getInstance();
                a.setDatetime(cal.getTime());
                session.save(a);

                session.getTransaction().commit();

                mv = new ModelAndView("redirect:manage_hrm_account.htm?invalid=updatesuccess");
            }
        }
        else if("Add".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query2 = (Query) session.createQuery("from HRM where hrm_username = :hrm_username").setParameter("hrm_username", hrm_username);
            if(!query2.list().isEmpty())
            {
                mv = new ModelAndView("redirect:manage_hrm_account.htm?invalid=usernameexist");
                return mv;
            }
            else
            {
                HRM e = new HRM();
                e.setHrm_username(hrm_username);
                e.setHrm_password(hrm_password);
                e.setHrm_name(hrm_name);
                e.setHrm_picture("defaultadmin.png");
                session.save(e);

                Activity_Log a=new Activity_Log();
                a.setUser_name((String) sample.getAttribute("currentAdmin_username"));
                a.setActivity_log_desc("Added HRM " + hrm_name  + " account in Manage HRM Accounts page");
                a.setUser_role("Admin");
                Calendar cal = Calendar.getInstance();
                a.setDatetime(cal.getTime());
                session.save(a);

                session.getTransaction().commit();

                mv = new ModelAndView("redirect:manage_hrm_account.htm?invalid=addsuccess");
            }
        }
           
        return mv;
    }
    
}