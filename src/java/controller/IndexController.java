/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Applicant;
import model.Division;
import model.Employee;
import model.HRM;
import model.HibernateUtil;
import model.Position;
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

public class IndexController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        HttpSession sample = hsr.getSession();
        ModelAndView mv = new ModelAndView("index");
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        if (sample.getAttribute("currentHRM_name") == null) {
            mv = new ModelAndView("login_hrm");
            mv.addObject("message", "Please do login first.");
            return mv;
        }
        
        try {
            mv = new ModelAndView("index");
            session.beginTransaction();

            mv.addObject("numberOfApplicants", ((Long)session.createQuery("select count(*) from Applicant").uniqueResult()).intValue());
            
            mv.addObject("numberOfEmployees", ((Long)session.createQuery("select count(*) from Employee").uniqueResult()).intValue());
            
            mv.addObject("numberOfTraining", ((Long)session.createQuery("select count(*) from Training").uniqueResult()).intValue());
            
            mv.addObject("numberOfIdle", ((Long)session.createQuery("select count(*) from Employee where training_id is null").uniqueResult()).intValue());
            
            List <Employee> employeeList = session.createQuery("from Employee").list();
            List <Training> trainingList = session.createQuery("from Training").list();
            List <Division> divisionList = session.createQuery("from Division").list();
            List <Applicant> applicantList = session.createQuery("from Applicant").list();
            
            // Chart 1 - Number of Employee per Division
            List <Position> positionforemployeeList = new ArrayList();
            ArrayList divisionforemployeeList = new ArrayList();
            List numberOfEmployee = new ArrayList();
            
            for(int i=0; i<employeeList.size(); i++){
                positionforemployeeList.addAll(session.createQuery("from Position where position_id=:position_id").setParameter("position_id", employeeList.get(i).getPosition_id()).list());
            }
            for(int i=0; i<positionforemployeeList.size(); i++){
                divisionforemployeeList.add((String) session.createQuery("select division_name from Division where division_id=:division_id").setParameter("division_id", positionforemployeeList.get(i).getDivision_id()).uniqueResult());
            }
            for(int i=0; i<divisionList.size(); i++){
                numberOfEmployee.add(Collections.frequency(divisionforemployeeList, divisionList.get(i).getDivision_name()));
            }
            
            mv.addObject("divisionList" , divisionList);
            mv.addObject("numberOfEmployeeChart" , numberOfEmployee);
            mv.addObject("divisionforemployeeList" , divisionforemployeeList);
            
            // Chart 2 - Number of Training Allocated per Division
            List <Position> positionfortrainingList = new ArrayList();
            ArrayList divisionfortrainingList = new ArrayList();
            List numberOfTraining = new ArrayList();
            
            for(int i=0; i<trainingList.size(); i++){
                positionfortrainingList.addAll(session.createQuery("from Position where position_id=:position_id").setParameter("position_id", trainingList.get(i).getPosition_id()).list());
            }
            for(int i=0; i<trainingList.size(); i++){
                divisionfortrainingList.add((String) session.createQuery("select division_name from Division where division_id=:division_id").setParameter("division_id", positionfortrainingList.get(i).getDivision_id()).uniqueResult());
            }
            for(int i=0; i<divisionList.size(); i++){
                numberOfTraining.add(Collections.frequency(divisionfortrainingList, divisionList.get(i).getDivision_name()));
            }
            
            mv.addObject("divisionList" , divisionList);
            mv.addObject("numberOfTrainingChart" , numberOfTraining);
            mv.addObject("divisionfortrainingList" , divisionfortrainingList);
            
            // Chart 3 - Number of Applicant Applied per Division
            List <Position> positionforapplicantList = new ArrayList();
            ArrayList divisionforapplicantList = new ArrayList();
            List numberOfApplicant = new ArrayList();
            
            for(int i=0; i<applicantList.size(); i++){
                positionforapplicantList.addAll(session.createQuery("from Position where position_id=:position_id").setParameter("position_id", applicantList.get(i).getPosition_id()).list());
            }
            for(int i=0; i<positionforapplicantList.size(); i++){
                divisionforapplicantList.add((String) session.createQuery("select division_name from Division where division_id=:division_id").setParameter("division_id", positionforapplicantList.get(i).getDivision_id()).uniqueResult());
            }
            for(int i=0; i<divisionList.size(); i++){
                numberOfApplicant.add(Collections.frequency(divisionforapplicantList, divisionList.get(i).getDivision_name()));
            }
            
            mv.addObject("divisionList" , divisionList);
            mv.addObject("numberOfApplicantChart" , numberOfApplicant);
            mv.addObject("divisionforapplicantList" , divisionforapplicantList);
            
            // Chart 4 - Dismissed Employee per Month
            List numberOfDismissed = new ArrayList();
            int year = Calendar.getInstance().get(Calendar.YEAR);
            numberOfDismissed.add(((Long)session.createQuery("select count(*) from Dismiss_Record where MONTH(datetime) = 1 AND YEAR(datetime) = :year").setParameter("year", year).uniqueResult()).intValue());
            numberOfDismissed.add(((Long)session.createQuery("select count(*) from Dismiss_Record where MONTH(datetime) = 2 AND YEAR(datetime) = :year").setParameter("year", year).uniqueResult()).intValue());
            numberOfDismissed.add(((Long)session.createQuery("select count(*) from Dismiss_Record where MONTH(datetime) = 3 AND YEAR(datetime) = :year").setParameter("year", year).uniqueResult()).intValue());
            numberOfDismissed.add(((Long)session.createQuery("select count(*) from Dismiss_Record where MONTH(datetime) = 4 AND YEAR(datetime) = :year").setParameter("year", year).uniqueResult()).intValue());
            numberOfDismissed.add(((Long)session.createQuery("select count(*) from Dismiss_Record where MONTH(datetime) = 5 AND YEAR(datetime) = :year").setParameter("year", year).uniqueResult()).intValue());
            numberOfDismissed.add(((Long)session.createQuery("select count(*) from Dismiss_Record where MONTH(datetime) = 6 AND YEAR(datetime) = :year").setParameter("year", year).uniqueResult()).intValue());
            numberOfDismissed.add(((Long)session.createQuery("select count(*) from Dismiss_Record where MONTH(datetime) = 7 AND YEAR(datetime) = :year").setParameter("year", year).uniqueResult()).intValue());
            numberOfDismissed.add(((Long)session.createQuery("select count(*) from Dismiss_Record where MONTH(datetime) = 8 AND YEAR(datetime) = :year").setParameter("year", year).uniqueResult()).intValue());
            numberOfDismissed.add(((Long)session.createQuery("select count(*) from Dismiss_Record where MONTH(datetime) = 9 AND YEAR(datetime) = :year").setParameter("year", year).uniqueResult()).intValue());
            numberOfDismissed.add(((Long)session.createQuery("select count(*) from Dismiss_Record where MONTH(datetime) = 10 AND YEAR(datetime) = :year").setParameter("year", year).uniqueResult()).intValue());
            numberOfDismissed.add(((Long)session.createQuery("select count(*) from Dismiss_Record where MONTH(datetime) = 11 AND YEAR(datetime) = :year").setParameter("year", year).uniqueResult()).intValue());
            numberOfDismissed.add(((Long)session.createQuery("select count(*) from Dismiss_Record where MONTH(datetime) = 12 AND YEAR(datetime) = :year").setParameter("year", year).uniqueResult()).intValue());
            mv.addObject("numberOfDismissedChart" , numberOfDismissed);
            
            session.getTransaction().commit();

        } catch(HibernateException e) {
            e.printStackTrace();
        }
        
       
        return mv;
    }
    
}