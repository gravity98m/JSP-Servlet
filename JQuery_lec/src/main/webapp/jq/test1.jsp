<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 외부CDN링크 사용 -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script> -->

<!-- 직접 파일 추가  -->
<script src="jquery-3.6.1.js"></script>
<script type="text/javascript">
	// $(document).ready(); // 문서가 html코드를 다 읽고 준비되었을 때
	
	alert('1111');
	$(document).ready(function(){
		alert('222');
	}); 
	
	$(function(){
		alert('4444');
	});
	
	jQuery(document).ready(function(){
	alert('5555');
		
	});
	
	jQuery(function(){
	alert('666');
		
	});
		alert('3333');
</script>
</head>
<body>
	<h1>test1.jsp</h1>
	
	
</body>
</html>