/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.SQLException;
import java.util.List;
import model.dto.Course;
import model.dto.Teacher;
import model.dto.TeacherCourseHeader;


/**
 *
 * @author HADI
 */
public interface DAOTeachers {

    public List<Teacher> getAllTeachers() throws SQLException;
    public Teacher getTeacherById(Integer teacherId) throws SQLException;
    public void addTeacher(Teacher tech) throws SQLException;
    public void deleteTeacher(Integer trid) throws SQLException;
    public void updateTeacher(Teacher tech) throws SQLException;
    public List<Course> allTeacherCourse(Integer tchid)  throws SQLException;
    public void deleteTeacherCourse(TeacherCourseHeader c) throws SQLException;
    
}
