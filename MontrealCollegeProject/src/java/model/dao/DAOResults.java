/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.SQLException;
import java.util.List;
import model.dto.Course;
import model.dto.Result;
import model.dto.ResultDetail;
import model.dto.Student;

/**
 *
 * @author HADI
 */
public interface DAOResults {
    public List<ResultDetail> getAllResults() throws SQLException;
    public Result getResultById(Integer resultId) throws SQLException;
    public void addResult(Result re) throws SQLException;
    public void deleteResult(Integer rsid) throws SQLException;
    public void updateResult(Result re) throws SQLException;
    public List<Student> getAllStudents()throws SQLException;
    public List<Course> getAllCourses()throws SQLException ;
    public String StCs(Result re) throws SQLException ;
    public List<Integer> teacherCs(Integer tid) throws SQLException;
    public List<Integer> studentCs(Integer studentid) throws SQLException;
    public Course getCourseById(Integer courseId)  throws SQLException;
}
