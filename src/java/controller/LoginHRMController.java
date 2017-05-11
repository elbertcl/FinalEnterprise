/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HRM;
import model.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class LoginHRMController implements Controller{
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("login_hrm");
        // ModelAndView mv = null;
        String out = "Login Form";
        String username = null;
        String password = null;
        
        if (hsr.getParameterMap().containsKey("username")) {
            username = hsr.getParameter("username");
            out = "Please enter valid username and password";
        }
        if (hsr.getParameterMap().containsKey("password")) {
            password = hsr.getParameter("password");
            out = "Please enter valid username and password";
        }
        
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            String sql = " from HRM where hrm_username='"+username+"' and hrm_password='"+password+"'";
            
            Query query = session.createQuery(sql);
            mv.addObject("message", query.getQueryString());
            query.setParameter("username", username);
            query.setParameter("password", password);
            
            List list = query.list(); // PROBLEM
//            List<HRM> list = session.createQuery(sql).list();
//            list = (HRM)query.uniqueResult();

            mv.addObject("message", out);
            
            if(list.isEmpty())
            {
                // Go back to the Login screen (username - password combination not found)
                mv = new ModelAndView("login_hrm");
            }
            else{
                Cookie cookie = new Cookie("username",username);
		cookie.setMaxAge(60*60); //1 hour
		hsr1.addCookie(cookie);
                mv.addObject("message", "successful");
                //hsr1.sendRedirect("http://localhost:8084/SpringHibernate/hello.htm");
                mv = new ModelAndView("redirect:index.htm");
                //mv = new ModelAndView("hello");
            }
            // mv.addObject("logins", list);
            
            session.getTransaction().commit();
        
        } catch(HibernateException e) {
            e.printStackTrace();
        }
        
        return mv;
    }
    
}