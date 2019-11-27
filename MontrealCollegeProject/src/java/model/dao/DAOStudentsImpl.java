
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
import model.dto.Result;
import model.dto.Student;
import model.dto.StudentCourse;
import util.DatabaseConnection;

/**
 *
 * @author user
 */
public class DAOStudentsImpl implements DAOStudents{
    


    @Override
    public List<Student> getAllStudents()throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        ArrayList<Student> students = new ArrayList();
        try(Statement st = conn.createStatement()){
            String sql = "select student_id, first_name, last_name, phone_number, address, city, major from students";
            ResultSet result = st.executeQuery(sql);
            while(result.next()){
                Student stu = new Student();
                stu.setStudentId(result.getInt("student_id"));
               stu.setFirstName(result.getString("first_name"));
               stu.setLastName(result.getString("last_name"));
               stu.setPhoneNumber(result.getString("phone_number"));
               stu.setAddress(result.getString("address"));
               stu.setCity(result.getString("city"));
               stu.setMajor(result.getString("major"));
                students.add(stu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudentsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return students;
    }

    @Override
    public void addStudent(Student stu) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        
        String sql="insert into students( student_id,first_name, last_name, phone_number, address, city, major ) "
                + " values (STUDENTS_SEQ.nextval,?,?,?,?,?,?)";
       
           PreparedStatement pst = conn.prepareCall(sql);        
           
        pst.setString(1, stu.getFirstName());
        pst.setString(2, stu.getLastName());
        pst.setString(3, stu.getPhoneNumber());
        pst.setString(4, stu.getAddress());
        pst.setString(5, stu.getCity());
        pst.setString(6, stu.getMajor());

        pst.execute();
    }
    
    
    @Override
    public Student getStudentById(Integer studentId)  throws SQLException{
//        System.out.println("dao1****************************************");
//        System.out.println(studentId);
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT STUDENT_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER,ADDRESS, CITY, MAJOR "
                + " FROM STUDENTS WHERE STUDENT_ID="+ studentId;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(sql);
        Student student = new Student();
        if(result.next()){
//            student = new Student();
            student.setStudentId(result.getInt("STUDENT_ID"));
            student.setFirstName(result.getString("FIRST_NAME"));
            student.setLastName(result.getString("LAST_NAME")); 
            student.setPhoneNumber(result.getString("PHONE_NUMBER"));
            student.setAddress(result.getString("ADDRESS"));
            student.setCity(result.getString("CITY"));
            student.setMajor(result.getString("MAJOR"));
        }
//        System.out.println("dao****************************************");
//        System.out.println(student.getFirstName());
        return student;
    }
    
        @Override
    public void deleteStudent(Integer stid) throws SQLException{
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE STUDENTS WHERE STUDENT_ID=?";
        PreparedStatement pSt = conn.prepareCall(sql);
        pSt.setInt(1, stid);
        pSt.execute();
    }

    @Override
    public void updateStudent(Student st)  throws SQLException{
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE STUDENTS SET FIRST_NAME=?,LAST_NAME=?,PHONE_NUMBER=?, ADDRESS=?, CITY=?, MAJOR=?  where STUDENT_ID=?";
                
        PreparedStatement pSt = conn.prepareCall(sql);
             
        pSt.setString(1, st.getFirstName());
        pSt.setString(2, st.getLastName());
        pSt.setString(3, st.getPhoneNumber());
        pSt.setString(4, st.getAddress());
        pSt.setString(5, st.getCity());
        pSt.setString(6, st.getMajor());
        pSt.setInt(7, st.getStudentId());
        pSt.execute();
    }
    
    public List<StudentCourse> allstudentCourse(Integer stid)  throws SQLException{        
        Connection conn = DatabaseConnection.getConnection();        
        ArrayList<StudentCourse> studentCs = new ArrayList();        
        try(Statement st = conn.createStatement()){
        String sql = "SELECT studentcourse.student_id,students.first_name, students.last_name, students.major , COURSES.*  " +
            " from studentcourse  " +
            " join COURSES on studentcourse.COURSE_id = COURSES.COURSE_id  " +
            " join STUDENTS on studentcourse.STUDENT_id = STUDENTS.STUDENT_id  " +
            " where studentcourse.student_id="+ stid;
                     
            ResultSet result = st.executeQuery(sql);
            while(result.next()){
                StudentCourse stu = new StudentCourse();
               stu.setStudentId(result.getInt("student_id"));
               stu.setFirstName(result.getString("first_name"));
               stu.setLastName(result.getString("last_name"));
               stu.setCourse_id(result.getInt("COURSE_ID"));
               stu.setCourse_name(result.getString("COURSE_NAME"));
               stu.setCredit_number(result.getInt("CREDIT_NUMBER"));
               stu.setMajor(result.getString("major"));
//               System.out.println("dao****************************************");
//               System.out.println(stu.getFirstName());
                studentCs.add(stu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudentsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return studentCs;        
    }
    
    public List<Result> allstudentResult(Integer stid)  throws SQLException{        
        Connection conn = DatabaseConnection.getConnection();        
        ArrayList<Result> studentresults = new ArrayList();        
        try(Statement st = conn.createStatement()){
        String sql = "SELECT *  " +
            " from results  " +
            " where student_id="+ stid;                     
            ResultSet result = st.executeQuery(sql);
            while(result.next()){
                Result rs = new Result();
               rs.setResult_id(result.getInt("result_id"));
               rs.setStudent_id(result.getInt("student_id"));
               rs.setCourse_id(result.getInt("COURSE_ID"));
               rs.setSession_number(result.getInt("SESSION_NUMBER"));
               rs.setMarks(result.getInt("marks"));
//               System.out.println("dao****************************************");
//               System.out.println(stu.getFirstName());
                studentresults.add(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudentsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return studentresults;        
    }
    
    }


  
    

