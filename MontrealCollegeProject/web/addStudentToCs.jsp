<%-- 
    Document   : addStudentToCs
    Created on : 3-Jan-2019, 6:52:27 PM
    Author     : HADI
--%>

<%@page import="model.dto.Course"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dto.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Student To Course</title>
    </head>
    <body>
        <%@include  file="MainMenu.jsp" %>
        <% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
        response.setHeader("Pragma", "no-cache"); 
        response.setDateHeader("Expires", 0); %>
        <br>
        <br>
        <h1>Add Student To Course</h1>
        <form action="addStudentToCsServlet" method="post">
            <table>
            <tr>
            <td>
        <%  
            List<Student> stuList = (List<Student>)request.getAttribute("students");
            if(stuList.size()==0){ %>
            <a href="addStudent.jsp?rtn=astc">Insert Student</a> <br>
<!--//                request.getRequestDispatcher("/addStudent.jsp").forward(request, response);-->
          <%  } else { %>
            <select name="std" style="width:200px">
          <% 
                for(Student stu : stuList){ 
          %>           
        <option value="<%=stu.getStudentId() %>"><%=stu.getFirstName()+ " " + stu.getLastName() %></option>       
          <% } %>        
        </select>          
         <%  } %> 
            </td>
            
          <td>
        <%  List<Course> csList = (List<Course>)request.getAttribute("courses");
            if(csList.size()==0){        %> 
             <a href="addCourse.jsp?rtn=astc">Insert Course</a> <br>
         <%   }else{ %>
             <select name="cs" style="width:200px">
          <% 
                for(Course cs : csList){
          %>         
            <option value="<%=cs.getCourse_id() %>"><%=cs.getCourse_name() %></option>
            
              <% } %>        
            </select>          
         <%  } %> 
            </td>       
        </tr>
        </table>
        <% if(csList.size()!=0 && stuList.size()!=0){  %>
        <input type="submit" value="Add Student">
        <%  } %>
        <input type="hidden"  name="act" value="ADDSTUDENTTOCS">
        </form>
    </body>
</html>
