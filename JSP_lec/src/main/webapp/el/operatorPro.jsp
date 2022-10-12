<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>operatorPro.jsp</h1>
	
	<h2> 전달정보 저장 </h2>
	[JSP]
	<%
		int num1 = Integer.parseInt(request.getParameter("num1")); 
		int num2 = Integer.parseInt(request.getParameter("num2")); 
	%>
	숫자 1 : <%=num1 %>, 숫자 2 : <%=num2 %>
	
	<hr>
	[el] : el표현식으로 전달받은 파라메터는 자동 캐스팅됨<br>
 	숫자1 : ${param.num1 }, 숫자2 : ${param.num2 } <br>
 	숫자1 : ${param.num1+999999 }, 숫자2 : ${param.num2+9999999 } <br>
	
	<hr>
	[el] : num1 + num2 출력 <br>
	${param.num1 } + ${param.num2 } = ${param.num1 + param.num2 }
	
	[el] : num1, num2 값이 모두 양수인가? (t/f) ${param.num1 > 0 && param.num2 > 0 }
	<br>
	[el] : num1, num2 값이 모두 양수인가? ("양수"/"양수가 아님") 
		   ${param.num1 > 0 && param.num2 > 0 ? "양수" : "양수가 아님" }
		   ${param.num1 gt 0 && param.num2 gt 0 ? "양수" : "양수가 아님" }
	<br>
	
	[el] : ${empty param.id }   empty 값 : 값이 null일 경우 true/ 아닐 경우 false
	if( id != null){} 
	<hr>
	
	<h2>EL 연산자 : p516 ~517</h2>
	
	비교연산자		논리연산자
	== eq			&& 		and
	!= ne			||		or
	<  lt			!		not
	>  gt
	<= le
	>= ge
	
</body>
</html>