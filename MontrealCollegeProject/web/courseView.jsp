<%@page import="java.util.List"%>
<%@page import="model.dto.Course"%>
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
            <jsp:param name="subTitle" value="Courses"/>
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
                List<Course> courList = (List<Course>)request.getAttribute("courses");                
                for(Course cou : courList){                  
            %>
                <tr>
                     <td>
                        <%= cou.getCourse_id()%>
                    </td>
                    <td>
                        <%= cou.getCourse_name()%>
                    </td>
                    <td>
                        <%= cou.getCredit_number()%>
                    </td>
                    </td>
                    <td><a href="courseServlet?action=GETCOURSEBYID&csId=<%=cou.getCourse_id()%>" >Edit</a></td>
                    <td><a href="courseServlet?action=COURSEREMOVE&csId=<%=cou.getCourse_id()%>" >Delete</a></td>
                    <td><a href="courseServlet?action=COURSEDETAIL&csId=<%=cou.getCourse_id()%>" >Detail</a></td>
                    <input type="hidden"  name="coId" value="<%=cou.getCourse_id()%>">
                </tr>
            <%    }
            %>            
        </table>
    </body>
</html>
