package model.dao;

import java.sql.SQLException;
import java.util.List;
import model.dto.Course;
import model.dto.Student;

public interface DAOAddStudentToCs {
    public List<Student> getAllStudents()throws SQLException;
    public List<Course> getAllCourses()throws SQLException;
    public void addStudentToCs(Integer stid, Integer csid) throws SQLException;
//    public Student getStudentById(Integer studentId) throws SQLException;
//    public void addStudent(Student stu) throws SQLException;
//    public void deleteStudent(Student stu) throws SQLException;
//    public void updateStudent(Student stu) throws SQLException;
//    public List<StudentCourse> allstudentCourse(Integer stid) throws SQLException;
//    public List<Result> allstudentResult(Integer stid)  throws SQLException;
}
