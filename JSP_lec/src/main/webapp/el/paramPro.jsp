<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>paramPro.jsp</h1>
	
	<%
		// 한글처리 - el표현식으로 나타내더라도 똑같이 한글처리를 수행해주어야 함
		request.setCharacterEncoding("UTF-8");	
		String name = request.getParameter("name");
	%>
	
	이름 : <%=name %> <br>
	이름(el표현식-jsp코드값 사용x) : ${name } <br>
	이름 (el) : ${param.name } <br>
	* el 표현식은 null값을 공백문자로 표시
	
	<%
		String[] menu = request.getParameterValues("menu");
	%>
	
	<hr>
	<h3> 오늘의 추천메뉴 </h3>
	메뉴1 : <%=menu[0] %> <br>
	메뉴2 : <%=menu[1] %> <br>
	
	<hr>
	
	[el] <br>
	메뉴1 : ${paramValues.menu[0] } <br>
	메뉴2 : ${paramValues.menu[1] } <br>
	
	<hr>
	<%
	 	String[] hobbys = request.getParameterValues("hobby");
	%>
	
	<h3> 취미정보 </h3>
<%-- 	취미1 : <%=hobbys[0] %> <br> --%> <!-- 이 식으로 인해 null이 발생하면 에러발생 -->
<%-- 	취미2 : <%=hobbys[1] %> <br> --%>
<%-- 	취미3 : <%=hobbys[2] %> <br> --%>
	
	[반복문]<br>
	
	 <% 
	 if(hobbys != null){
		 for(int i = 0; i < hobbys.length; i++){ %>
			취미 <%=i+1 %> : <%=hobbys[i] %> <br>
	<%
		} 
	}
	%>

	<% for(String h: hobbys){ %>
		취미 : <%=h %> <br>
	<% } %>
	<hr>
	
	[el]<br>	
	취미1 : ${paramValues.hobby[0] } <br>
	취미2 : ${paramValues.hobby[1] } <br>
	취미3 : ${paramValues.hobby[2] } <br>
	
</body>
</html>