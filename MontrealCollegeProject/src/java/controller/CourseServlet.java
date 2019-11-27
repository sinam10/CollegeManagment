
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.DAOCourses;
import model.dao.DAOCoursesImpl;
import model.dao.DAOStudents;
import model.dao.DAOStudentsImpl;
import model.dto.Course;
import model.dto.StudentCourse;
import model.dto.StudentCourseHeader;



@WebServlet("/courseServlet")
public class CourseServlet extends HttpServlet{
    
    private DAOCourses dao= new DAOCoursesImpl();
    
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action=request.getParameter("action");
         System.out.println("C_sr_action_doGet****************************************");
         System.out.println(action);
        
        if(action.equals("GETCOURSEBYID")){
        Course cs;
            try {
                cs = dao.getCourseById(Integer.parseInt(request.getParameter("csId")));
//                System.out.println(cs.getCourse_name()); 
                request.setAttribute("thecourse", cs);                
                request.getRequestDispatcher("/editCourse.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }
        else if(action.equals("COURSEREMOVE")){
             try {
                dao.deleteCourse(Integer.parseInt(request.getParameter("csId")));
                request.getRequestDispatcher("/MainMenu.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(CourseServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        else if(action.equals("LISTCOURSE")){
        try {
            request.setAttribute("courses", dao.getAllCourses());
        } catch (SQLException ex) {
            Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/courseView.jsp").forward(request, response);        
        }
        else if(action.equals("COURSEDETAIL")){
            try {
            request.setAttribute("theCs", dao.getCourseById(Integer.parseInt(request.getParameter("csId")))); 
        } catch (SQLException ex) {
            Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/courseDetail.jsp").forward(request, response);
        }
        else if(action.equals("TEACHEROFCS")){
            try {
            request.setAttribute("myTeacher", dao.teacherOfCourse(Integer.parseInt(request.getParameter("csId")))); 
        } catch (SQLException ex) {
            Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/teacherDetail.jsp").forward(request, response);
        }
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String action=request.getParameter("action");
        System.out.println("sr_action_doPost****************************************");
         System.out.println(action);
  
         
         
         if(action.equals("EDITCOURSE")){
             Course cs = new Course();       
         cs.setCourse_name(request.getParameter("courseName"));
         cs.setCredit_number(Integer.parseInt(request.getParameter("creditNumber")));
               cs.setCourse_id(Integer.parseInt( request.getParameter("courseID")));
             System.out.println("C_sr_action_doPost_EDITCOURSE****************************************");
            try {
                dao.updateCourse(cs);
            } catch (SQLException ex) {
                Logger.getLogger(CourseServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
         } else if(action.equals("ADDCOURSE")){         
         Course cse = new Course();
         cse.setCourse_name(request.getParameter("courseName"));
         cse.setCredit_number(Integer.parseInt(request.getParameter("creditNumber")));
            try {
                System.out.println(request.getParameter("courseName"));
                System.out.println(request.getParameter("creditNumber"));
                dao.addCourse(cse);
            } catch (SQLException ex) {
                Logger.getLogger(CourseServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
         
    request.getRequestDispatcher("/MainMenu.jsp").forward(request, response);
    }
    
 
     
}  



