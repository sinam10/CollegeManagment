
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
import model.dto.StudentCourse;
import model.dto.StudentCourseHeader;
import model.dto.Teacher;
import util.DatabaseConnection;
import static util.DatabaseConnection.getConnection;

/**
 *
 * @author user
 */
public class DAOCoursesImpl implements DAOCourses{
    


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
    public Course getCourseById(Integer courseId)  throws SQLException{
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT COURSE_ID, COURSE_NAME, CREDIT_NUMBER "
                + "FROM COURSES WHERE COURSE_ID="+ courseId ;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(sql);
        Course course = new Course();
        if(result.next()){
//            course = new Course();
            course.setCourse_id(result.getInt("COURSE_ID"));
            course.setCourse_name(result.getString("COURSE_NAME"));
            course.setCredit_number(result.getInt("CREDIT_NUMBER"));
        }
        return course;
    }
    
    @Override
    public void deleteCourse(Integer coId) throws SQLException{
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE COURSES WHERE COURSE_ID="+coId;
        PreparedStatement pSt = conn.prepareCall(sql);
        pSt.execute();
    }

     @Override
    public void updateCourse(Course co)  throws SQLException{
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE COURSES SET COURSE_NAME=?,CREDIT_NUMBER=? where COURSE_ID=?";
                
        PreparedStatement pSt = getConnection().prepareCall(sql);
             
        pSt.setString(1, co.getCourse_name());
        pSt.setInt(2, co.getCredit_number());
        pSt.setInt(3, co.getCourse_id()); 

        pSt.execute();
    }
    
    @Override
    public void deleteStudentCourse(StudentCourseHeader co) throws SQLException{
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE STUDENTCOURSE WHERE COURSE_ID=? And student_id=?";
        PreparedStatement pSt = conn.prepareCall(sql);
        
//        System.out.println("C_dao_STUDENTCOURSEREMOVE****************************************");
//        System.out.println(co.getCourse_id());
//        System.out.println(co.getStudentId());
             
        pSt.setInt(1, co.getCourse_id());
        pSt.setInt(2, co.getStudentId());
        pSt.execute();
    }
       

    @Override
    public void addCourse(Course co) throws SQLException {
        
       Connection conn = DatabaseConnection.getConnection();
       String sql="insert into courses (course_id, course_name, credit_number) "+
               " values(COURSES_SEQ.nextval,?, ?)";
        PreparedStatement pst = conn.prepareCall(sql);      
        pst.setString(1, co.getCourse_name());
        pst.setInt(2, co.getCredit_number());
        pst.execute();
    }
    
    @Override
    public Teacher teacherOfCourse(Integer courseId)  throws SQLException{
        Connection conn = DatabaseConnection.getConnection();
        String sql = "select c.teacher_id, c.first_name, c.last_name, c.phone_number, c.city, c.address, c.salary  " +
        " from teachercourse e " +
        " join teachers c on e.teacher_id=c.teacher_id  " +
        " where e.course_id="+ courseId ;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(sql);
        Teacher tch = new Teacher();
        if(result.next()){
            tch.setTeacher_id(result.getInt("teacher_id"));
            tch.setFirst_name(result.getString("first_name"));
            tch.setLast_name(result.getString("last_name"));
            tch.setPhone_number(result.getString("phone_number"));
            tch.setCity(result.getString("city"));
            tch.setAddress(result.getString("address"));
            tch.setSalary(result.getDouble("salary"));
        }
        return tch;
    }

    }


  
    

