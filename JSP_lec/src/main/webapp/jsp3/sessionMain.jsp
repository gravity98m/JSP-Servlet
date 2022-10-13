<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>sessionMain.jsp</h1>
	
	<h2> 메인페이지 </h2>
	
	<%
		// 로그인 정보가 없을 경우 사용불가
		// => 로그인 페이지 이동
		// 로그인 정보가 있을 경우 사용가능
		// => 0000님 환영합니다! 출력(화면)
		
		// 세션에 저장된 정보 가져오기
		// session.setAttribute("id", id);
		String id = (String)session.getAttribute("id");
		
		if(id == null){
			response.sendRedirect("sessionLoginForm.jsp");
		}
		
	%>

	아이디 : <%=id %>님 환영합니다! <br>
	<input type="button" value="로그아웃" onclick="location.href='sessionLogOut.jsp';">
	

</body>
</html>