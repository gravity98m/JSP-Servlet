<%@page import="java.util.ArrayList"%>
<%@page import="com.itwillbs.member.MemberDAO"%>
<%@page import="com.itwillbs.member.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>memberList.jsp</h1>

	<h2>회원정보 목록</h2>
	
	<%
		// 로그인 체크 + 관리자 여부 체크
		String id = (String)session.getAttribute("id");
		
		if(id == null || !id.equals("admin") ){
			response.sendRedirect("loginForm.jsp");
		}
		
		// DB에 저장된 회원정보를 모두 가져오기
		
		// MemberDAO 객체 생성
		MemberDAO dao = new MemberDAO();
		
		// dao 객체 안에 회원정보 전부를 조회하는 메서드 호출
		ArrayList memberList = dao.memberList();
	
		// 정보 출력
		System.out.println(memberList);
	%>
		
	
		<table border="1">
			<tr>
				<th>아이디</th>
				<th>비밀번호</th>
				<th>이름</th>
				<th>나이</th>
				<th>성별</th>
				<th>이메일</th>
				<th>회원가입일</th>
			</tr>
			<% 
				for(int i=0;i<memberList.size();i++){
					MemberBean mb = (MemberBean) memberList.get(i); // memberList 안에는 MemberBean이 저장되어있음
					
					if(mb.getId().equals("admin")){ 
						continue;
					}
			%>
			<tr>
				<td><%=mb.getId() %></td>
				<td><%=mb.getPw() %></td>
				<td><%=mb.getName() %></td>
				<td><%=mb.getAge() %></td>
				<td><%=mb.getGender() %></td>
				<td><%=mb.getEmail() %></td>
				<td><%=mb.getRegdate() %></td>
			</tr>
			<% }%>
		</table>
	
		<a href="main.jsp"> 메인페이지</a>
</body>
</html>