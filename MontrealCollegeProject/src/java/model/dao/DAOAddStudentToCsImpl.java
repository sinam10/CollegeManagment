package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dto.Course;
import model.dto.Student;
import util.DatabaseConnection;

public class DAOAddStudentToCsImpl implements DAOAddStudentToCs {
    
    @Override
    public List<Student> getAllStudents()throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        ArrayList<Student> students = new ArrayList();
        try(Statement st = conn.createStatement()){
            String sql = "select student_id, first_name, last_name from students";
            ResultSet result = st.executeQuery(sql);
            while(result.next()){
                Student stu = new Student();
                stu.setStudentId(result.getInt("student_id"));
               stu.setFirstName(result.getString("first_name"));
               stu.setLastName(result.getString("last_name"));
                students.add(stu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudentsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return students;
    }
    
    @Override
    public List<Course> getAllCourses()throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        ArrayList<Course> courses = new ArrayList();
        try(Statement st = conn.createStatement()){
            String sql = "select COURSE_ID, course_name, credit_number from courses";
            ResultSet result = st.executeQuery(sql);
            while(result.next()){
                Course co = new Course();
               co.setCourse_id(Integer.parseInt(result.getString("COURSE_ID")));
               co.setCourse_name(result.getString("course_name"));
               co.setCredit_number(result.getInt("credit_number"));
                courses.add(co);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudentsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return courses;
    }
    
     @Override
    public void addStudentToCs(Integer stid, Integer csid) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();    
        
        int num1 = 0;    
    String sql1="select count(*) as theCount from STUDENTCOURSE where student_id=? and course_id=?";
    PreparedStatement st1 = conn.prepareStatement(sql1);
    st1.setInt(1, stid);
    st1.setInt(2, csid);
    ResultSet result1 = st1.executeQuery();
    while(result1.next()){
        num1 = (result1.getInt("theCount"));
    }   
        
    if(num1==0){
        String sql="insert into STUDENTCOURSE( student_id, COURSE_ID) "
                  + " values (?,?)";       
           PreparedStatement pst = conn.prepareCall(sql); 
        pst.setInt(1, stid);
        pst.setInt(2, csid);
        pst.execute();
    }
    }
    
    
}
