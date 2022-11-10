<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript">
	$(document).ready(function(){
		// 선택자   // 위에서부터 순서대로 진행되므로 마지막에는 하위의 코드가 적용됨
		$('h2').css('color','RED'); // 태그 선택자
		$('*').css('color','blue'); // * - 전체(모든 요소)
		$('.title1').css('color','orange'); // . - 클래스
		$('#title2').css('color','pink');  // # - 아이디 
		
		/* js - 아이디값 가져오기  */
		//document.폼태그명.요소명.value;
		// alert($('input').val());
		$('input').val('아이티윌');
		alert($('input').val());
		
		// 비밀번호 - 12345 입력
		// $('input#pw').val('12345')
		$('input[type=password]').val('123456789');
		//alert($('input#pw').val());
		alert($('input[type=password]').val());
		
		// 위치탐색 선택자 // odd, even은 인덱스를 기준으로 함(0부터 시작)
		
		$('tr:odd').css('background','red');
		$('tr:even').css('background','blue');
		$('tr:first').css('background','yellow');
		
		
	});
</script>
</head>
<body>
	<h1>test2.jsp</h1>
	
	<h2 class="title1">제목1</h2>
	<h2 id="title2">제목2</h2>
	<form action="">
		<label for="id">아이디 : </label><input type="text" name="id" id="id"><br>
		비밀번호 : <input type="password" name="pw" id="pw"><br>
	</form>
	<hr>
	<table border="1">
		<tr>
			<td>1</td>
			<td>2</td>
			<td>3</td>
		</tr>
		<tr>
			<td>1</td>
			<td>2</td>
			<td>3</td>
		</tr>
		<tr>
			<td>1</td>
			<td>2</td>
			<td>3</td>
		</tr>
		<tr>
			<td>1</td>
			<td>2</td>
			<td>3</td>
		</tr>
	</table>
	
</body>
</html>