/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.dto.Result;
import model.dto.ResultDetail;
import model.dto.Student;
import util.DatabaseConnection;
import static util.DatabaseConnection.getConnection;

/**
 *
 * @author HADI
 */
public class DAOResultImpl implements DAOResults{


    
    
    @Override
    public Result getResultById(Integer resultId) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * "
                + " FROM RESULTS WHERE RESULT_ID="+ resultId ;
        Statement st = getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        Result result = new Result();
        if(rs.next()){
//            result = new Result();
            result.setResult_id(rs.getInt("RESULT_ID"));
            result.setStudent_id(rs.getInt("STUDENT_ID"));
            result.setCourse_id(rs.getInt("COURSE_ID"));
            result.setSession_number(rs.getInt("SESSION_NUMBER"));
            result.setMarks(rs.getInt("MARKS"));
        }
        return result;
    }
        @Override
    public void deleteResult(Integer rsid) throws SQLException{
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE  RESULTS WHERE RESULT_ID=?";
        PreparedStatement pSt = conn.prepareCall(sql);
        pSt.setInt(1, rsid);
        pSt.execute();
    }

     @Override
    public void updateResult(Result re) throws SQLException{
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE RESULTS SET SESSION_NUMBER=?,MARKS=? WHERE RESULT_ID=?";
                
        PreparedStatement pSt = conn.prepareCall(sql);            
        pSt.setInt(1, re.getSession_number());
        pSt.setInt(2, re.getMarks());
        pSt.setInt(3, re.getResult_id());  
        pSt.execute();
    }
    
    
    @Override
    public String StCs(Result re) throws SQLException {
    Connection conn = DatabaseConnection.getConnection();
    int num = 0;
    String sql="select count(*) as thestcs from studentcourse where student_id=? and course_id=?";
    PreparedStatement st = conn.prepareStatement(sql);
    st.setInt(1, re.getStudent_id());
    st.setInt(2, re.getCourse_id());    
    ResultSet result = st.executeQuery();
    while(result.next()){
        num = result.getInt("thestcs");
    }
    return num==1?"T":"F";
    }    
    
    @Override
    public void addResult(Result re) throws SQLException {
    Connection conn = DatabaseConnection.getConnection();
        
    int num1 = 0;    
    String sql1="select count(*) as theCount from results where student_id=? and course_id=?";
    PreparedStatement st1 = conn.prepareStatement(sql1);
    st1.setInt(1, re.getStudent_id());
    st1.setInt(2, re.getCourse_id());
    ResultSet result1 = st1.executeQuery();
    while(result1.next()){
        num1 = (result1.getInt("theCount"));
    }     
    String sql="";
    if(num1==0){
        System.out.println("DAO_num1==0****************************************");
        sql="insert into results( result_id,student_id, course_id, marks ) "
                + " values (RESULTS_SEQ.nextval,?,?,?)";       
        PreparedStatement pst = conn.prepareCall(sql); 
        pst.setInt(1, re.getStudent_id());
        pst.setInt(2, re.getCourse_id());
        pst.setInt(3, re.getMarks());
        pst.execute();
        } 
    }    
    
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
    public List<ResultDetail> getAllResults() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        ArrayList<ResultDetail> rst = new ArrayList();
        try(Statement st = conn.createStatement()){
            String sql = "select e.RESULT_ID,t.student_id,t.first_name,t.last_name,c.course_id,c.course_name,e.session_number,e.marks   " +
                         " from results e  " +
                         " join students t on t.student_id=e.student_id  " +
                         " join courses c on e.course_id=c.course_id ";
            ResultSet result = st.executeQuery(sql);
            while(result.next()){
                ResultDetail str = new ResultDetail();
                str.setResult_id(result.getInt("RESULT_ID"));
                str.setStudent_id(result.getInt("student_id"));
                str.setFirstName(result.getString("first_name"));
                str.setLastName(result.getString("last_name"));
                str.setCourse_id(result.getInt("course_id"));
                str.setCourse_name(result.getString("course_name"));
                str.setSession_number(result.getInt("session_number"));
                str.setMarks(result.getInt("marks"));
                rst.add(str);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudentsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return rst;    
    }
    
    @Override
    public List<Integer> teacherCs(Integer tid) throws SQLException {
    Connection conn = DatabaseConnection.getConnection();
    List<Integer> li=new ArrayList();
    int num = 0;
    String sql="select course_id from teachercourse where teacher_id=? ";
    PreparedStatement st = conn.prepareStatement(sql);
    st.setInt(1, tid); 
    ResultSet result = st.executeQuery();
    while(result.next()){
//        num = result.getInt("course_id");
        li.add(result.getInt("course_id"));
    }
    return li;
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
            course.setCourse_id(result.getInt("COURSE_ID"));
            course.setCourse_name(result.getString("COURSE_NAME"));
            course.setCredit_number(result.getInt("CREDIT_NUMBER"));
        }
        return course;
    }
    
    @Override
    public List<Integer> studentCs(Integer studentid) throws SQLException {
    Connection conn = DatabaseConnection.getConnection();
    List<Integer> li=new ArrayList();
    int num = 0;
    String sql="select course_id from studentcourse where student_id=? ";
    PreparedStatement st = conn.prepareStatement(sql);
    st.setInt(1, studentid); 
    ResultSet result = st.executeQuery();
    while(result.next()){
//        num = result.getInt("course_id");
        li.add(result.getInt("course_id"));
    }
    return li;
    } 

}
