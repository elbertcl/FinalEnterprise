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

public class EmployeeBenefitController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("employee_benefit");
        HttpSession sample = hsr.getSession();
        
        // View List
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            List <Employee> employeeList = session.createQuery("from Employee").list();
            List <Benefit> benefitList = new ArrayList();
            for(int i=0; i<employeeList.size(); i++){
                benefitList.addAll(session.createQuery("from Benefit where position_id=:position_id").setParameter("position_id", employeeList.get(i).getPosition_id()).list());
            }
            List <Position> positionList = new ArrayList();
            for(int i=0; i<employeeList.size(); i++){
                positionList.addAll(session.createQuery("from Position where position_id=:position_id").setParameter("position_id", employeeList.get(i).getPosition_id()).list());
            }
            List <Division> divisionList = new ArrayList();
            for(int i=0; i<positionList.size(); i++){
                divisionList.addAll(session.createQuery("from Division where division_id=:division_id").setParameter("division_id", positionList.get(i).getDivision_id()).list());
            }
            
            mv.addObject("employeelist", employeeList);
            mv.addObject("benefitlist", benefitList);
            mv.addObject("positionlist", positionList);
            mv.addObject("divisionlist", divisionList);
            
            session.getTransaction().commit();

        } catch(HibernateException e) {
            e.printStackTrace();
        }
            
        return mv;
    }
    
}