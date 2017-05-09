/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Sera
 */
public class helloController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("hello");
        String out = "Dashboard";
        String username = null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
 
            Session session = sessionFactory.openSession();
            
            session.beginTransaction();
            
            Cookie[] c = hsr.getCookies();
            for (int i = 0; i < c.length; i++) {
               Cookie cd = c[i];
               if (cd.getName().equals("username")) {
                username = cd.getValue();
               }
            }
            
            String sql = " from Users where uname=:username";
            
            Query query = session.createQuery(sql);
            query.setParameter("username", username);
            
            List list = query.list();
            mv.addObject("hellos",list);
            session.getTransaction().commit();
        
        } catch(HibernateException e) {
            e.printStackTrace();
        }
        mv.addObject("message", out);
        return mv;

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
