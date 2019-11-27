<%@page import="model.dto.Result"%>
<%@page import="model.dto.StudentCourse"%>
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
        
        <% Student stu=(Student)request.getAttribute("thestudent"); 
//        System.out.println("jsp****************************************");
//        System.out.println(stu.getFirstName());
        %>
        <form action="studentServlet" method="post">
            First Name<input type="text" name="firstName"  value="<%=stu.getFirstName()%>" required="true" ><br>
            Last Name<input type="text" name="lastName" value="<%=stu.getLastName()%>" required="true"><br>
            Phone Number<input type="text" name="phoneNumber" value="<%=stu.getPhoneNumber()%>" required="true"><br>
            Address<input type="text" name="address" value="<%=stu.getAddress()%>" required="true"><br>
            City<input type="text" name="city" value="<%=stu.getCity()%>" required="true"><br>
            Major<input type="text" name="major" value="<%=stu.getMajor()%>" required="true"><br>  
            <input type="submit" value="Save Edit">
            <input type="hidden"  name="action" value="EDITSTUDENT">
            <input type="hidden"  name="studentID" value="<%=stu.getStudentId()%>">
        </form>
        <br>
        <br>
         <table border="1">
            <tr>
                <th>Student ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Major</th>
                <th>Course ID</th>
                <th>Course Name</th>
                <th>Credit Number</th>               
            </tr>            
            <%
                List<StudentCourse> stcList = (List<StudentCourse>)request.getAttribute("courses");                
                for(StudentCourse stc : stcList){                  
            %>
                <tr>
                     <td>
                        <%= stc.getStudentId()%>
                    </td>
                    <td>
                        <%= stc.getFirstName()%>
                    </td>
                    <td>
                        <%= stc.getLastName() %>
                    </td>
                    <td>
                        <%= stc.getMajor() %>
                    </td>
                     <td>
                         <%= stc.getCourse_id() %>
                    </td>
                    <td>
                        <%= stc.getCourse_name() %>
                    </td>
                     <td>
                         <%= stc.getCredit_number() %>
                    </td>
                    <td><a href="courseServlet?action=GETCOURSEBYID&csId=<%=stc.getCourse_id()%>" >Edit Course</a></td>
                    <td><a href="courseServlet?action=STUDENTCOURSEDELETE&csId=<%=stc.getCourse_id()%>&stcId=<%=stc.getStudentId()%>" >Remove Student From Course</a></td>
                    <%  //System.out.println("stCview****************************************");
                        //System.out.println(stc.getStudentId());
                    %>
                </tr>
            <%    }
            %>            
            </table>            
            <br>
            <br>   
                   
            <table border="1">
            <tr>
                <th>Result ID</th>
                <th>Student ID</th>
                <th>Course ID</th>
                <th>Session Number</th>                
                <th>Mark</th>
               
            </tr>
            <%
                List<Result> stresultList = (List<Result>)request.getAttribute("results");                
                for(Result stc : stresultList){                  
            %>
                <tr>
                     <td>
                        <%= stc.getResult_id()%>
                    </td>
                    <td>
                        <%= stc.getStudent_id()%>
                    </td>
                    <td>
                        <%= stc.getCourse_id() %>
                    </td>
                    <td>
                        <%= stc.getSession_number() %>
                    </td>
                     <td>
                         <%= stc.getMarks() %>
                    </td>
                    
                    <td><a href="resultServlet?action=GETRESULTBYID&rsId=<%=stc.getResult_id()%>" >Edit Result</a></td> 
                    <td><a href="resultServlet?action=DELETERESULT&rstId=<%=stc.getResult_id()%>" >Remove Result</a></td>
                    <% //  System.out.println("stCview****************************************");
                        //System.out.println(stc.getStudent_id());
                    %>
                </tr>
            <%    }
            %>
            
        </table>
        
    </body>
</html>













