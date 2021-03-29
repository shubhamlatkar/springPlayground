<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.spring_mvn.dto.Employee, java.util.List "%>
<html lang="en">
<head>
 <meta charset="utf-8">
 <title>Hello Page</title>
 <meta name="description" content="The HTML5 Herald">
 <meta name="author" content="SitePoint">
 <style>
     table {
       font-family: arial, sans-serif;
       border-collapse: collapse;
       width: 100%;
     }

     td, th {
       border: 1px solid #dddddd;
       text-align: left;
       padding: 8px;
     }

     tr:nth-child(even) {
       background-color: #dddddd;
     }

     form {
        margin: 1rem 0;
     }
 </style>
</head>
<body>
    <h1>Welcome to hello page</h1>

    <% if(request.getAttribute("id") != null) {%>
        <h2>Individual Values </h2>
        <p><% out.println("id -> " + request.getAttribute("id"));%>
        <p><% out.println("name -> " + request.getAttribute("name"));%>
        <p><% out.println("salary -> " + request.getAttribute("salary"));%>
    <% } %>

    <% if(request.getAttribute("testEmp") != null) {%>
        <h2>Employee as object</h2>
        <% out.println(request.getAttribute("testEmp")); %>
    <% } %>
    <h2>Emp list</h2>
    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Salary</th>
            </tr>
        </thead>
        <tbody>
        <%
            List<Employee> employees = (List<Employee>) request.getAttribute("employeeList");
            for(Employee e:employees) {
        %>
            <tr>
                <td><%out.println(e.getId());%></td>
                <td><%out.println(e.getName());%></td>
                <td><%out.println(e.getSalary());%></td>
            <tr>
        <% } %>
        </tbody>
    </table>

    <form action="addEmployee" method="post">
         <div>
             <label>Id</label>
             <input type="text" name="id" />
         </div>
         <div>
              <label>Name</label>
              <input type="text" name="name" />
         </div>
         <div>
           <label>Salary</label>
           <input type="text" name="salary" />
         </div>
         <div>
           <input type="submit" name="AddEmployee" />
         </div>
    </form>
</body>
</html>