/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.DAOStudents;
import model.dao.DAOStudentsImpl;
import model.dao.DAOTeachers;
import model.dao.DAOTeachersImpl;
import model.dto.Student;
import model.dto.Teacher;
import model.dto.TeacherCourseHeader;

/**
 *
 * @author HADI
 */
@WebServlet("/teacherServlet")
public class TeacherServlet extends HttpServlet{

    private DAOTeachers dao= new DAOTeachersImpl();
    
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action=request.getParameter("action");
         System.out.println("sr_action_doGet****************************************");
         System.out.println(action);
        
        if(action.equals("GETTEACHERBYID")){
           Teacher thr;
            try {
                request.setAttribute("courses", dao.allTeacherCourse(Integer.parseInt(request.getParameter("thId"))));
                thr = dao.getTeacherById(Integer.parseInt(request.getParameter("thId")));
                request.setAttribute("theteacher", thr);                
                request.getRequestDispatcher("/editTeacher.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }        
        else if(action.equals("LISTTEACHER")){
        try {
            request.setAttribute("teachers", dao.getAllTeachers());
        } catch (SQLException ex) {
            Logger.getLogger(TeacherServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/teacherView.jsp").forward(request, response);
        }else if(action.equals("REMOVETEACHER")){
             try {
                dao.deleteTeacher(Integer.parseInt(request.getParameter("thrId")));
                request.getRequestDispatcher("/MainMenu.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(CourseServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(action.equals("DETAILTEACHER")){
            try {
            request.setAttribute("myTeacher", dao.getTeacherById(Integer.parseInt(request.getParameter("tchId")))); 
            request.setAttribute("courses", dao.allTeacherCourse(Integer.parseInt(request.getParameter("tchId"))));
        } catch (SQLException ex) {
            Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/studentDetail.jsp").forward(request, response);
        }
         else if(action.equals("TEACHERCOURSEREMOVE")){
            try {
                TeacherCourseHeader sch=new TeacherCourseHeader();
                sch.setTeacher_Id(Integer.parseInt(request.getParameter("tId")));
                sch.setCourse_id(Integer.parseInt(request.getParameter("csId")));
                dao.deleteTeacherCourse(sch);
                request.getRequestDispatcher("/teacherDetail.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(CourseServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String action=request.getParameter("action");
        System.out.println("sr_action_doPost****************************************");
         System.out.println(action);
        
         if(action.equals("EDITTEACHER")){
             
             Teacher teacher= new Teacher();
        teacher.setTeacher_id(Integer.parseInt(request.getParameter("teacherID")));
        teacher.setFirst_name(request.getParameter("firstName"));
        teacher.setLast_name(request.getParameter("lastName"));
        teacher.setPhone_number(request.getParameter("phoneNumber"));
        teacher.setAddress(request.getParameter("address"));
        teacher.setCity(request.getParameter("city"));
        teacher.setSalary(Double.parseDouble(request.getParameter("salary")));
            try {
//                System.out.println("sr1****************************************");
                dao.updateTeacher(teacher);        
                request.getRequestDispatcher("/MainMenu.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);
            }             
        }      
         else if(action.equals("ADDTEACHER")){
         try {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String salary = request.getParameter("salary");
            
            Teacher teacher = new Teacher();
            teacher.setFirst_name(firstName);
            teacher.setLast_name(lastName);
            teacher.setPhone_number(phoneNumber);
            teacher.setAddress(address);
            teacher.setCity(city);
            teacher.setSalary(Double.parseDouble(salary));
               System.out.println("sr_T_action_doPost****************************************"); 
                dao.addTeacher(teacher);
            request.getRequestDispatcher("/MainMenu.jsp").forward(request, response);    
        } catch (SQLException ex) {
            Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    }
 
     
}  


