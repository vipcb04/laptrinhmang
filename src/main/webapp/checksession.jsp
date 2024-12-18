<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
    HttpSession ses = request.getSession(false);
    if (ses == null || ses.getAttribute("userid") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

</body>
</html>