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

public class ManagePositionController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("manage_position");
        HttpSession sample = hsr.getSession();
        
        // View List
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            List <Division> divisionList = session.createQuery("from Division").list();
            List <Position> positionList = session.createQuery("from Position").list();
            List <Division> divisionforpositionList = new ArrayList();
            for(int i=0; i<positionList.size(); i++){
                divisionforpositionList.addAll(session.createQuery("from Division where division_id=:division_id").setParameter("division_id", positionList.get(i).getDivision_id()).list());
            }
            
            mv.addObject("positionlist", positionList);
            mv.addObject("divisionlist", divisionList);
            mv.addObject("divisionforpositionlist", divisionforpositionList);
            
            session.getTransaction().commit();

        } catch(HibernateException e) {
            e.printStackTrace();
        }
        
        // Actions Controller
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String action = null;
        Integer division_id = null;
        Integer position_id = null;
        String division_name = null;
        String position_name = null;
        
        if (hsr.getParameterMap().containsKey("action")) {
            action = hsr.getParameter("action");
        }
        if (hsr.getParameterMap().containsKey("division_name")) {
            division_name = hsr.getParameter("division_name");
        }
        if (hsr.getParameterMap().containsKey("position_name")) {
            position_name = hsr.getParameter("position_name");
        }
        if (hsr.getParameterMap().containsKey("division_id")) {
            division_id = Integer.parseInt(hsr.getParameter("division_id"));
        }
        if (hsr.getParameterMap().containsKey("position_id")) {
            position_id = Integer.parseInt(hsr.getParameter("position_id"));
        }
        
        if("Delete Division".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query = session.createQuery("delete from Division where division_id=:division_id");
            query.setParameter("division_id", division_id);
            query.executeUpdate();
            
            Activity_Log a=new Activity_Log();
            a.setUser_name((String) sample.getAttribute("currentHRM_name"));
            a.setActivity_log_desc("Deleted division ID " + division_id  + " in Manage Company's Position page");
            a.setUser_role("HRM");
            Calendar cal = Calendar.getInstance();
            a.setDatetime(cal.getTime());
            session.save(a);
            
            session.getTransaction().commit();
            
            mv = new ModelAndView("redirect:manage_position.htm?invalid=deletedivisionsuccess");
        }
        else if("Update Division".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query2 = (Query) session.createQuery("from Division where division_name = :division_name").setParameter("division_name", division_name);
            if(!query2.list().isEmpty())
            {
                mv = new ModelAndView("redirect:manage_position.htm?invalid=divisionnameexist");
                return mv;
            }
            else
            {
                Query query = session.createQuery("update Division set division_name = :division_name where division_id = :division_id");
                query.setParameter("division_id", division_id);
                query.setParameter("division_name", division_name);
                query.executeUpdate();

                Activity_Log a=new Activity_Log();
                a.setUser_name((String) sample.getAttribute("currentHRM_name"));
                a.setActivity_log_desc("Updated division ID " + division_id  + "'s name in Manage Company's Position page");
                a.setUser_role("HRM");
                Calendar cal = Calendar.getInstance();
                a.setDatetime(cal.getTime());
                session.save(a);

                session.getTransaction().commit();

                mv = new ModelAndView("redirect:manage_position.htm?invalid=updatedivisionsuccess");
            }
        }
        
        else if("Delete Position".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query = session.createQuery("delete from Position where position_id=:position_id");
            query.setParameter("position_id", position_id);
            query.executeUpdate();
            
            Activity_Log a=new Activity_Log();
            a.setUser_name((String) sample.getAttribute("currentHRM_name"));
            a.setActivity_log_desc("Deleted position ID " + position_id  + " in Manage Company's Position page");
            a.setUser_role("HRM");
            Calendar cal = Calendar.getInstance();
            a.setDatetime(cal.getTime());
            session.save(a);
            
            session.getTransaction().commit();
            
            mv = new ModelAndView("redirect:manage_position.htm?invalid=deletepositionsuccess");
        }
        else if("Update Position".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query2 = (Query) session.createQuery("from Position where position_name = :position_name").setParameter("position_name", position_name);
            if(!query2.list().isEmpty())
            {
                mv = new ModelAndView("redirect:manage_position.htm?invalid=positionnameexist");
                return mv;
            }
            else
            {
                Query query = session.createQuery("update Position set position_name = :position_name where position_id = :position_id");
                query.setParameter("position_id", position_id);
                query.setParameter("position_name", position_name);
                query.executeUpdate();

                Activity_Log a=new Activity_Log();
                a.setUser_name((String) sample.getAttribute("currentHRM_name"));
                a.setActivity_log_desc("Updated position ID " + position_id  + "'s name in Manage Company's Position page");
                a.setUser_role("HRM");
                Calendar cal = Calendar.getInstance();
                a.setDatetime(cal.getTime());
                session.save(a);

                session.getTransaction().commit();

                mv = new ModelAndView("redirect:manage_position.htm?invalid=updatepositionsuccess");
            }
        }
        
        else if("Add Division".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query2 = (Query) session.createQuery("from Division where division_name = :division_name").setParameter("division_name", division_name);
            if(!query2.list().isEmpty())
            {
                mv = new ModelAndView("redirect:manage_position.htm?invalid=positionnameexist");
                return mv;
            }
            else
            {
                Division e = new Division();
                e.setDivision_name(division_name);
                session.save(e);

                Activity_Log a=new Activity_Log();
                a.setUser_name((String) sample.getAttribute("currentHRM_name"));
                a.setActivity_log_desc("Added division " + division_name  + " in Manage Company's Position page");
                a.setUser_role("HRM");
                Calendar cal = Calendar.getInstance();
                a.setDatetime(cal.getTime());
                session.save(a);

                session.getTransaction().commit();

                mv = new ModelAndView("redirect:manage_position.htm?invalid=adddivisionsuccess");
            }
        }
        else if("Add Position".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query2 = (Query) session.createQuery("from Position where position_name = :position_name").setParameter("position_name", position_name);
            if(!query2.list().isEmpty())
            {
                mv = new ModelAndView("redirect:manage_position.htm?invalid=positionnameexist");
                return mv;
            }
            else
            {
                Position e = new Position();
                e.setPosition_name(position_name);
                e.setDivision_id(division_id);
                e.setMonthly_dismissal_fee(0);
                session.save(e);
                
                Position query3 = (Position) session.createQuery("from Position where position_name = :position_name").setParameter("position_name", position_name).uniqueResult();
                Benefit b = new Benefit();
                b.setPosition_id(query3.getPosition_id());
                b.setBenefit_desc("To be defined.");
                session.save(b);

                Activity_Log a=new Activity_Log();
                a.setUser_name((String) sample.getAttribute("currentHRM_name"));
                a.setActivity_log_desc("Added position " + position_name  + " as child of division ID " + division_id + " in Manage Company's Position page");
                a.setUser_role("HRM");
                Calendar cal = Calendar.getInstance();
                a.setDatetime(cal.getTime());
                session.save(a);

                session.getTransaction().commit();

                mv = new ModelAndView("redirect:manage_position.htm?invalid=addpositionsuccess");
            }
        }
        
            
        return mv;
    }
    
}