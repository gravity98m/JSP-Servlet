<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="jquery-3.6.1.js"></script>
<script type="text/javascript">
	/* 
		ajax - 비동기 자바스크립트와 XML(Asynchronous JavaScript And XML)
	
		동기화 <-> 비동기화
		
	 	동기화 - 서버의 데이터와 클라이언트의 데이터를 동일하게 만드는 작업
	 	 (화면 전환 o)
		
		비동기 - 서버의 데이터와 클라이언트의 데이터를 동일하게 만드는 작업
		 (화면 전환 x) // 페이지 로드 시간 단축
		
		 // 그러면 데이터 가져오는 것도 AJAX를 사용하면 어떨까?
		 
	*/
	$(function(){
		
/* 		$('input').click(function(){
			// 비동기방식 처리
			//$.ajax("test.jsp");
			$.ajax({
				url:"test.jsp",
				data: {name: "홍길동",tel:"010-1234-1234"}, // {객체 : 속성값}
				success: function(data){ // 값을 받아옴
					//alert('ajax 성공');
					//alert(data) // jsp 코드를 제외하고 전부 받아옴
					$('body').append(data);
				},
				error:function(){
					alert('ajax 실패');
				},
				complete: function(){
					alert('ajax 완전 끝');
				}
			});
		}); // click */
		
		$('#btnNews').click(function(){
			alert('뉴스 정보 가져오기');
			$.ajax({
				url: "https://fs.jtbc.co.kr//RSS/fullvideo.xml",
				success: function(data){
					alert('성공');
					console.log(data);
					$(data).find('item').each(function(){
						$('body').append("<img src='https://photo.jtbc.co.kr/news/jam_photo/202211/08/93babfb6-5746-4f5f-9cf8-aa78d44e5643.jpg'>")
						$('body').append("<a href='"+$(this).find('link').text()+"'>" + $(this).find('title').text() + "<hr></a>");
					});
				}
			});
			
		});
		
		

		
	}); // jquery 
</script>
</head>
<body>
	<h1>test5.jsp</h1>
	
	<input type="button" value="정보 불러오기">
	
	<input type="button" id="btnNews" value="뉴스 정보 불러오기">
	
	
</body>
</html>