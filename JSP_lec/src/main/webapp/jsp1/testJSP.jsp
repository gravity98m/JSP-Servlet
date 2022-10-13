<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> JSP 파일</h1>
<!-- 	HTML + JAVA 코드 -->
	<script type="text/javascript">
		//javascript 주석문
	</script>
	
	<%
	// 스크립틀릿 - JAVA 코드 작성
	
	// 한 줄짜리 주석
	/*
		여러 줄짜리 주석
		1
		2
		3
	*/
	
	// ctrl + alt + down : 코드라인 복사
	// crtl + D : 코드라인 삭제
	// ctrl + shift + / : 여러줄 주석
	// ctrl + shift + c : 코드라인 주석 설정&삭제
	
	System.out.println("1");
	System.out.println("2");
	System.out.println("3");
	System.out.println("4");
	System.out.println("5"); 
	
	// 화면(view)에 출력 => HTML 코드
			
	out.println("아이티윌<br>"); 
	out.println("아이티윌<br>");
	out.println("아이티윌<br>");
	out.println("아이티윌<br>");
	out.println("아이티윌<br>");

	// bit - 0/1을 표현하는 공간
	// 1 byte <=> 8bit
	// 1 kB <=> 1024 byte = 2^10 byte
	// 1 MB <=> 1024*1024 byte = 2^20 byte = 1,048,576
	
	// 기본형 타입 - 8개
	
	// 논리형
	// boolean	- 1 byte
	// 문자형
	// char		- 2 byte
	// 정수형
	// byte		- 1 byte
	// short	- 2 byte
	// int		- 4 byte 
	// long		- 8 byte
	// 실수형
	// float	- 4 byte
	// double	- 8 byte
	
	
	// 반복문 - for
// 	for(초기식;조건식;증감식){
// 		반복할 코드;
// 	}
	for(int i=0;i<20;i++){
		out.print("울산"+(i+1)+"<br>"); //넘버링해보기
	}
		
	%>
	부산 부산 부산 부산
	
	<% //JSP
	for(int i=0;i<5;i++){ 
	%>
		<!-- html -->
		<h1>@@@</h1>
	<%
	//JSP
	}
	%>
	
	
</body>
</html>