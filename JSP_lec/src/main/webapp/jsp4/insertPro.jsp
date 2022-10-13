<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
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
	<h1>insertPro.jsp</h1>
	
	

	<%
		// 한글 처리	
		request.setCharacterEncoding("UTF-8");
	
		// 전달된 정보 저장하기 -> 출력하기 
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String jumin = request.getParameter("jumin");
	
	
	%>
	
	<%=name %>, <%=gender %>, <%=age %>, <%=jumin %>
	<h2> 전달받은 정보를 DB에 저장 </h2>
	
	<%
		// 0. 드라이버 설치 - 생략(프로젝트에 설치했기 때문)

		// 디비연결정보 (상수)
		final String DRIVER ="com.mysql.cj.jdbc.Driver";
		final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
		final String DBID = "root";
		final String DBPW = "1234";
		
		// 1. 드라이버 로드
		Class.forName(DRIVER);
		System.out.println("드라이버 로드 성공!");
		
		// 2. 디비 연결
		Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);
		System.out.println("디비 연결 성공!");
		System.out.println("con : " + con);
		
		// 3. SQL문 작성 & stmt 객체 생성
		// itwill_member 테이블에 정보 저장 - insert "insert into itwill_member (name, gender, age, jumin) values ( '부산','여자', 55, '900101-9999999')";
		String sql = "insert into itwill_member (name, gender, age, jumin) values ( '"+name+"', '" +gender+"', " +age+ ", '" +jumin+ "')";
		System.out.println("SQL 구문 작성 완료!");
		
		// Statement : SQL구문을 실행하도록 도와주는 객체
		Statement stmt = con.createStatement();
		System.out.println("Stmt 객체 생성 완료!");
		
		// 4.  SQL문 실행
		stmt.executeUpdate(sql);
		System.out.println("SQL 실행 완료!");


		
		// 외부로부터 name, gender, age, jumin의 값을 받아서 SQL 쿼리를 생성한다.
           // 만약 where 절에서도 이런 형태로 구성한다면 
		// ' OR ' a'='a를 입력하여 조작된 쿼리를 생성하는 문자열이 전달되어 안전하지 않은 코드가 될 수 있음
	

	%>

</body>
</html>
