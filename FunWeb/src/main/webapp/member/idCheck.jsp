<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function useID() {
		alert('아이디 사용하기');
		
		// 확인된 아이디를 회원가입창으로 전달 
// 		alert(document.ckfr.userid.value);
		opener.document.fr.id.value = document.ckfr.userid.value;
		
		//opener.document.fr.id.readonly = true;

		// 해당창 종료
		window.close();
	}
</script>
</head>
<body>
	<h1>idCheck.jsp(중복체크)</h1>
	
	<form action="./MemberIdCheckAction.me" method="post" name="ckfr">
		아이디 : <input type="text" name="userid" value="${param.inputID}" > 
		<input type="submit" value="중복확인">
	</form>

	<%-- ${requestScope.result } --%>	
	
	<c:if test="${result == 0 }">
		<h3> 사용 가능한 아이디입니다. <a href="javascript:useID();">사용하기</a> </h3>
		<input type="button" value="사용하기" onclick="useID();">
	</c:if>
	<c:if test="${requestScope.result == 1 }">
		<h3> 이미 사용 중인 아이디입니다.</h3>
	</c:if>
</body>
</html>