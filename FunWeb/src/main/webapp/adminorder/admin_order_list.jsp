<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body>
	<div id="wrap">
		<!-- 헤더들어가는 곳 -->
		<jsp:include page="../inc/top.jsp" />
		<!-- 헤더들어가는 곳 -->

		<!-- 본문들어가는 곳 -->
		<!-- 메인이미지 -->
		<div id="sub_img_center"></div>
		<!-- 메인이미지 -->

		<!-- 왼쪽메뉴 -->
		<nav id="sub_menu">
			<ul>
				<li><a href="#">Notice</a></li>
				<li><a href="#">Public News</a></li>
				<li><a href="#">Driver Download</a></li>
				<li><a href="#">Service Policy</a></li>
			</ul>
		</nav>
		<!-- 왼쪽메뉴 -->

		<!-- 게시판 -->
		<article>
		<h1> 주문목록(관리자) </h1>
		<form action="./OrderAddAction.or" method="post">
			<table id="notice">
				<tr>
					<th class="ttitle" colspan="8">
					<h3>주문상세내역(관리자)</h3>
					</th>
				</tr>
				<tr>
					<th class="tno">주문번호</th>
					<th class="twrite">상품명</th>
					<th class="tdate">결제방법</th>
					<th class="tdate">주문금액</th>
					<th class="tdate">주문상태</th>
					<th class="tdate">주문일자</th>
					<th class="tdate">주문자(ID)</th>
					<th class="tdate">관리</th>
				</tr>
				<c:forEach var="dto" items="${adminOrderList }">
					<!-- status 상태 계산하기 -->
					<c:choose>
						<c:when test="${dto.o_status == 0 }">
							<c:set var="status" value="대기중"/>
						</c:when>
						<c:when test="${dto.o_status == 1 }">
							<c:set var="status" value="발송준비"/>
						</c:when>
						<c:when test="${dto.o_status == 2 }">
							<c:set var="status" value="발송완료"/>
						</c:when>
						<c:when test="${dto.o_status == 3 }">
							<c:set var="status" value="배송중"/>
						</c:when>
						<c:when test="${dto.o_status == 4 }">
							<c:set var="status" value="배송완료"/>
						</c:when>
						<c:when test="${dto.o_status == 5 }">
							<c:set var="status" value="주문취소"/>
						</c:when>
					</c:choose>
				<tr>
					<td>${dto.o_trade_num }</td>
					<td>${dto.o_g_name }</td>
					<td>${dto.o_trade_type }</td>
					
					<td>
					<fmt:formatNumber value="${dto.o_sum_money }"/>원
					</td>
					
					<td>${status }</td>
					<td>${dto.o_date }</td>
					<td>${dto.o_m_id }</td>
					<td>
					<a href="./AdminOrderDetail.ao?trade_num=${dto.o_trade_num }">수정</a>
					/
					삭제</td>
				</tr>
				</c:forEach>
			</table>
				<div id="table_search">
				</div>
			</form>
			<div class="clear"></div>
			<div id="page_control">
			</div>
		</article>
		<!-- 게시판 -->
		<!-- 본문들어가는 곳 -->
		<div class="clear"></div>
		<!-- 푸터들어가는 곳 -->
		<jsp:include page="../inc/bottom.jsp" />
		<!-- 푸터들어가는 곳 -->
	</div>
</body>
</html>