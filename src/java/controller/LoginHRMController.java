/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

public class LoginHRMController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        ModelAndView mv = new ModelAndView("login_hrm");
        String out = null;
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
            session.beginTransaction();
            
            String sql = "from HRM where hrm_username=:username and hrm_password=:password";
            
            Query query = session.createQuery(sql);
            query.setParameter("username", username);
            query.setParameter("password", password);
            
            List list = query.list();
            
            if(list.isEmpty())
            {
                // Go back to the Login screen (username - password combination not found)
                mv = new ModelAndView("login_hrm");
                if(password != null && username != null)
                    mv.addObject("message", out);
            }
            else{
                HRM listSession = (HRM) query.uniqueResult();
//                Cookie cookie = new Cookie("username",username);
//		cookie.setMaxAge(60*60); //1 hour
//		hsr1.addCookie(cookie);
                HttpSession sample = hsr.getSession();
                sample.setAttribute("currentHRM_id", listSession.getHrm_id());
                sample.setAttribute("currentHRM_username", listSession.getHrm_username());
                sample.setAttribute("currentHRM_password", listSession.getHrm_password());
                sample.setAttribute("currentHRM_name", listSession.getHrm_name());
                sample.setAttribute("currentHRM_picture", listSession.getHrm_picture());
                
                Login_Log e=new Login_Log();
                e.setUser_name((String) sample.getAttribute("currentHRM_name"));
                e.setLogin_log_desc("HRM Logged In");
                e.setUser_role("HRM");
                Calendar cal = Calendar.getInstance();
                e.setDatetime(cal.getTime());
                session.save(e);
                session.getTransaction().commit();
                
                mv = new ModelAndView("redirect:index.htm");
            }
             mv.addObject("currentHRM", list);
            
            session.getTransaction().commit();
            session.flush();
            session.close();
        
        } catch(HibernateException e) {
            e.printStackTrace();
        }
  
        return mv;
    }
    
}