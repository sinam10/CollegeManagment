<%@page import="model.dto.Course"%>
<%@page import="java.util.List"%>
<%@page import="model.dto.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results</title>
    </head>
    <body>
         <% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
        response.setHeader("Pragma", "no-cache"); 
        response.setDateHeader("Expires", 0); %>
        <jsp:include page="header.jsp">
            <jsp:param name="subTitle" value="Results"/>
        </jsp:include>
        <%@include  file="MainMenu.jsp" %>
        <br>
        <br>
        <h1>Insert a Result</h1>
        <form action="resultServlet" method="post">
        <table>
            <tr>
            <td>
        <%  
            List<Student> stuList = (List<Student>)request.getAttribute("students");
            if(stuList.size()==0){ %>
            <a href="addStudent.jsp?rtn=astc">Insert Student</a> <br>
          <%  } else { %>
            <select name="studentID" style="width:200px">
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
             <select name="cID" style="width:200px">
          <% 
                for(Course cs : csList){
          %>         
            <option value="<%=cs.getCourse_id() %>"><%=cs.getCourse_name() %></option>
            
              <% } %>        
            </select>          
         <%  } %> 
            </td>   
            
            
            
             <td>      
                 Mark<input type="number" min="0" max="10" name="mark" required="true"><br>
             </td>      
        </tr>
        </table>
        <% if(csList.size()!=0 && stuList.size()!=0){  %>
        <input type="submit" value="Add Result">
        <%  } %> 
        <input type="hidden"  name="action" value="ADDRESULT">
        </form>
    </body>
</html>

