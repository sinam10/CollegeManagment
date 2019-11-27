package model.dao;

import java.sql.SQLException;
import java.util.List;
import model.dto.Course;
import model.dto.Teacher;

public interface DAOAddTeacherToCs {
    public List<Teacher> getAllTeachers()throws SQLException;
    public List<Course> getAllCourses()throws SQLException;
    public void addTeacherToCs(Integer stid, Integer csid) throws SQLException;
}
