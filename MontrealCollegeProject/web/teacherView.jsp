<%@page import="model.dto.Teacher"%>
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
                List<Teacher> thrList = (List<Teacher>)request.getAttribute("teachers");                
                for(Teacher stu : thrList){                  
            %>
                <tr>
                     <td>
                        <%= stu.getTeacher_id()%>
                    </td>
                    <td>
                        <%= stu.getFirst_name()%>
                    </td>
                    <td>
                        <%= stu.getLast_name()%>
                    </td>
                    <td>
                        <%= stu.getPhone_number()%>
                    </td>
                     <td>
                        <%= stu.getAddress() %>
                    </td>
                    <td>
                        <%= stu.getCity() %>
                    </td>
                    <td>
                        <%= stu.getSalary() %>
                    </td>
                    <td><a href="teacherServlet?action=GETTEACHERBYID&thId=<%=stu.getTeacher_id()%>" >Edit</a></td>
                    <td><a href="teacherServlet?action=REMOVETEACHER&thrId=<%=stu.getTeacher_id()%>" >Delete</a></td>
                    <td><a href="teacherServlet?action=DETAILTEACHER&tchId=<%=stu.getTeacher_id()%>" >Detail</a></td>                    
                    <%  //System.out.println("stview****************************************");
                    //System.out.println(stu.getStudentId());
                    %>
                </tr>
            <%    }
            %>
            
        </table>
    </body>
</html>
