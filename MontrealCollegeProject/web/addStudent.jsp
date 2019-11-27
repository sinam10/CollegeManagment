<%@page import="java.util.List"%>
<%@page import="model.dto.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Students</title>
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
        <h1>Insert a Student</h1>
        <form action="studentServlet" method="post">
            First Name<input type="text" name="firstName" required="true"><br>
            Last Name<input type="text" name="lastName" required="true"><br>
            Phone Number<input type="text" name="phoneNumber" required="true"><br>
            Address<input type="text" name="address" required="true"><br>
            City<input type="text" name="city" required="true"><br>
            Major<input type="text" name="major" required="true"><br>  
            <input type="submit" value="Send">
            <input type="hidden"  name="action" value="ADDSTUDENT">
            <input type="hidden"  name="rtn" value="<%=request.getParameter("rtn") %>">
            <% 
              // System.out.println("ADD_ST****************************************");
             //System.out.println(request.getParameter("rtn"));  
            %>           
        </form>
    </body>
</html>

















<!--<html>
    <head>
        <title>Insert an Employee</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Insert an Student</h1>
        <form action="studentServlet" method="post">
            First Name<input type="text" name="firstName"><br>
            Last Name<input type="text" name="lastName"><br>
            Phone Number<input type="text" name="phoneNumber"><br>
            Address<input type="text" name="address"><br>
            City<input type="text" name="city"><br>
            Major<input type="text" name="major"><br>  
            <input type="submit" value="Send">
        </form>
    </body>
</html>-->