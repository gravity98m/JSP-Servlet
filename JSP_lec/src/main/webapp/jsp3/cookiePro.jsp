<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 서버역할 -->
	<h1>cookiePro.jsp</h1> 

 	
 	<% 
 		String lang = request.getParameter("language"); 	
 		
 	
 	
 	%>
 	전달 정보 : <%=lang %> <br>

	<%
 	// 쿠키값 생성
 	Cookie cookie = new Cookie("lang",lang);
 	// Cookie cookie = new Cookie("lang",request.getParameter("language")); 같은 형태
 	
 	// 쿠키의 유효기간 설정
 	cookie.setMaxAge(60*60); // 전달되는 데이터가 int형이면 s, long형이면 ms
 	
 	// 쿠키값을 클라이언트에 전달
 	response.addCookie(cookie);
 
 	
	%>
	
	<script type="text/javascript">
			alert('언어 쿠키정보 생성!');
			location.href="cookieForm.jsp";
	</script>

</body>
</html>