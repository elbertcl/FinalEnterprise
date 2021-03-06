/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.HRM;
import model.HibernateUtil;
import model.Login_Log;
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

public class LogoutHRMController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        HttpSession sample = hsr.getSession();
        Login_Log e=new Login_Log();
        e.setUser_name((String) sample.getAttribute("currentHRM_name"));
        e.setLogin_log_desc("HRM Logged Out");
        e.setUser_role("HRM");
        Calendar cal = Calendar.getInstance();
        e.setDatetime(cal.getTime());
        session.save(e);
        session.getTransaction().commit();
        
        
        if (sample != null) {
//            sample.invalidate();
            sample.removeAttribute("currentHRM_id");
            sample.removeAttribute("currentHRM_username");
//            sample.removeAttribute("currentHRM_password");
            sample.removeAttribute("currentHRM_name");
            sample.removeAttribute("currentHRM_picture");
        }
        
        ModelAndView mv = new ModelAndView("redirect:login_hrm.htm");
        return mv;
    }
    
}