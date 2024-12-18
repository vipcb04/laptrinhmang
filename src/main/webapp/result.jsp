<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="model.bean.Documents"%>
<%@page import="java.util.List"%>
<%@ include file="checksession.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document Processing Results</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f4;
        }
        .container {
            width: 80%;
            margin: auto;
            overflow: hidden;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            color: #333;
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .btn {
            display: inline-block;
            padding: 5px 10px;
            background-color: #333;
            color: #fff;
            text-decoration: none;
            border-radius: 3px;
        }
        .btn:hover {
            background-color: #555;
        }
    </style>
</head>
<body>

<% List<Documents> listdoc =(List<Documents>)request.getAttribute("listdoc"); %>
    <div class="container">
        <h2>Document Processing Results</h2>
        <table>
            <tr>
                <th>Document Name</th>
                <th>Status</th>
                
                <th>Download</th>
                 <th>Delete</th>
            </tr>
            <%for(Documents doc : listdoc) {%>
                <tr>
                    <td><%=doc.getFileName()%></td>
                    <td><%=doc.getStatus() %></td>
                    
                    <td>
                       		<%if(doc.getStatus().equals("Completed")){ %>
                            <a href="ResultServlet?id=<%=doc.getId() %>" class="btn">Download</a>
                            <%} %>
                        
                    </td>
                    <td><%if(doc.getStatus().equals("Completed")){ %>
                            
                             <a href="ResultServlet?deleteID=<%=doc.getId() %>" class="btn">Delete</a>
                            <%} %></td>
                </tr>
            <%} %>
        </table>
    </div>
</body>
</html>