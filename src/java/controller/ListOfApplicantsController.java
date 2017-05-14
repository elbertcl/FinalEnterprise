/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Applicant;
import model.HRM;
import model.HibernateUtil;
import model.Position;
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

public class ListOfApplicantsController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("list_of_applicants");
        String action = null;
        Integer applicant_id = null;
        
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            List <Applicant> applicantsList = session.createQuery("from Applicant").list();
            List <Position> positionList = new ArrayList();
            for(int i=0; i<applicantsList.size(); i++){
                positionList.addAll(session.createQuery("from Position where position_id=:position_id").setParameter("position_id", applicantsList.get(i).getPosition_id()).list());
            }
//            mv.addObject("applicantslist", applicantsList.get(0).getPosition_id());
            mv.addObject("applicantslist", applicantsList);
            mv.addObject("positionlist", positionList);
            
//            mv.addObject("applicantslist", session.createQuery("from Applicant").list());
//            mv.addObject("applicantslist", session.createQuery("from Applicant a JOIN Position p ON a.position_id=p.position_id").list());
//            mv.addObject("applicantslist", session.createQuery("from Applicant applicant JOIN applicant.position position").list());
//            mv.addObject("applicantslist", session.createQuery("from Applicant as a, Position as p where a.position_id=p.position_id").list());
//            mv.addObject("applicantslist", session.get(Applicant.class, 1).list());
            
//            mv = new ModelAndView("login_hrm");
            session.getTransaction().commit();

        } catch(HibernateException e) {
            e.printStackTrace();
        }
        
        // Actions Controller
        if (hsr.getParameterMap().containsKey("action")) {
            action = hsr.getParameter("action");
        }
        if (hsr.getParameterMap().containsKey("applicant_id")) {
            applicant_id = Integer.parseInt(hsr.getParameter("applicant_id"));
        }
        
        if("Delete".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query = session.createQuery("delete from Applicant where applicant_id=:applicant_id").setParameter("applicant_id", applicant_id);
            query.executeUpdate();
            
            session.getTransaction().commit();
            
            mv = new ModelAndView("redirect:list_of_applicants.htm");
        }
            
        return mv;
    }
    
}