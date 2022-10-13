
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>Test2.jsp</h1>
	
	<h2> 객체 (Object) </h2>
	=> 내 눈 앞에 보이는 모든 대상
	
	객체 추상화
	
	객체 - 1) 속성 : 객체의 특징을 표현하기 위한 정보
				=> 변수로 표현
		   2) 동작 : 객체가 수행하는 모든 행동
		   		=> 함수(메서드) 표현
	
	
	
	<h2> 내장 객체 (p177~) </h2>
		=> JSP 페이지 -> 서블릿 클래스 필요한 정보(객체)를 미리 생성
		
		<h3> javax.servlet 패키지</h3> -> 톰캣 안에 들어있는 정보
			request : 클라이언트의 HTTP요청정보를 저장한 객체 <br>
			response : HTTP요청에 해당하는 응답정보를 저장한 객체 <br>
			session : 클라이언트의 세션정보를 저장한 객체 <!--  서버와 연결해서 서버에 왔던 사람이라는것을 나타내는 것 --><br>
			pageContext : 페이지 실행에 필요한 컨텍스트정보를 저장한 객체 <!--  (프로젝트정보) --> <br>
			out : 응답페이지를 전송하기 위한 출력스트림 객체 <br>
			application : 동일한 애플리케이션의 컨텍스트 정보를 저장한 객체 (모든 정보를 가진)<br>
			config : 페이지에 필요한 서블릿 설정정보(초기화) <br>
			page : 해당 페이지의 서블릿 객체 <br>
		
		<h3> javax.lang 패키지</h3> -> JDK 설치할 때 들어있는 정보
			exception : 예외 처리 객체 <br>

		<hr>

		
		<h3> request 객체 </h3>	
		
		서버 도메인명 : <%=request.getServerName() %> <br>
		URL : <%=request.getRequestURL() %> <br>
		URI : <%=request.getRequestURI() %> <br> <!-- 프로토콜, 도메인, 포트번호를 제외한 나머지 -->
		클라이언트 IP주소 : <%=request.getRemoteAddr() %> <br> <!-- ipv6로 인해 0:0:0:0:0:0:0:1로 나타남  -->
		프로젝트의 물리적 경로 : <%=request.getRealPath("/") %>
		
		<h3> response 객체 </h3>	
		<%
			// response.setHeader(arg0, arg1);
			// response.addCookie(cookie);
			// response.sendRedirect("http://www.naver.com"); // 바로 네이버로 연결됨
			// response.setContentType("test/html; charset=UTF-8");
		
// 			response.addHeader("Refresh", "3; url=Test1.jsp"); // 새로고침. 새로고침되면서 이동. 주로 결제화면
			
		%>
		
		<h3> session 객체 </h3>	
		
		세션 ID 값 : <%=session.getId() %> <br> <!-- 세션은 브라우저당 1개씩 생성 -->
		세션 생성시간 : <%=session.getCreationTime() %> <br> <!-- 단위 : [ms] -->
		<%=new Date(session.getCreationTime()) %> <br>
		세션 최종접속시간 : <%=session.getLastAccessedTime() %> <br>
		세션 유지시간 (1000초, 30분) : <%=session.getMaxInactiveInterval() %>s <br>
		
		<%
			session.setMaxInactiveInterval(600);
		%>
		
		
</body>
</html>