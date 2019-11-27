
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dao.DAOCourses;
import model.dao.DAOCoursesImpl;
import model.dao.DAOResultImpl;
import model.dao.DAOResults;
import model.dao.DAOStudents;
import model.dao.DAOStudentsImpl;
import model.dto.Course;
import model.dto.Result;
import model.dto.StudentCourse;
import model.dto.StudentCourseHeader;



@WebServlet("/resultServlet")
public class ResultServlet extends HttpServlet{
    
    private DAOResults dao= new DAOResultImpl();
    
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action=request.getParameter("action");
         System.out.println("RS_sr_action_doGet****************************************");
         System.out.println(action);
         
         HttpSession session = request.getSession();
        
        if(action.equals("GETRESULTBYID")){
        Result rs;
            try {
                System.out.println("RS_sr1****************************************");                
                rs = dao.getResultById(Integer.parseInt(request.getParameter("rsId")));
                
                request.setAttribute("theresult", rs);                
                request.getRequestDispatcher("/editResult.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(action.equals("DELETERESULT")){
            try {
                dao.deleteResult(Integer.parseInt(request.getParameter("rstId")));
                request.getRequestDispatcher("/MainMenu.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(CourseServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
        else if(action.equals("LISTSTUDENTCS")){
        try {
            request.setAttribute("students", dao.getAllStudents());
            if(session.getAttribute("user_r").equals("manager-script") || session.getAttribute("user_r").equals("admin")) {
            request.setAttribute("courses", dao.getAllCourses());
            } else if(session.getAttribute("user_r").equals("teacher")){
               List<Course> cse = new ArrayList();
                for (Integer i : dao.teacherCs(Integer.parseInt(session.getAttribute("user_id").toString()))) {
                    cse.add(dao.getCourseById(i));
                }
            request.setAttribute("courses", cse);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/addResult.jsp").forward(request, response);
        }
        else if(action.equals("LISTRESULTS")){
            try {
                System.out.println("RS_sr1_LISTRESULTS****************************************"); 
                if(session.getAttribute("user_r").equals("teacher")){
                    request.setAttribute("teacherCs", dao.teacherCs(Integer.parseInt(session.getAttribute("user_id").toString())));
//               System.out.println(dao.teacherCs(Integer.parseInt(session.getAttribute("user_id").toString())));
                }
                if(session.getAttribute("user_r").equals("student")){
                    request.setAttribute("studentCs", dao.studentCs(Integer.parseInt(session.getAttribute("user_id").toString())));
//                System.out.println(dao.studentCs(Integer.parseInt(session.getAttribute("user_id").toString())));
                }
                request.setAttribute("cs", dao.getAllCourses());
                request.setAttribute("allresult", dao.getAllResults());
                request.getRequestDispatcher("/resultView.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ResultServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String action=request.getParameter("action");
        System.out.println("sr_action_doPost****************************************");
         System.out.println(action);
         
         if(action.equals("EDITRESULT")){
             Result cs = new Result();         
            cs.setResult_id(Integer.parseInt(request.getParameter("resultID")));
            cs.setStudent_id(Integer.parseInt(request.getParameter("studentID")));
            cs.setCourse_id(Integer.parseInt(request.getParameter("courseID")));
            cs.setSession_number(Integer.parseInt(request.getParameter("sessionnumber")));
            cs.setMarks(Integer.parseInt(request.getParameter("mark")));
             System.out.println("RS_sr_action_doPost_EDITRESULT****************************************");
            try {
                dao.updateResult(cs);
            } catch (SQLException ex) {
                Logger.getLogger(CourseServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
         }else if(action.equals("ADDRESULT")){ 
              Result cs = new Result();
            cs.setStudent_id(Integer.parseInt(request.getParameter("studentID")));
            cs.setCourse_id(Integer.parseInt(request.getParameter("cID")));
            cs.setMarks(Integer.parseInt(request.getParameter("mark")));
              try {
                  if((dao.StCs(cs)).equals("T")){
                      System.out.println("in _ T****************************************");
                dao.addResult(cs);
                  }
            } catch (SQLException ex) {
                Logger.getLogger(CourseServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
         
    request.getRequestDispatcher("/MainMenu.jsp").forward(request, response);
    }
    
 
     
}  

