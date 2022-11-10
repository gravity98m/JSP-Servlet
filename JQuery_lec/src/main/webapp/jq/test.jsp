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
	System.out.println("text.jsp 실행!!");
	System.out.println(request.getParameter("name"));
%>

<%= request.getParameter("name") %>
<%-- ${param.name } --%>
</body>
</html>