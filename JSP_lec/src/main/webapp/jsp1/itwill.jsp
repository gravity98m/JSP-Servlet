<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>itwill.jsp</h1>
	
	<%
 		// form태그에서 한글데이터를 전달 시 post방식 한글 깨짐	(가장 먼저))
		request.setCharacterEncoding("UTF-8");
 		
 		
		// 전달된 데이터(파라미터)를 저장
		String name = request.getParameter("name"); // 
		String tel = request.getParameter("tel");
		
		// 폼태그로 전달하는 모든 파라메터는 String타입이다.

		int age = Integer.parseInt(request.getParameter("age"));
// 		String age = request.getParameter("age"); // 
		
		
		
		
	%>
	
	전달값 : <%= name %><br>
	전달값 : <%= tel %> <br>
	전달값 : <%= age %><br>
	전달값 : <%= age+100 %><br>
	
	
	
</body>
</html>