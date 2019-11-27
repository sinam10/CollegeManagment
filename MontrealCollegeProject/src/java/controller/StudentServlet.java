
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
import model.dto.Student;
import model.dto.StudentCourseHeader;


@WebServlet("/studentServlet")
public class StudentServlet extends HttpServlet{
    
    private DAOStudents dao= new DAOStudentsImpl();
    private DAOCourses daoc= new DAOCoursesImpl();
    
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action=request.getParameter("action");
//         System.out.println("sr_action_doGet****************************************");
//         System.out.println(action);
        
        if(action.equals("GETSTUDENTBYID")){
        Student student;
            try {
                request.setAttribute("courses", dao.allstudentCourse(Integer.parseInt(request.getParameter("stId"))));
                request.setAttribute("results", dao.allstudentResult(Integer.parseInt(request.getParameter("stId"))));
                student = dao.getStudentById(Integer.parseInt(request.getParameter("stId")));                
                request.setAttribute("thestudent", student);                
                request.getRequestDispatcher("/editStudent.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }else if(action.equals("LISTSTUDENT")){
        try {
            request.setAttribute("students", dao.getAllStudents());
        } catch (SQLException ex) {
            Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/studentView.jsp").forward(request, response);
        
        }else if(action.equals("DETAILSTUDENT")){
            try {
            request.setAttribute("myStudent", dao.getStudentById(Integer.parseInt(request.getParameter("stdtId")))); 
            request.setAttribute("courses", dao.allstudentCourse(Integer.parseInt(request.getParameter("stdtId"))));
            request.setAttribute("results", dao.allstudentResult(Integer.parseInt(request.getParameter("stdtId"))));
        } catch (SQLException ex) {
            Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/studentDetail.jsp").forward(request, response);
        }
        else if(action.equals("DELETESTUDENT")){
            try {
                dao.deleteStudent(Integer.parseInt(request.getParameter("stdId")));
                request.getRequestDispatcher("/MainMenu.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(CourseServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
        else if(action.equals("STUDENTCOURSEDELETE")){
            try {
                StudentCourseHeader sch=new StudentCourseHeader();
                sch.setStudentId(Integer.parseInt(request.getParameter("stcId")));
                sch.setCourse_id(Integer.parseInt(request.getParameter("csId")));
                daoc.deleteStudentCourse(sch);
                request.getRequestDispatcher("/studentDetail.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(CourseServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String action=request.getParameter("action");
//         System.out.println("ST_SR_sr_action_doPost****************************************");
         System.out.println(action);
         try{            
            Student student = new Student();           
            student.setFirstName(request.getParameter("firstName"));
            student.setLastName(request.getParameter("lastName"));
            student.setPhoneNumber(request.getParameter("phoneNumber"));
            student.setAddress(request.getParameter("address"));
            student.setCity(request.getParameter("city"));
            student.setMajor(request.getParameter("major"));
            
            if(action.equals("EDITSTUDENT")){
                 student.setStudentId(Integer.parseInt( request.getParameter("studentID")));
//                System.out.println("sr_action_doPost_EDITSTUDENT****************************************");
                 dao.updateStudent(student);
            } else if(action.equals("ADDSTUDENT")){                
//               System.out.println("sr_action_doPost_else****************************************");                
                dao.addStudent(student);
            }
                  request.getRequestDispatcher("/MainMenu.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}  
