<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/subpage.css" rel="stylesheet" type="text/css">

<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->

<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]-->
<script type="text/javascript">
	function isBasket() {
		// 옵션 선택 체크 (크기, 색상)
		//alert(document.fr.size.value);
		//consolel.log(document.fr.size);
		if(document.fr.size.value == ""){ // 크기옵션 선택x
			alert('크기를 선택하시오');
			document.fr.size.focus();
			return;
		}
		if(document.fr.color.value == ""){ // 크기옵션 선택x
			alert('색상을 선택하시오');
			document.fr.color.focus();
			return;
		}
		// 장바구니 페이지 이동
		var isMove = confirm('장바구니 페이지로 이동할까요?');
		
		if(isMove){
			// 장바구니 페이지 이동
			alert('장바구니 페이지로 이동');
			document.fr.action="./BasketAddAction.ba";
			document.fr.submit();
		} else{
			// 계속 쇼핑하기 이동
			alert('계속 쇼핑하기 !');
			// 장바구니 정보 전달&저장 ./BasketAddAction.ba
			// + 쇼핑정보
			location.href="GoodsList.go"; /* 페이징처리 시에는 쿼리스트링 사용  */
		}
	} // isBasket()

	function isOrder(){
		if(document.fr.size.value == ""){ // 크기옵션 선택x
			alert('크기를 선택하시오');
			document.fr.size.focus();
			return;
		}
		if(document.fr.color.value == ""){ // 크기옵션 선택x
			alert('색상을 선택하시오');
			document.fr.color.focus();
			return;
		}
		alert('구매 페이지로 이동')		
	}
	
	
</script>
</head>
<body>
	<div id="wrap">
		<!-- 헤더가 들어가는 곳 -->
		<jsp:include page="../inc/top.jsp" />
		<!-- 헤더가 들어가는 곳 -->

		<!-- 본문 들어가는 곳 -->
		<!-- 서브페이지 메인이미지 -->
		<div id="sub_img"></div>
		<!-- 서브페이지 메인이미지 -->
		<!-- 왼쪽메뉴 -->
		<nav id="sub_menu">
			<ul>
				<li><a href="#">Welcome</a></li>
				<li><a href="#">History</a></li>
				<li><a href="#">Newsroom</a></li>
				<li><a href="#">Public Policy</a></li>
			</ul>
		</nav>
		<!-- 왼쪽메뉴 -->
		<!-- 내용 -->
		<%-- ${dto} --%>
		<article>
			<h1>ITWILL's 쇼핑몰 상세페이지</h1>
			
			<form action="" method="post" name="fr">
				<input type="hidden" name="gno" value="${dto.gno }">
			<table border="1" id="notice">
				<tr>
					<td width="310"> <!-- 강제로 사이즈를 조절하는 것보다 thumnail을 사용하는 것이 나음  -->
						<img src="./upload/${dto.image.split(',')[0] }" width="300" height="300">
					</td>
					<td>
						상품정보(구매옵션)<br>
						<h2>상품명 : ${dto.name }</h2>
						<h2>판매가격 <fmt:formatNumber value="${dto.price }"/>원</h2>
						<h2>구매수량 : <input type="number" name="amount"  value="1" min="1" max="${dto.amount }"></h2>
						<h2>(남은 수량 : ${dto.amount }개)</h2>
						<hr>
						<h2>구매옵션</h2>
						<h2>크기:
						 <select name="size">
						 	<option value="">크기를 선택하세요</option>
						 	<c:forEach items="${dto.size.split(',') }" var="size">
							 	<option value="${size }">${size}</option>
						 	</c:forEach>
						 </select> 
						 </h2>
						<h2>색상:
						 <select name="color">
						 	<option value="">색상을 선택하세요</option>
						 	<c:forTokens delims="," items="${dto.color }" var="color">
						 		<option value="${color}">${color}</option>
						 	</c:forTokens>
						 </select>
						 </h2>
						<hr>
						<h2> 총 가격 : <fmt:formatNumber value="${dto.price * 1 }"/>원 </h2>
						<h2> 
						 <a href="javascript: isBasket();" >[장바구니 담기]</a> 		
						 <a href="javascript: isOrder();">[바로 구매하기]</a> 
						</h2>
						
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<c:forTokens var="img" items="${dto.image}" delims=",">
							<c:if test="${img != 'null' }"> <!-- img가 문자열 형태이므로 null도 문자열형태로 입력  -->
								<img src="./upload/${img}" width="400" height="500">
							</c:if>
							<c:if test="${img == 'null' }">
								<img src="./upload/default.jpg" width="400" height="500">
							</c:if>
						</c:forTokens>
					</td>
				</tr>
				<tr>
					<td colspan="2">리뷰/QnA</td>
				</tr>
			</table>
			</form>
		</article>
		<!-- 내용 -->
		<!-- 본문 들어가는 곳 -->
		<div class="clear"></div>
		<!-- 푸터 들어가는 곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터 들어가는 곳 -->
	</div>
</body>
</html>



