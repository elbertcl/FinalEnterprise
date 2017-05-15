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

public class ManagePositionBenefitController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("manage_position_benefit");
        HttpSession sample = hsr.getSession();
        
        // View List
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            List <Position> positionList = session.createQuery("from Position").list();
            List <Benefit> benefitList = new ArrayList();
            for(int i=0; i<positionList.size(); i++){
                benefitList.addAll(session.createQuery("from Benefit where position_id=:position_id").setParameter("position_id", positionList.get(i).getPosition_id()).list());
            }
            List <Division> divisionList = new ArrayList();
            for(int i=0; i<positionList.size(); i++){
                divisionList.addAll(session.createQuery("from Division where division_id=:division_id").setParameter("division_id", positionList.get(i).getDivision_id()).list());
            }
            
            mv.addObject("benefitlist", benefitList);
            mv.addObject("positionlist", positionList);
            mv.addObject("divisionlist", divisionList);
            
            session.getTransaction().commit();

        } catch(HibernateException e) {
            e.printStackTrace();
        }
        
        String action = null;
        Integer benefit_id = null;
        String benefit_desc = null;
        
        // Actions Controller
        if (hsr.getParameterMap().containsKey("action")) {
            action = hsr.getParameter("action");
        }
        if (hsr.getParameterMap().containsKey("benefit_id")) {
            benefit_id = Integer.parseInt(hsr.getParameter("benefit_id"));
        }
        if (hsr.getParameterMap().containsKey("benefit_desc")) {
            benefit_desc = hsr.getParameter("benefit_desc");
        }
        
        if("Update".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query = session.createQuery("update Benefit set benefit_desc = :benefit_desc where benefit_id = :benefit_id");
            query.setParameter("benefit_id", benefit_id);
            query.setParameter("benefit_desc", benefit_desc);
            query.executeUpdate();
            
            Activity_Log a=new Activity_Log();
            a.setUser_name((String) sample.getAttribute("currentHRM_name"));
            a.setActivity_log_desc("Updated benefit ID " + benefit_id  + " in Manage Position's Benefit page");
            a.setUser_role("HRM");
            Calendar cal = Calendar.getInstance();
            a.setDatetime(cal.getTime());
            session.save(a);
            
            session.getTransaction().commit();
            
            mv = new ModelAndView("redirect:manage_position_benefit.htm?invalid=updatesuccess");
        }
            
        return mv;
    }
    
}