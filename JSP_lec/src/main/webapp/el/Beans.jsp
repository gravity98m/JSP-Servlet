<%@page import="com.itwillbs.test.Phone1"%>
<%@page import="com.itwillbs.test.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Beans.jsp</h1>
	
	[jsp]
	<%
		// request 영역 저장
		//request.setAttribute("person", kim);
		Person p = (Person)request.getAttribute("person");
	%>
	
	이름 : <%=p.getName() %>
	나이 : <%=p.getAge() %>
	<%
		Phone1 iPhone = p.getP();
	%>
	휴대폰 모델명 : <%=p.getP().getModel() %>
	휴대폰 모델명 : <%=iPhone.getModel() %>
	전화번호 : <%=p.getP().getTel() %>
	전화번호 : <%=iPhone.getTel() %>
	
	<hr>
	[el] set/get 없이 변수명으로 호출 <br>
	${requestScope.person.name} <br> person.getName();
	${requestScope.person.age} <br>
	${requestScope.person} <br>
	${requestScope.person.p} <br>
    ${requestScope.person.p.model} <br>
    ${requestScope.person.p.tel} <br>
	${person.p.tel} <br>
	
	<%-- ${requestScope.person.Phone1.tel } --%>
</body>
</html>