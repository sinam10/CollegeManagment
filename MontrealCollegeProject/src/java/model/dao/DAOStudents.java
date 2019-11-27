
package model.dao;

import java.sql.SQLException;
import model.dto.Student;
import java.util.List;
import model.dto.Result;
import model.dto.StudentCourse;

/**
 *
 * @author user
 */
public interface DAOStudents {

    public List<Student> getAllStudents() throws SQLException;
    public Student getStudentById(Integer studentId) throws SQLException;
    public void addStudent(Student stu) throws SQLException;
    public void deleteStudent(Integer stid) throws SQLException;
    public void updateStudent(Student stu) throws SQLException;
    public List<StudentCourse> allstudentCourse(Integer stid) throws SQLException;
    public List<Result> allstudentResult(Integer stid)  throws SQLException;
    
    
}
