<%-- 
    Document   : addStudentToCs
    Created on : 3-Jan-2019, 6:52:27 PM
    Author     : HADI
--%>

<%@page import="model.dto.Teacher"%>
<%@page import="model.dto.Course"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dto.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Teacher To Course</title>
    </head>
    <body>
        <% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
        response.setHeader("Pragma", "no-cache"); 
        response.setDateHeader("Expires", 0); %>
        <%@include  file="MainMenu.jsp" %>
        <br>
        <br>
        <h1>Add Teacher To Course</h1>
        <form action="addTeacherToCsServlet" method="post">
            <table>
            <tr>
            <td>
        <%  
            List<Teacher> trList = (List<Teacher>)request.getAttribute("teachers");
            if(trList.size()==0){ %>
            <a href="addTeacher.jsp?rtn=astc">Insert Teacher</a> <br>
          <%  }else{ %>
            <select name="trd" style="width:200px">
          <% 
                for(Teacher tr : trList){
          %>           
        <option value="<%=tr.getTeacher_id() %>"><%=tr.getFirst_name()+ " " + tr.getLast_name() %></option>       
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
        <% if(csList.size()!=0 && trList.size()!=0){  %>
        <input type="submit" value="Add Teacher">
        <%  } %>
        <input type="hidden"  name="act" value="ADDTEACHERTOCS">
        </form>
    </body>
</html>
