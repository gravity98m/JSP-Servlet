<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript">
$(function(){
	   // h2태그 글자색변경
	   alert($('h2').css('color'));
	 	  $('h2').css('color','#5F00FF');
	   alert($('h2').css('color'));
	   
	   // h2 태그의 글자색을 모두 다른색 변경
	   $('h2').css('color','red');
	   var colorArr = ['red','orange','yellow'];
	   $('h2').css('color',function(idx,val){
		     // 글자색 값을 표현 -> 리턴
		     // alert(idx+"/"+val);
		     return colorArr[idx];
	   });
	   
	   // 속성 여러개 한번에 사용
	   $('h2').css({
		   color:"green",
		   background:"yellow"
	   });
	   
	   // 속성 여러개 각각 적용
	   $('h2').css({
		   color:"green",
		   background:function(idx){
			   return colorArr[idx];
		   }
	   });
	   
	   
	   // attr(속성명,속성값);
	   $('img').attr('src','japan-gf0c7fa6b2_1280.jpg');
	   // 이미지 가로크기 조절(100)
	   $('img').attr('width',100);
	   
	   // 이미지 가로크기 각각 다른 크기로 조절	   
	   $('img').attr('width',function(idx){
		      return (idx+1)*100;
	   });
	   
	   // 이미지 가로/세로 크기 각각 다른 크기로 조절
	   $('img').attr({
		   width:function(a){
			   return (a+1)*100;
		   },
		   height:function(b){
			   return (b+1)*50;
		   }
	   });
	   
	   
});
</script>
</head>
<body>
	<h1>test3.jsp</h1>
	
	<h2>head - 0</h2>
	<h2>head - 1</h2>
	<h2>head - 2</h2>
	<h2>head - 2</h2>
	
	<hr>
	
	<img src="chef-g2e21e5abb_640.jpg">
	<img src="chef-g2e21e5abb_640.jpg">
	<img src="chef-g2e21e5abb_640.jpg">
</body>
</html>