<%@page import="model.dto.Course"%>
<%@page import="model.dto.Teacher"%>
<%@page import="model.dto.Result"%>
<%@page import="java.util.List"%>
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
        <table border="1">
            <tr>
                <th>Teacher ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                 <th>Phone Number</th>
                <th>Address</th>
                <th>City</th>
                <th>Salary</th>               
            </tr>            
            <%
                Integer teacherid;
                Teacher thr = (Teacher)request.getAttribute("myTeacher"); 
                teacherid=thr.getTeacher_id();
            %>
                <tr>
                     <td>
                        <%= thr.getTeacher_id()%>
                    </td>
                    <td>
                        <%= thr.getFirst_name()%>
                    </td>
                    <td>
                        <%= thr.getLast_name()%>
                    </td>
                    <td>
                        <%= thr.getPhone_number()%>
                    </td>
                     <td>
                        <%= thr.getAddress() %>
                    </td>
                    <td>
                        <%= thr.getCity() %>
                    </td>
                    <td>
                        <%= thr.getSalary() %>
                    </td>
                    <td><a href="teacherServlet?action=GETTEACHERBYID&thId=<%=thr.getTeacher_id()%>" >Edit</a></td>
                </tr>
        </table>
            
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
                    <td><a href="courseServlet?action=TEACHERCOURSEREMOVE&csId=<%=cou.getCourse_id()%>&tId=<%=teacherid %>" >Remove Teacher From Course</a></td>
                    <td><a href="" >Detail</a></td>
                    <input type="hidden"  name="coId" value="<%=cou.getCourse_id()%>">
                </tr>
            <%    }
            %>            
        </table>              
            <br>
            <br>            
           
            
    </body>
</html>
