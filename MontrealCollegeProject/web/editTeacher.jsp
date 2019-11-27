<%@page import="model.dto.Course"%>
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
        <h1>Edit Teacher</h1>
        
        <% Teacher stu=(Teacher)request.getAttribute("theteacher"); 
//        System.out.println("jsp****************************************");
//        System.out.println(stu.getFirstName());
        %>
        <form action="teacherServlet" method="post">
            First Name<input type="text" name="firstName"  value="<%=stu.getFirst_name()%>" required="true"><br>
            Last Name<input type="text" name="lastName" value="<%=stu.getLast_name()%>" required="true"><br>
            Phone Number<input type="text" name="phoneNumber" value="<%=stu.getPhone_number()%>" required="true"><br>
            Address<input type="text" name="address" value="<%=stu.getAddress()%>" required="true"><br>
            City<input type="text" name="city" value="<%=stu.getCity()%>" required="true"><br>
            Salary<input type="text" name="salary" value="<%=stu.getSalary()%>" min="0" max="99999999.99" step="0.01"  required="true"><br>  
            <input type="submit" value="Save Edit">
            <input type="hidden"  name="action" value="EDITTEACHER">
            <input type="hidden"  name="teacherID" value="<%=stu.getTeacher_id()%>">
        </form>
        
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
                    <td><a href="courseServlet?action=TEACHERCOURSEREMOVE&csId=<%=cou.getCourse_id()%>&tId=<%=stu.getTeacher_id() %>" >Remove Teacher From Course</a></td>
                    <td><a href="" >Detail</a></td>
                    <input type="hidden"  name="coId" value="<%=cou.getCourse_id()%>">
                </tr>
            <%    }
            %>            
        </table>              
        
    </body>
</html>
