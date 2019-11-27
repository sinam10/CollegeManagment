<%@page import="model.dto.Result"%>
<%@page import="model.dto.Course"%>
<%@page import="java.util.List"%>
<%@page import="model.dto.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Result</title>
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
        <h1>Edit Result</h1>
        
        <% Result rs=(Result)request.getAttribute("theresult"); 
//        System.out.println("jsp_Rs****************************************");
//        System.out.println(rs.getCourse_name());
        %>
        <form action="resultServlet" method="post">           
            Session Number<input type="text" name="sessionnumber" value="<%=rs.getSession_number()%>"><br>
            Mark<input type="text" name="mark" value="<%=rs.getMarks()%>"><br>
            
            <input type="submit" value="Save Edit">
            <input type="hidden"  name="action" value="EDITRESULT">
            <input type="hidden"  name="resultID" value="<%=rs.getResult_id()%>">
            <input type="hidden"  name="studentID" value="<%=rs.getStudent_id()%>">
            <input type="hidden"  name="courseID" value="<%=rs.getCourse_id()%>">            
        </form>
    </body>
</html>













