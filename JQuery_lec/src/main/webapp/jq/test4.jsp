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
    // html(),text()
    var h = $('h2').html(); // 해당 대상(요소) 처음만나는 값을 1개 가져옴
    alert(h);
    var t = $('h2').text(); // 해당 대상(요소) 모든 값을 가져옴
    alert(t);
    
    // 해당요소의 값을 변경(모두)
    //$('h2').html('Hello Itwill');
    //$('h2').text('Hello Itwill');
    
    // 전달되는 값이 HTML 태그의 경우
    //$('h2').html('<h1>Hello Itwill'+t+'</h1>'); // 태그가 적용
//	    $('h2').text('<h1>Hello Itwill</h1>'); // 태그가 적용 X,단순텍스트
    
    $('h2').html(function(idx,html){
    	return html+"///"+idx+"@@@@@@@@@";
    });
    
    
    // append(), prepend()
    $('body').append("추가!!!!!!!!!!!!!!!!!!!");
    $('body').prepend("@@@@@@@@@@@@@@@@@@@@@@@@@");
    
//	    $('div').html(function(idx,html){
//	    	return html+idx;
//	    });
	$('div').append(function(i){
		return i;
	});
	
	// 배열(2차원배열 형태) => JSON타입
	var arr = 
		[ 
			{name:"학생1",addr:"부산"},
			{name:"학생2",addr:"울산"},
			{name:"학생3",addr:"서울"},
			{name:"학생4",addr:"제주"},
			{name:"학생4",addr:"대전"}
		];
    	for(var i=0;i<arr.length;i++){
			$('table').append("<tr><td>"+arr[i].name+"</td><td>"+arr[i].addr+"</td></tr>");
    	}
    	
    	//each()
    	//$(요소).each(function(){});
    	//$.each(요소,function(){});
    	
    	// 배열의 값을 테이블에 추가
    	$(arr).each(function(idx,item){
//     		alert('1');
// 			alert(idx+"/"+item); 
			console.log(item); // 배열의 객체값
			$('table').append("<tr><td>"+item.name+"</td><td>"+item.addr+"</td></tr>");
    	});
    	
    	// h3태그 배경색 파란색 변경
    	//$('h3').css('background','blue');
    	
    	// addClass() : 요소에 클래스명을 추가
    	
    	//$('h3').addClass('high_0');
/*     	$('h3').addClass(function(idx){
    		return 'high_'+idx;
    	}); */
		
    	$('h3').each(function(idx){
    		//alert(idx);
    		$(this).addClass('high_'+idx); 
    		// this - 이벤트가 발생한 요소
    			
    	});
    	
    	$('#btn2').click(function(){
    		alert('클릭!');
    	});
    	
});
</script>
<style type="text/css">
/* 	h3{
		background: red;
	} */
	
	.high_0 {background: olive;}
	.high_1 {background: aqua;}
	.high_2 {background: fuchsia;}
	.high_3 {background: lime;}
	.high_4 {background: maroon;}
	
</style>
</head>
<body>
	<h1>test4.jsp</h1>
	
	<h2>head-0</h2>
	<h2>head-1</h2>
	<h2>head-2</h2>
	<h2>head-3</h2>
	<h2>head-4</h2>
	
	<hr>
	<div>div</div>
	<div>div</div>
	<div>div</div>
	<div>div</div>
	<div>div</div>
	
	<table border="1">
		<tr>
			<td>이름</td>
			<td>주소</td>
		</tr>
	</table>
	
	<hr>
<!-- 	<h3 style="background: red">item-0</h3>
	<h3 class="high_1">item-1</h3>
	<h3>item-2</h3>
	<h3 class="high_3">item-3</h3>
	<h3>item-4</h3> -->
	
	<h3>item-0</h3>
	<h3>item-1</h3>
	<h3>item-2</h3>
	<h3>item-3</h3>
	<h3>item-4</h3>
	
	<hr>
	<input type="button" value="버튼1" onclick="alert('버튼 클릭');">
	<input type="button" value="버튼2" id="btn2">
	
</body>
</html>