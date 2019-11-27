<%@page import="java.util.List"%>
<%@page import="model.dto.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teachers</title>
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
        <h1>Insert a Teacher</h1>
        <form action="teacherServlet" method="post">
            First Name<input type="text" name="firstName" required="true"><br>
            Last Name<input type="text" name="lastName" required="true"><br>
            Phone Number<input type="text" name="phoneNumber" required="true"><br>
            Address<input type="text" name="address" required="true"><br>
            City<input type="text" name="city" required="true"><br>
            Salary<input type="number" name="salary" min="0" max="99999999.99" step="0.01" required="true"><br>  
            <input type="submit" value="Send">
            <input type="hidden"  name="action" value="ADDTEACHER">
        </form>
    </body>
</html>

