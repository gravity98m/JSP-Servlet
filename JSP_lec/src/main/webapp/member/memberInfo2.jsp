<%@page import="com.itwillbs.member.MemberBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>memberInfo2.jsp</h1>
	
	<h2> 로그인한 사용자의 개인정보 조회 페이지 </h2>
	
	<%
	
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		// 로그인 제어 / 회원정보
	 	String id = (String)session.getAttribute("id"); // (String) 참조형 형변환(downcasting) getArribute는 반환타입이 object이기 때문. 
														// JSP내장객체로 JSP페이지에서만 쓸 수 있음
	 	if(id == null){ //세션에서 받아온 id가 없다
	 		response.sendRedirect("loginForm.jsp");
	 	} //여기부터는 else영역이므로 굳이 추가의 로직(else구문)을 쓰지 않는다.
	 	
		// DB에서 필요한 정보 가져오기
		// 디비연결정보 (상수)
		 final String DRIVER ="com.mysql.cj.jdbc.Driver";
		 final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
		 final String DBID = "root";
		 final String DBPW = "1234";
		// 1. 드라이버 로드
		Class.forName(DRIVER);
		// 2. 디비 연결
		Connection con =  DriverManager.getConnection(DBURL,DBID,DBPW);
		// 3. SQL 작성(select) & pstmt 객체
		String sql = "select * from itwill_member where id =?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		// ???
		pstmt.setString(1,id); // 변수에 커서를 두고 f3 / ctrl누른상태에서 해당 변수(id)를 누르면 무엇과 연관되는지 알려줌
		
		// 4. SQL 실행
		ResultSet rs = pstmt.executeQuery(); // 여기는 객체가 생성되었음. 'new'는 수동적으로 생성하는 것임. 
											 //	어디론가부터 객체정보를 받아온다면 객체가 생성된다는 것이다.
											 
		// MemberBean mb = new MemberBean();  -> new를 통해 객체를 직접 생성.
		MemberBean mb = null; // 객체가 만들어졌다는 것은 new를 해야한다는 것. 객체를 저장하는 변수만 생겼다는 의미
		// 5. 데이터 실행
		if(rs.next()){
			// 회원정보 저장(MemberBean)
			// rs(DB정보) -> MemberBean
			mb = new MemberBean();    // 시스템의 효율(메모리)를 고려해서, '필요할 때' 객체를 생성하기 위해 밖에 변수를 선언하고, 지역변수를 만든다.
			
			mb.setAge(rs.getInt("age"));
			mb.setEmail(rs.getString("email"));
			mb.setGender(rs.getString("gender"));
			mb.setId(rs.getString("id"));
			mb.setName(rs.getString("name"));
			mb.setPw(rs.getString("pw"));
			mb.setRegdate(rs.getTimestamp("regdate"));
			
		}
	
	%>
	
	<h3> 아이디 : <%=mb.getId() %></h3>
	<h3> 비밀번호 : <%=mb.getPw() %> </h3>
	<h3> 이름 : <%=mb.getName() %></h3>
	<h3> 나이 : <%=mb.getAge() %></h3>
	<h3> 성별 : <%=mb.getGender() %></h3>
	<h3> 이메일 :<%=mb.getEmail() %></h3>
	<h3> 회원가입일 : <%=mb.getRegdate() %></h3>
	
	<a href="main.jsp">메인페이지</a>
	
	
	
</body>
</html>