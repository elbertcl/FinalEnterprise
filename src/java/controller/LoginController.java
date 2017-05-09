/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


//code 1
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Sera
 */


//code 1
public class LoginController implements Controller{
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        //ModelAndView mv = new ModelAndView("hello");
        ModelAndView mv = null;
        String out = "Login Form";
        String username = null;
        String password = null;
        
        if (hsr.getParameterMap().containsKey("username")) {
            username = hsr.getParameter("username");
            out = "Please enter valid username and password";
        }
        if (hsr.getParameterMap().containsKey("pwd")) {
            password = hsr.getParameter("pwd");
            out = "Please enter valid username and password";
        }
        
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
 
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            String sql = " from Users where uname=:username and pass=:password";
            
            Query query = session.createQuery(sql);
            query.setParameter("username", username);
            query.setParameter("password", password);
            
            List list = query.list();
            if(query.list().isEmpty())
            {
                mv = new ModelAndView("loginform");
                mv.addObject("message", out);
            }
            else{
                
                Cookie cookie = new Cookie("username",username);
		cookie.setMaxAge(60*60); //1 hour
		hsr1.addCookie(cookie);
                
                //hsr1.sendRedirect("http://localhost:8084/SpringHibernate/hello.htm");
                mv = new ModelAndView("redirect:hello.htm");
                //mv = new ModelAndView("hello");
            }
            mv.addObject("logins",list);
            
            session.getTransaction().commit();
        
        } catch(HibernateException e) {
            e.printStackTrace();
        }
        
        return mv;
    }
    
}