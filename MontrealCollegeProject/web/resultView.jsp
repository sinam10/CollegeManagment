<%@page import="java.util.ArrayList"%>
<%@page import="model.dto.ResultDetail"%>
<%@page import="model.dto.Course"%>
<%@page import="java.util.List"%>
<%@page import="model.dto.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result List</title>
    </head>
    <body>
        <% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
        response.setHeader("Pragma", "no-cache"); 
        response.setDateHeader("Expires", 0); %>
        <jsp:include page="header.jsp">
            <jsp:param name="subTitle" value="Results"/>
        </jsp:include>
        <%@include  file="MainMenu.jsp" %>
        <br>
        <br>
            
            <%
                System.out.println("in rview*********************************");
                System.out.println(session.getAttribute("user_r"));
                System.out.println(session.getAttribute("user_id"));
                List<Course> csList = (List<Course>)request.getAttribute("cs"); 
                List<ResultDetail> rList = (List<ResultDetail>)request.getAttribute("allresult");
                for(Course cs : csList){ %>
                    
                
                
        <% if(session.getAttribute("user_r").equals("manager-script") || session.getAttribute("user_r").equals("admin")) { %>        
            <h2><%=cs.getCourse_name() %></h2>
            <table border="1">
                <tr>
                    <th>Result ID</th>
                    <th>Student ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Course ID</th>
                    <th>Course Name</th>
                    <th>Session Number</th>
                    <th>Mark</th>               
                </tr>
                    
              <%   for(ResultDetail rsdtl : rList){
                   if(cs.getCourse_id()== rsdtl.getCourse_id()) {
            %>
                    <tr>
                     <td>
                        <%= rsdtl.getResult_id()%>
                    </td>
                    <td>
                        <%= rsdtl.getStudent_id()%>
                    </td>
                    <td>
                        <%= rsdtl.getFirstName() %>
                    </td>
                    <td>
                        <%= rsdtl.getLastName() %>
                    </td>
                     <td>
                         <%= rsdtl.getCourse_id() %>
                    </td>
                    <td>
                        <%= rsdtl.getCourse_name()%>
                    </td>
                    <td>
                        <%= rsdtl.getSession_number() %>
                    </td>
                    <td>
                        <%= rsdtl.getMarks() %>
                    </td>
                    <td><a href="resultServlet?action=GETRESULTBYID&rsId=<%=rsdtl.getResult_id()%>" >Edit</a></td>
                    <td><a href="resultServlet?action=DELETERESULT&rstId=<%=rsdtl.getResult_id()%>" >Delete</a></td>
                    <input type="hidden"  name="stId" value="<%=rsdtl.getStudent_id()%>">
                </tr>               
                <br>
            <%    }}
            %>
             </table>             
             <% } 
                else  if(session.getAttribute("user_r").equals("teacher")) { 
                List<Integer> l=(List<Integer>)request.getAttribute("teacherCs");
                for(Integer li : l){
//                  if(request.getAttribute("teacherCs") == cs.getCourse_id()) { 
                 if(li == cs.getCourse_id()) {    %>
                <h2><%=cs.getCourse_name() %></h2>
            <table border="1">
                <tr>
                    <th>Result ID</th>
                    <th>Student ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Course ID</th>
                    <th>Course Name</th>
                    <th>Session Number</th>
                    <th>Mark</th>               
                </tr>
                    
              <%   for(ResultDetail rsdtl : rList){
                   if(cs.getCourse_id()== rsdtl.getCourse_id()) {
            %>
                    <tr>
                     <td>
                        <%= rsdtl.getResult_id()%>
                    </td>
                    <td>
                        <%= rsdtl.getStudent_id()%>
                    </td>
                    <td>
                        <%= rsdtl.getFirstName() %>
                    </td>
                    <td>
                        <%= rsdtl.getLastName() %>
                    </td>
                     <td>
                         <%= rsdtl.getCourse_id() %>
                    </td>
                    <td>
                        <%= rsdtl.getCourse_name()%>
                    </td>
                    <td>
                        <%= rsdtl.getSession_number() %>
                    </td>
                    <td>
                        <%= rsdtl.getMarks() %>
                    </td>
                    <td><a href="resultServlet?action=GETRESULTBYID&rsId=<%=rsdtl.getResult_id()%>" >Edit</a></td>
                    <td><a href="resultServlet?action=DELETERESULT&rstId=<%=rsdtl.getResult_id()%>" >Delete</a></td>
                    <input type="hidden"  name="stId" value="<%=rsdtl.getStudent_id()%>">
                </tr>               
                <br>
            <%    }}
            %>
             </table> 
             <% }}} else  if(session.getAttribute("user_r").equals("student")) { 
//                System.out.println(cs.getCourse_id());
//                System.out.println(session.getAttribute("studentCs"));
                List<Integer> lt=(List<Integer>)request.getAttribute("studentCs");
                for(Integer l : lt){
//                if(request.getAttribute("studentCs") == cs.getCourse_id()) {
                 if(l == cs.getCourse_id()) {    %>
            <h2><%=cs.getCourse_name() %></h2>
            <table border="1">
                <tr>
                    <th>Result ID</th>
                    <th>Student ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Course ID</th>
                    <th>Course Name</th>
                    <th>Session Number</th>
                    <th>Mark</th>               
                </tr>
                    
              <%   for(ResultDetail rsdtl : rList){
                   if(cs.getCourse_id()== rsdtl.getCourse_id()) {
            %>
                    <tr>
                     <td>
                        <%= rsdtl.getResult_id()%>
                    </td>
                    <td>
                        <%= rsdtl.getStudent_id()%>
                    </td>
                    <td>
                        <%= rsdtl.getFirstName() %>
                    </td>
                    <td>
                        <%= rsdtl.getLastName() %>
                    </td>
                     <td>
                         <%= rsdtl.getCourse_id() %>
                    </td>
                    <td>
                        <%= rsdtl.getCourse_name()%>
                    </td>
                    <td>
                        <%= rsdtl.getSession_number() %>
                    </td>
                    <td>
                        <%= rsdtl.getMarks() %>
                    </td>
                    <td><a href="resultServlet?action=GETRESULTBYID&rsId=<%=rsdtl.getResult_id()%>" >Edit</a></td>
                    <td><a href="resultServlet?action=DELETERESULT&rstId=<%=rsdtl.getResult_id()%>" >Delete</a></td>
                    <input type="hidden"  name="stId" value="<%=rsdtl.getStudent_id()%>">
                </tr>               
                <br>
            <%    }}
            %>
             </table> 
             <% }}} %>
                  
             <% } %>
    </body>
</html>
