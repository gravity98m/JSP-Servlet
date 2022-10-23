<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>admin_goods_list.jsp</h1>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>카테고리</td>
			<td>상품명</td>
			<td>상품정보</td>
			<td>사이즈</td>
			<td>색상</td>
			<td>수량</td>
			<td>가격</td>
			<td>상품이미지</td>
			<td>등록일자</td>
		</tr>
		<c:forEach var="goods" items="${goodsListAll}">
			<tr>	
				<td>${goods.gno}</td>
				<td>${goods.category}</td>
				<td>${goods.name}</td>
				<td>${goods.content}</td>
				<td>${goods.size}</td>
				<td>${goods.content}</td>
				<td>${goods.amount}</td>
				<td>${goods.price}</td>
				<td>${goods.image}</td>
				<td>${goods.date}</td>
			</tr>
		</c:forEach>
		</tr>
		
	</table>
</body>
</html>