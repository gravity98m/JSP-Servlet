<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>sessionLoginPro.jsp</h1>
	
	<%
		
		String id =	request.getParameter("id");
		String pw = request.getParameter("pw");
		
		String userID = "admin";
		String userPW = "1234";
		
		
		// == : 기본형타입의 값을 비교할 때 사용.
		// equals() : String 타입의 값을 비교할 때 사용
		
		if(userID.equals(id)){
			// 아이디 O
			if(userPW.equals(pw)){
				// 아이디, 비밀번호 O => 본인
				out.println("로그인 성공!");
				// 로그인 성공한 ID 정보를 유지(세션영역에 저장)
				session.setAttribute("id", id);
				
				
				// sessionMain.jsp 이동
				response.sendRedirect("sessionMain.jsp");
				
			}else{
				// 아이디 O, 비밀번호 X
				out.println("비밀번호 오류!");
			}
		}else{
			// 아이디 X
			out.println("비회원 !!");
		}
		
	%>

</body>
</html>