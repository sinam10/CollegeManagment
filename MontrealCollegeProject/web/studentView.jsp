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
        <table border="1">
            <tr>
                <th>Student ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                 <th>Phone Number</th>
                <th>Address</th>
                <th>City</th>
               
            </tr>
            
            <%
                System.out.println("user_r*****************************");
                System.out.println(session.getAttribute("user_r"));
                List<Student> stuList = (List<Student>)request.getAttribute("students");                
                for(Student stu : stuList){                  
            %>
                <tr>
                     <td>
                        <%= stu.getStudentId()%>
                    </td>
                    <td>
                        <%= stu.getFirstName()%>
                    </td>
                    <td>
                        <%= stu.getLastName() %>
                    </td>
                    <td>
                        <%= stu.getPhoneNumber() %>
                    </td>
                     <td>
                        <%= stu.getAddress() %>
                    </td>
                    <td>
                        <%= stu.getCity() %>
                    </td>
                    <% if(session.getAttribute("user_r").equals("manager-script") || session.getAttribute("user_r").equals("admin")) { %>
                    <td><a href="studentServlet?action=GETSTUDENTBYID&stId=<%=stu.getStudentId()%>" >Edit</a></td>
                    <td><a href="studentServlet?action=DELETESTUDENT&stdId=<%=stu.getStudentId()%>" >Delete</a></td>
                    <td><a href="studentServlet?action=DETAILSTUDENT&stdtId=<%=stu.getStudentId()%>" >Detail</a></td>
                     <% } %>
                    <input type="hidden"  name="stId" value="<%=stu.getStudentId()%>">                   
                </tr>
            <%    }
            %>
            
        </table>
    </body>
</html>
