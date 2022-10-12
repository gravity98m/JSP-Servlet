<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>logOut.jsp</h1>
	
	<%
		// 로그아웃 => 세션정보 초기화	
		session.invalidate();
	
	%>
		
		<script type="text/javascript">
			alert(' 회원님의 정보가 안전하게 로그아웃 되었습니다. ');
			location.href="main.jsp"; // 여기에 로그아웃을 체크하는 로직이 있기 때문에 이를 확인하기 위해 main.jsp페이지로 이동시킨다.
		</script>
			
</body>
</html>