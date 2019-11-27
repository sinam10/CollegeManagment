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
                Student stu = (Student)request.getAttribute("myStudent");
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
                    <td><a href="studentServlet?action=GETSTUDENTBYID&stId=<%=stu.getStudentId()%>" >Edit</a></td>
                    <input type="hidden"  name="stId" value="<%=stu.getStudentId()%>">
                    <%  //System.out.println("stview****************************************");
                    //System.out.println(stu.getStudentId());
                    %>
                </tr>            
        </table>
            
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
