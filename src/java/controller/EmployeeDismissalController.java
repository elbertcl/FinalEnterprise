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
import model.Dismiss_Record;
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

public class EmployeeDismissalController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("employee_dismissal");
        HttpSession sample = hsr.getSession();
        Calendar cal = Calendar.getInstance();
        
        // View List
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            List totalfeeList = new ArrayList();
            Position positionfeeObject = new Position();
            Employee employeefeeObject = new Employee();
            List <Employee> employeeList = session.createQuery("from Employee").list();
            for(int i=0; i<employeeList.size(); i++){
                positionfeeObject = (Position) session.createQuery("from Position where position_id=:position_id").setParameter("position_id", employeeList.get(i).getPosition_id()).uniqueResult();
                employeefeeObject = (Employee) session.createQuery("from Employee where employee_id=:employee_id").setParameter("employee_id", employeeList.get(i).getEmployee_id()).uniqueResult();
                
                totalfeeList.add(differenceInMonths(employeefeeObject.getEmployee_start_date(), cal.getTime()) * positionfeeObject.getMonthly_dismissal_fee());
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
            mv.addObject("positionlist", positionList);
            mv.addObject("divisionlist", divisionList);
            mv.addObject("totalfeelist", totalfeeList);
            
            session.getTransaction().commit();

        } catch(HibernateException e) {
            e.printStackTrace();
        }
        
        Integer total_dismissal_fee = null;
        String action = null;
        String division_name = null;
        String position_name = null;
        String employee_name = null;
        Integer employee_id = null;
        
        if (hsr.getParameterMap().containsKey("action")) {
            action = hsr.getParameter("action");
        }
        if (hsr.getParameterMap().containsKey("total_dismissal_fee")) {
            total_dismissal_fee = Integer.parseInt(hsr.getParameter("total_dismissal_fee"));
        }
        if (hsr.getParameterMap().containsKey("division_name")) {
            division_name = hsr.getParameter("division_name");
        }
        if (hsr.getParameterMap().containsKey("position_name")) {
            position_name = hsr.getParameter("position_name");
        }
        if (hsr.getParameterMap().containsKey("employee_name")) {
            employee_name = hsr.getParameter("employee_name");
        }
        if (hsr.getParameterMap().containsKey("employee_id")) {
            employee_id = Integer.parseInt(hsr.getParameter("employee_id"));
        }
        
        if("Dismiss".equals(action))
        {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Date currentDate = cal.getTime();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Dismiss_Record e = new Dismiss_Record();
            e.setDatetime(currentDate);
            e.setDivision_name(division_name);
            e.setEmployee_name(employee_name);
            e.setPosition_name(position_name);
            e.setTotal_dismissal_fee(total_dismissal_fee);
            session.save(e);
            
            Query query2 = session.createQuery("delete from Employee where employee_id=:employee_id").setParameter("employee_id", employee_id);
            query2.executeUpdate();
            
            Activity_Log a=new Activity_Log();
            a.setUser_name((String) sample.getAttribute("currentHRM_name"));
            a.setActivity_log_desc("Dismissed employee " + employee_name + " in Employee Dismissal page");
            a.setUser_role("HRM");
            a.setDatetime(cal.getTime());
            session.save(a);
            
            session.getTransaction().commit();
            
            mv = new ModelAndView("redirect:employee_dismissal.htm?invalid=dismisssuccess");
        }
         
        return mv;
    }
    
    private static int differenceInMonths(Date d1, Date d2) {
    Calendar c1 = Calendar.getInstance();
    c1.setTime(d1);
    Calendar c2 = Calendar.getInstance();
    c2.setTime(d2);
    int diff = 0;
    if (c2.after(c1)) {
        while (c2.after(c1)) {
            c1.add(Calendar.MONTH, 1);
            if (c2.after(c1)) {
                diff++;
            }
        }
    } else if (c2.before(c1)) {
        while (c2.before(c1)) {
            c1.add(Calendar.MONTH, -1);
            if (c1.before(c2)) {
                diff--;
            }
        }
    }
    return diff;
}
    
}