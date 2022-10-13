<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>testPro2.jsp</h1>
	<h2>결과처리 페이지</h2>
	
	<%
	//전달된 파라미터 값 저장(이름, 비밀번호, 성별)
	
	//한글처리
	request.setCharacterEncoding("UTF-8");
	
	
	String name = request.getParameter("name");
	String pass = request.getParameter("pass");
	String gender = request.getParameter("gender");
// 	String hobby = request.getParameter("hobby");
	
	String[] hobbys = request.getParameterValues("hobby");

	//배열이 null인지 아닌지 체크
	// null 반복문 실행 X, null 아니면 실행 O
	
	if(hobbys != null){
		for(int i=0;i<hobbys.length; i++){
			// System.out.println(hobbys[i]);
			out.println("<h3>취미"+(i+1)+" : "+hobbys[i] + "</h3>");
		}
		
		for(int i=0;i<hobbys.length; i++){
			%>
			<h3>취미 <%=i+1 %> : <%=hobbys[i] %> </h3>
			<%	
		}
	}
		
	%>
	
	<h3> 이름 : <%=name %></h3>
	<h3> 비밀번호 : <%=pass %> </h3>
	<h3> 성별 : <%=gender %> </h3>
<%-- 	<h3> 취미1 : <%=hobbys[0] %> </h3> --%>
<%-- 	<h3> 취미2 : <%=hobbys[1] %> </h3> --%>
<%-- 	<h3> 취미3 : <%=hobbys[2] %> </h3> --%>

<%
	String num = request.getParameter("num");

%>
	<h3>강의장 : <%=num %> </h3>

</body>
</html>