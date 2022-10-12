<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.net.ConnectException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>loginPro.jsp</h1>
	<%
		// 한글처리
		request.setCharacterEncoding("UTF-8");
	
		// 전달정보 저장(id, pw) - 액션태그
		%>
		<jsp:useBean id="mb" class="com.itwillbs.member.MemberBean" />
		<jsp:setProperty property="*" name="mb"/>
		<%
		//로그인처리
		// 디비연결정보 (상수)
		final String DRIVER ="com.mysql.cj.jdbc.Driver";
		final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
		final String DBID = "root";
		final String DBPW = "1234";
		
		
		// 1. 드라이버 로드
		Class.forName(DRIVER);
		
		// 2. 디비 연결
		Connection con = DriverManager.getConnection(DBURL,DBID,DBPW);
		// 3. SQL 작성(select) &  pstmt 객체
		String sql = "select pw from itwill_member where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql); // pstmt에 pw들어감.
		
		// ???
		pstmt.setString(1, mb.getId());
		
		// 4. SQL 실행
		ResultSet rs = pstmt.executeQuery(); // select구문 실행한 sql을 rs에 저장
		
	 	
		// 5. 데이터 처리
		if(rs.next()){
			// 아이디에 해당하는 비밀번호가 있다 => 회원이다
			if(mb.getPw().equals(rs.getString("pw"))){   //  -> rs.getString()은 Returns:the column value; if the value is SQL NULL, thevalue returned is null
				// 입력받은 비밀번호랑 디비에 저장된 비밀번호가 같음    //으로 폼 페이지에서 아무것도 입력하지 않을 경우 null로 입력이 되는데, 
				// => 본인(로그인 성공) 								// equals에서 null을 입력받는 경우, true도 false도 아니게 되어 ERROR 발생!
				// 로그인 성공 결과 저장								// 그러므로 여기 IF문에서 else로 넘어가고 싶다면,  
				session.setAttribute("id", mb.getId());					// 'pw'입력단에 하나라도 입력이 되어야함
				// main.jsp 페이지 이동
				response.sendRedirect("main.jsp");
				
			}else{
				// 입력받은 비밀번호랑 디비에 저장된 비밀번호가 다름 
				// => 비밀번호 오류
				// 페이지 뒤로가기
				%> 
					<script type="text/javascript">
						alert('비밀번호가 다릅니다!');
						history.back();
					</script>
				<%
			}
	
		}else{
			// 아이디에 해당하는 비밀번호가 없다 => 비회원이다
			// 페이지 뒤로가기
			%> 
				<script type="text/javascript">
					alert('회원정보가 없습니다!');
					history.back();
				</script>
			<%

			
		}
		
		
		
	%>
</body>
</html>