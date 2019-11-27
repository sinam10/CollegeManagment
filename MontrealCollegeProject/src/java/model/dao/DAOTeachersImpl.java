
package model.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dto.Course;
import model.dto.Teacher;
import model.dto.TeacherCourseHeader;
import util.DatabaseConnection;
import static util.DatabaseConnection.getConnection;

/**
 *
 * @author user
 */
public class DAOTeachersImpl implements DAOTeachers{

    @Override
    public List<Teacher> getAllTeachers()throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        ArrayList<Teacher> teachers = new ArrayList();
        try(Statement st = conn.createStatement()){
            String sql = "select teacher_id, first_name, last_name, phone_number, address, city, salary from teachers";
            ResultSet result = st.executeQuery(sql);
            while(result.next()){
                Teacher tech = new Teacher();
               tech.setTeacher_id(Integer.parseInt(result.getString("teacher_id")));
               tech.setFirst_name(result.getString("first_name"));
               tech.setLast_name(result.getString("last_name"));
               tech.setPhone_number(result.getString("phone_number"));
               tech.setAddress(result.getString("address"));
               tech.setCity(result.getString("city"));
               tech.setSalary(result.getDouble("salary"));
                teachers.add(tech);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudentsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return teachers;
    }

    @Override
    public void addTeacher(Teacher tech) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql="insert into teachers( teacher_id,first_name, last_name, phone_number, address, city, salary )"
                + " values(TEACHERS_SEQ.nextval,?,?,?,?,?,?)";
       
           PreparedStatement pst = conn.prepareCall(sql);        
           
        pst.setString(1, tech.getFirst_name());
        pst.setString(2, tech.getLast_name());
        pst.setString(3, tech.getPhone_number());
        pst.setString(4, tech.getAddress());
        pst.setString(5, tech.getCity());
        pst.setDouble(6, tech.getSalary());
        pst.execute();
    }
    
    
    @Override
    public Teacher getTeacherById(Integer teacherId)  throws SQLException{
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT TEACHER_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER,ADDRESS, CITY, SALARY "
                + " FROM TEACHERS WHERE TEACHER_ID="+teacherId;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(sql);
        Teacher teacher = null;
        if(result.next()){
            teacher = new Teacher();
            teacher.setTeacher_id(result.getInt("TEACHER_ID"));
            teacher.setFirst_name(result.getString("FIRST_NAME"));
            teacher.setLast_name(result.getString("LAST_NAME")); 
            teacher.setPhone_number(result.getString("PHONE_NUMBER"));
            teacher.setAddress(result.getString("ADDRESS"));
            teacher.setCity(result.getString("CITY"));
            teacher.setSalary(result.getDouble("SALARY"));
        }
        return teacher;
    }
    
        @Override
    public void deleteTeacher(Integer trid) throws SQLException{
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE TEACHERS WHERE TEACHER_ID="+trid;
        PreparedStatement pSt = conn.prepareCall(sql);
        pSt.execute();
    }

    @Override
    public void updateTeacher(Teacher tech)  throws SQLException{
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE TEACHERS SET FIRST_NAME=?,LAST_NAME=?,PHONE_NUMBER=?, ADDRESS=?, CITY=?, SALARY=? where TEACHER_ID=?";
                
        PreparedStatement pSt = conn.prepareCall(sql);
        pSt.setString(1, tech.getFirst_name());
        pSt.setString(2, tech.getLast_name());
        pSt.setString(3, tech.getPhone_number());
        pSt.setString(4, tech.getAddress());
        pSt.setString(5, tech.getCity());
        pSt.setDouble(6, tech.getSalary());
        pSt.setInt(7, tech.getTeacher_id());      
        pSt.execute();
    }
    
     @Override
    public List<Course> allTeacherCourse(Integer tchid)  throws SQLException{        
        Connection conn = DatabaseConnection.getConnection();        
        ArrayList<Course> teacherCs = new ArrayList();        
        try(Statement st = conn.createStatement()){
        String sql = "select c.course_id, c.course_name, c.credit_number  " +
        " from teachercourse e " +
        " join courses c on e.course_id=c.course_id  " +
        " where e.teacher_id="+ tchid;
        ResultSet result = st.executeQuery(sql);
        while(result.next()){
            Course stu = new Course();
           stu.setCourse_id(result.getInt("COURSE_ID"));
           stu.setCourse_name(result.getString("COURSE_NAME"));
           stu.setCredit_number(result.getInt("CREDIT_NUMBER"));
            teacherCs.add(stu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudentsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return teacherCs;        
    }
    
    @Override
    public void deleteTeacherCourse(TeacherCourseHeader c) throws SQLException{
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE TEACHERCOURSE WHERE COURSE_ID=? And teacher_id=?";
        PreparedStatement pSt = conn.prepareCall(sql);
             
        pSt.setInt(1, c.getCourse_id());
        pSt.setInt(2, c.getTeacher_Id());
        pSt.execute();
    }
    
}