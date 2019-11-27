<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>        
<!--        <a href="teacherServlet?action=DETAILTEACHER">Teachers Detail</a> <br>-->
<!--        <a href="studentServlet?action=DETAILSTUDENT">Students Detail</a> <br>-->

        <h1>Main Menu</h1>
        
        <% if(session.getAttribute("user_r").equals("manager-script") || session.getAttribute("user_r").equals("admin")) { %>
        <a href="studentServlet?action=LISTSTUDENT">Students</a> <br>
        <a href="addStudent.jsp">Insert Student</a> <br>
        <a href="teacherServlet?action=LISTTEACHER">Teachers</a> <br>
        <a href="addTeacher.jsp">Insert Teacher</a> <br>
        <a href="courseServlet?action=LISTCOURSE">Courses</a> <br>
        <a href="addStudentToCsServlet?act=LISTSTUDENTCS">Add Student To Course</a> <br>
        <a href="addTeacherToCsServlet?act=LISTTEACHERCS">Add Teacher To Course</a> <br>
        <a href="resultServlet?action=LISTSTUDENTCS">Insert Result</a> <br>
        <a href="resultServlet?action=LISTRESULTS">Results</a> <br>
        
        <% } else if(session.getAttribute("user_r").equals("teacher")){ %>
        <a href="studentServlet?action=LISTSTUDENT">Students</a> <br>
        <a href="resultServlet?action=LISTSTUDENTCS">Insert Result</a> <br>
        <a href="resultServlet?action=LISTRESULTS">Results</a> <br>
        
         <% }  else if(session.getAttribute("user_r").equals("student")){ %>
         <a href="resultServlet?action=LISTRESULTS">Results</a> <br>
         <% } %>
          <a href="logoutServlet?action=LOGOUT">Logout</a> <br>
    </body>
</html>
