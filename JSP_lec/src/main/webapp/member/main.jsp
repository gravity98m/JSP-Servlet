<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>main.jsp</h1>
	
	<h2> 메인페이지 </h2>
	
	<%
		// 로그인을 해야만 정보를 확인가능한 페이지
		// 로그인X ->  사용자가 페이지 사용 X
		// => 로그인 페이지로 이동
		// 로그인O -> "ㅇㅇㅇㅇ"님 환영합니다.
		
	 	String id = (String)session.getAttribute("id"); // (String) 참조형 형변환(downcasting) getArribute는 반환타입이 object이기 때문
		
	 	if(id == null){ //세션에서 받아온 id가 없다
	 		response.sendRedirect("loginForm.jsp");
	 	}
	
		
	%>
	
	
	<%=id %>님 환영합니다! <br>
	
	<input type="button" value="로그아웃" onclick="location.href='logOut.jsp';">
	
	<a href="logOut.jsp">로그아웃2</a>
	<!-- a태그는 디자인적인면 때문에 버튼이 아닌 것으로 페이지를 부르기 위해 사용할 때 이러한 형태로 사용할 수 있다. -->
	<a href="javascript:location.href='logOut.jsp';">로그아웃3</a> 
	
	<hr><hr>
	
	<h4><a href="memberInfo.jsp">회원정보 조회</a></h4>
	
	
	<h4><a href="memberUpdate.jsp">회원정보 수정</a></h4>
	
	
	<h4><a href="memberDelete.jsp">회원정보 삭제</a></h4>
	
	
	<h4><a href="../board/writeForm.jsp">게시판 글쓰기</a></h4>
	<hr><hr>
	
	<% 
	// JSP 페이지는 실행 순서
	// JSP(java) - HTML - JS
	
	//jsp 코드가 처음부터 끝까지 실행될 때 null 비교불가
	// => 객체 레퍼런스를 사용할 때는 항상 null 값 체크
	
// 	if(id != null){
// 		if(id.equals("admin")){
	
	if(id != null && id.equals("admin")){
// 	if(id.equals("admin") && id != null){ -> null을 먼저 비교해야 함.
			%>
				<!-- 관리자 전용 메뉴  -->
				<h4><a href="memberList.jsp">회원정보 목록</a></h4>
			<% 
		
	} 
	%>
	
	
</body>
</html>