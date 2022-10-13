<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>javaBean1.jsp</h1>

	<!-- 아이디 정보를 입력받아서, javaBeanPro.jsp 페이지로 전달  -->
	
	<form action = "javaBeanPro.jsp" method ="post">
		아이디 : <input type="text" name ="id"> <br>
		<input type="submit" value="전달하기">
	</form>
	
</body>
</html>