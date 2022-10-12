<%@page import="com.itwillbs.member.MemberBean"%>
<%@page import="com.itwillbs.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>
	<h1>memberUpdate.jsp</h1>
	<h2> 회원정보 수정페이지 </h2>
	
	<%
		// 로그인 여부 체크
		String id = (String)session.getAttribute("id");
		if(id == null){
			System.out.println("memberUpdate.jsp - 로그인 X");
			response.sendRedirect("loginForm.jsp");
		}
	
		// DB에서 기존의 회원정보를 가져오기

		// DAO 객체 생성(DB 사용할 준비) <- DB작업을 한다
		MemberDAO dao = new MemberDAO();
		
		// 회원정보 조회 메서드 호출
		MemberBean mb = dao.getMember(id); // mb 객체로 리턴.
		
	
		
		if( mb != null){
	%>
	<fieldset>  <!-- disabled(태그를 사용할 수 없고, 수정불가) 와 readonly(태그는 사용할 수있지만, 수정불가) -->
		<form action="memberUpdatePro.jsp" method="post" name="fr" onsubmit="return check();">	
			아이디 : <input type="text" name="id" value="<%=mb.getId() %>" readonly> <br> 
			비밀번호 : <input type="text" name="pw" value="" placeholder="비밀번호를 입력하세요"> <br>
			이름 : <input type="text" name="name" value="<%=mb.getName()%>"> <br>
			나이 : <input type="text" name="age" value="<%=mb.getAge()%>"> <br>
			성별 : 	<input type="radio" name="gender" value="남" 
					<%if(mb.getGender().equals("남")){ %>
						checked
					<%} %>
					> 남
			  		<input type="radio" name="gender" value="여"
			  		<%if(mb.getGender().equals("여")){ %>
						checked
					<%} %>
			  		> 여 <br>
			이메일 :  <input type="text" name="email" value="<%=mb.getEmail()%>"readonly> <br>
			
			<hr><hr>
			<input type="submit" value="수정하기">
		</form>
	</fieldset>
	<%	} %>

	<script type="text/javascript">
		// js 입력된 데이터 체크
		// alert(window.document.fr);
		// alert(document.fr);
		// console.log(window.document.fr.pw.value);
		
		function check(){
			if(window.document.fr.pw.value == ""){
					alert("비밀번호를 입력하세요! ");
					document.fr.pw.focus();
					return false; // 함수의 종료
					// break; (X) -> 함수를 끝내지는 못하므로 return을 사용해야 함.
			}
		
			alert("check() 끝");
		}
		
		
	</script>
</body>
</html>