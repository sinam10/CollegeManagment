
package model.dto;

/**
 *
 * @author HADI
 */
public class Course {
    
    private Integer course_id;
    private String course_name;
    private Integer credit_number;

    public Course() {
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Integer getCredit_number() {
        return credit_number;
    }

    public void setCredit_number(Integer credit_number) {
        this.credit_number = credit_number;
    }
    
    
}
