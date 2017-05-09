/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Sera
 */
public class UserController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("user");
        String out = "All User Details";
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
 
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            mv.addObject("users",session.createQuery("from Users").list());
            
            session.getTransaction().commit();
            
        
        } catch(HibernateException e) {
            e.printStackTrace();
        }
        mv.addObject("message", out);
        return mv;
    }
    
}
