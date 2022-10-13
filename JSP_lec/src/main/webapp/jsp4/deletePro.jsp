<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="com.mysql.cj.jdbc.Driver"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>deletePro.jsp</h1>
	
	<%
		// 회원정보 삭제
			
		
		/*
			1. 회원정보가 필요 (deleteForm.jsp 전달, 한글처리)
			request.ge
			
			2. 디비에서 삭제
			
			3. 드라이버 로드
			
			4. 디비 연결
			
			5. SQL 작성(select) & pstmt 객체
			
			6. SQL 실행
			
			7. 데이터가 있을 경우에만 회원 삭제
			
			8. SQL 작성(delete) & pstmt 객체
			
			9. SQL 실행
		
		*/
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		
		// 전달정보 저장
		String name = request.getParameter("name");
		String jumin = request.getParameter("jumin");
		
		// 디비 연결 정보 (상수)
		final String DRIVER = "com.mysql.cj.jdbc.Driver";
		final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
		final String DBID = "root";
		final String DBPW = "1234";
		
		// 1.드라이버 로드
		Class.forName(DRIVER);
		
		// 2. 디비 연결
		Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);
		
		// 3. SQL 작성(select) & pstmt 객체  
		// sql의 select구문을 작성할 때 조건절은 보통 id를 사용하여 만듦(지금은 DB에 있는 데이터가 적기때문에 name을 사용 )
		String sql = "select jumin from itwill_member where name = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		// ???
		pstmt.setString(1, name);
		
		// 4. SQL 실행
		ResultSet rs = pstmt.executeQuery();
		
		// 5. 데이터 처리
		
		if(rs.next()){
			// 회원
			if(jumin.equals(rs.getString("jumin"))){
				// 회원-주민번호 동일 => 삭제
				// 2. SQL & pstmt
				sql = "delete from itwill_member where jumin=?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, jumin);
				
				// 4. SQL 실행
				pstmt.executeUpdate();
			}
			
		} else {
			// 비회원
		}
		
	%>
</body>
</html>