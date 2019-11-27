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
import model.dao.DAOAddStudentToCs;
import model.dao.DAOAddStudentToCsImpl;
import model.dao.DAOAddTeacherToCs;
import model.dao.DAOAddTeacherToCsImpl;
import model.dao.DAOStudents;
import model.dao.DAOStudentsImpl;
import model.dao.DAOTeachers;
import model.dao.DAOTeachersImpl;
import model.dto.StudentCourseHeader;
import model.dto.Teacher;

/**
 *
 * @author HADI
 */
@WebServlet("/addTeacherToCsServlet")
public class AddTeacherToCsServlet extends HttpServlet{

    private DAOAddTeacherToCs dao= new DAOAddTeacherToCsImpl();
    
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String act=request.getParameter("act");
         System.out.println("sr_action_doGet****************************************");
         System.out.println(act);
          if(act.equals("LISTTEACHERCS")){
        try {
            request.setAttribute("teachers", dao.getAllTeachers());
            request.setAttribute("courses", dao.getAllCourses());
        } catch (SQLException ex) {
            Logger.getLogger(TeacherServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/addTeacherToCs.jsp").forward(request, response);
        }
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String act=request.getParameter("act");
        System.out.println("sr_action_doPost****************************************");
         System.out.println(act);
         
          if(act.equals("ADDTEACHERTOCS")){
         try {
                dao.addTeacherToCs(Integer.parseInt(request.getParameter("trd")),Integer.parseInt(request.getParameter("cs")));
            request.getRequestDispatcher("/MainMenu.jsp").forward(request, response);
    
        } catch (SQLException ex) {
            Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
          }
    }
 
     
}  
