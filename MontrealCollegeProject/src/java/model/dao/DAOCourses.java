
package model.dao;

import java.sql.SQLException;
import java.util.List;
import model.dto.Course;
import model.dto.StudentCourse;
import model.dto.StudentCourseHeader;
import model.dto.Teacher;

/**
 *
 * @author HADI
 */
public interface DAOCourses {
     public List<Course> getAllCourses() throws SQLException;
    public Course getCourseById(Integer courseId) throws SQLException;
    public void addCourse(Course co) throws SQLException;
    public void deleteCourse(Integer coId) throws SQLException;
    public void updateCourse(Course co) throws SQLException;
    public void deleteStudentCourse(StudentCourseHeader co) throws SQLException;
    public Teacher teacherOfCourse(Integer courseId)  throws SQLException;
    
}
