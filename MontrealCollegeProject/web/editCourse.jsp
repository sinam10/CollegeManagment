<%@page import="model.dto.Course"%>
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
        <h1>Edit Student</h1>
        
        <% Course cs=(Course)request.getAttribute("thecourse"); 
//        System.out.println("jspCs****************************************");
//        System.out.println(cs.getCourse_name());
        %>
        <form action="courseServlet" method="post">           
            Course Name<input type="text" name="courseName" value="<%=cs.getCourse_name()%>"><br>
            Credit Number<input type="text" name="creditNumber" value="<%=cs.getCredit_number()%>"><br>
            
            <input type="submit" value="Save Edit">
            <input type="hidden"  name="action" value="EDITCOURSE">
            <input type="hidden"  name="courseID" value="<%=cs.getCourse_id()%>">
        </form>
    </body>
</html>













