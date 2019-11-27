
package model.dto;

/**
 *
 * @author HADI
 */
public class Result {
    
    private Integer result_id;
    private Integer student_id;
    private Integer course_id;
    private Integer session_number;
    private Integer marks;

    public Result() {
    }

    public Integer getResult_id() {
        return result_id;
    }

    public void setResult_id(Integer result_id) {
        this.result_id = result_id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public Integer getSession_number() {
        return session_number;
    }

    public void setSession_number(Integer session_number) {
        this.session_number = session_number;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }
    
    
}
