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

public class EditAccountController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("edit_account");
        HttpSession sample = hsr.getSession();
        
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            List <HRM> hrmlist = session.createQuery("from HRM").list();
            mv.addObject("hrmlist", hrmlist);
            
//            mv = new ModelAndView("login_hrm");
            session.getTransaction().commit();

        } catch(HibernateException e) {
            e.printStackTrace();
        }
        
        String action = null;
        Integer hrm_id = null;
        String hrm_username = null;
        String hrm_picture = null;
        String oldpassword = null;
        String newpassword = null;
        String repeatpassword = null;
        
        // Actions Controller
        if (hsr.getParameterMap().containsKey("action")) {
            action = hsr.getParameter("action");
        }
        if (hsr.getParameterMap().containsKey("hrm_id")) {
            hrm_id = Integer.parseInt(hsr.getParameter("hrm_id"));
        }
        if (hsr.getParameterMap().containsKey("hrm_username")) {
            hrm_username = hsr.getParameter("hrm_username");
        }
         if (hsr.getParameterMap().containsKey("hrm_picture")) {
            hrm_picture = hsr.getParameter("hrm_picture");
        }
          if (hsr.getParameterMap().containsKey("oldpassword")) {
            oldpassword = hsr.getParameter("oldpassword");
        }
           if (hsr.getParameterMap().containsKey("newpassword")) {
            newpassword = hsr.getParameter("newpassword");
        }
            if (hsr.getParameterMap().containsKey("repeatpassword")) {
            repeatpassword = hsr.getParameter("repeatpassword");
        }
        
        if("Submit New Username".equals(action))
        {
            mv.addObject("test" , "working");
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query2 = (Query) session.createQuery("from HRM where hrm_username = :hrm_username").setParameter("hrm_username", hrm_username);
            if(!query2.list().isEmpty())
            {
                mv = new ModelAndView("redirect:edit_account.htm?invalid=usernameexist");
                return mv;
            }
            else
            {
                Query query = session.createQuery("update HRM set hrm_username = :hrm_username where hrm_id = :hrm_id");
                query.setParameter("hrm_id", hrm_id);
                query.setParameter("hrm_username", hrm_username);
                query.executeUpdate();
                
                sample.setAttribute("currentHRM_username", hrm_username);

                Activity_Log a=new Activity_Log();
                a.setUser_name((String) sample.getAttribute("currentHRM_username"));
                a.setActivity_log_desc("Updated HRM ID " + hrm_id  + "'s username in Edit Account page");
                a.setUser_role("HRM");
                Calendar cal = Calendar.getInstance();
                a.setDatetime(cal.getTime());
                session.save(a);

                session.getTransaction().commit();

                mv = new ModelAndView("redirect:edit_account.htm?invalid=success");
            }
        }
        else if("Submit New Photo".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query = session.createQuery("update HRM set hrm_picture = :hrm_picture where hrm_id = :hrm_id");
            query.setParameter("hrm_id", hrm_id);
            query.setParameter("hrm_picture", hrm_picture);
            query.executeUpdate();

            sample.setAttribute("currentHRM_picture", hrm_picture);
            
            Activity_Log a=new Activity_Log();
            a.setUser_name((String) sample.getAttribute("currentHRM_username"));
            a.setActivity_log_desc("Updated HRM ID " + hrm_id  + "'s profile picture in Edit Account page");
            a.setUser_role("HRM");
            Calendar cal = Calendar.getInstance();
            a.setDatetime(cal.getTime());
            session.save(a);

            session.getTransaction().commit();

            mv = new ModelAndView("redirect:edit_account.htm?invalid=success");
        }
        else if("Submit New Password".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            if(!newpassword.equals(repeatpassword))
            {
                mv = new ModelAndView("redirect:edit_account.htm?invalid=repeatpassword");
                return mv;
            }
            
            Query query2 = (Query) session.createQuery("from HRM where hrm_password = :hrm_password and hrm_username= :hrm_username").setParameter("hrm_username", sample.getAttribute("currentHRM_username")).setParameter("hrm_password", oldpassword);
            if(query2.list().isEmpty())
            {
                mv = new ModelAndView("redirect:edit_account.htm?invalid=oldpassword");
                return mv;
            }
            else
            {
                Query query = session.createQuery("update HRM set hrm_password = :hrm_password where hrm_id = :hrm_id");
                query.setParameter("hrm_id", hrm_id);
                query.setParameter("hrm_password", newpassword);
                query.executeUpdate();

                Activity_Log a=new Activity_Log();
                a.setUser_name((String) sample.getAttribute("currentHRM_username"));
                a.setActivity_log_desc("Updated HRM ID " + hrm_id  + "'s password in Edit Account page");
                a.setUser_role("HRM");
                Calendar cal = Calendar.getInstance();
                a.setDatetime(cal.getTime());
                session.save(a);

                session.getTransaction().commit();

                mv = new ModelAndView("redirect:edit_account.htm?invalid=success");
            }
        }
            
        return mv;
    }
    
}