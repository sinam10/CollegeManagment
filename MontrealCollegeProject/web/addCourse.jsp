<%@page import="model.dto.Course"%>
<%@page import="java.util.List"%>
<%@page import="model.dto.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course</title>
    </head>
    <body>
        <% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
        response.setHeader("Pragma", "no-cache"); 
        response.setDateHeader("Expires", 0); %>
        
        <jsp:include page="header.jsp">
            <jsp:param name="subTitle" value="Students"/>
        </jsp:include>
        <%@include  file="MainMenu.jsp" %>
        <br>
        <br>
        <h1>Add Course</h1>
        
      
        <form action="courseServlet" method="post">           
            Course Name<input type="text" name="courseName" required="true" ><br>
            Credit Number<input type="number" min="1" max="8"  name="creditNumber"  required="true"><br>
            
            <input type="submit" value="Save Edit">
            <input type="hidden"  name="action" value="ADDCOURSE">

        </form>
    </body>
</html>













