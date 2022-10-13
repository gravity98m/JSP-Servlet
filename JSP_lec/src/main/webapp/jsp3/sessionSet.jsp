<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>sessionSet.jsp</h1>

	<h2>서버 (세션값 생성)</h2>
	
	<%
		// 세션값 생성(세션 영역에 속성을 생성하겠다)
		session.setAttribute("ID", "itwill");
	
		session.setAttribute("name", "BUSAN");
		
// 		session.setMaxInactiveInterval(interval);

	%>
	<script type="text/javascript">
		alert('세션값 생성완료!')
		location.href='sessionTest.jsp';
		
	</script>
</body>
</html>