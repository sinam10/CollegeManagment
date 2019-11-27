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
import model.dto.Teacher;
import util.DatabaseConnection;

public class DAOAddTeacherToCsImpl implements DAOAddTeacherToCs {
    
    @Override
    public List<Teacher> getAllTeachers()throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        ArrayList<Teacher> teachers = new ArrayList();
        try(Statement st = conn.createStatement()){
            String sql = "select teacher_id, first_name, last_name,phone_number, address, city, salary from teachers";
            ResultSet result = st.executeQuery(sql);
            while(result.next()){
                Teacher stu = new Teacher();
                stu.setTeacher_id(result.getInt("teacher_id"));
               stu.setFirst_name(result.getString("first_name"));
               stu.setLast_name(result.getString("last_name"));
               stu.setPhone_number(result.getString("phone_number"));
               stu.setAddress(result.getString("address"));
               stu.setCity(result.getString("city"));
               stu.setSalary(Double.parseDouble(result.getString("salary")));
                teachers.add(stu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudentsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return teachers;
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
    public void addTeacherToCs(Integer trid, Integer csid) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();    
        
            int num1 = 0;    
    String sql1="select count(*) as theCount from TEACHERCOURSE where teacher_id=? and course_id=?";
    PreparedStatement st1 = conn.prepareStatement(sql1);
    st1.setInt(1, trid);
    st1.setInt(2, csid);
    ResultSet result1 = st1.executeQuery();
    while(result1.next()){
        num1 = (result1.getInt("theCount"));
    }   
        
    if(num1==0){
        String sql="insert into TEACHERCOURSE( teacher_id, COURSE_ID) "
                  + " values (?,?)";       
           PreparedStatement pst = conn.prepareCall(sql); 
        pst.setInt(1, trid);
        pst.setInt(2, csid);
        pst.execute();
    }
    }

    
}

