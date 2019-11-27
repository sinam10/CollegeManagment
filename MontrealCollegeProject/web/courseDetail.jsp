<%@page import="model.dto.Course"%>
<%@page import="model.dto.Result"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Courses</title>
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
        <table border="1">
            <tr>
                <th>Course ID</th>
                <th>Course Name</th>
                <th>Course Number</th>                
            </tr>            
            <%
                Course cs = (Course) request.getAttribute("theCs");
            %>
                <tr>
                     <td>
                        <%= cs.getCourse_id()%>
                    </td>
                    <td>
                        <%= cs.getCourse_name()%>
                    </td>
                    <td>
                        <%= cs.getCredit_number()%>
                    </td>
                    </td>
                    <td><a href="courseServlet?action=GETCOURSEBYID&csId=<%=cs.getCourse_id()%>" >Edit</a></td>
                    <td><a href="courseServlet?action=TEACHEROFCS&csId=<%=cs.getCourse_id()%>" >Teacher</a></td>
                    <input type="hidden"  name="coId" value="<%=cs.getCourse_id()%>">
                </tr>
        </table>
            
            <br>
            <br>
            
            <br>
            <br>   
    </body>
</html>
