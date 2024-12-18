<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="checksession.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload Document</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .main-container {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            color: #333;
            text-align: center;
        }
        form {
            margin-top: 20px;
        }
        input[type="file"] {
            display: block;
            margin: 10px auto;
        }
        input[type="submit"] {
            display: block;
            width: 200px;
            margin: 20px auto;
            padding: 10px;
            background-color: #333;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #555;
        }
    </style>
</head>
<body>
    <%@ include file="header.jsp" %>
    <div class="main-container">
        <h2>Upload PDF Document for Conversion and Analysis</h2>
        <form action="upload" method="post" enctype="multipart/form-data">
            <input type="file" name="file" accept=".pdf" multiple required>
            <input type="submit" value="Upload and Process">
        </form>
    </div>
</body>
</html>