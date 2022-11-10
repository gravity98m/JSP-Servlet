<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<!-- iamport.payment.js --> <!-- 최신버전 확인 할 것 -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script> 
<script type="text/javascript">
	var IMP = window.IMP;   // 생략 가능
	IMP.init("imp31126701"); // 내 페이지에서 '결제 연동' > 우측상단 '내 식별코드 . API Keys'에서 확인 가능
	
	function requestPay() {
	      IMP.request_pay({ 
	          pg: "html5_inicis", // 우측 PG사 코드표에서 PG사에 맞는 코드 가져오기
	          pay_method: "card",
	          merchant_uid: "ORD20180131-0000011",   //주문번호 uk이므로 다시 체크하려면 번호를 바꿔서 결제처리할 것
	          name: "노르웨이 회전 의자",			// 상품정보
	          amount: 100,                         // 숫자타입 // 가격정보
	          buyer_email: "gildong@gmail.com",		// 구매자 정보
	          buyer_name: "홍길동",
	          buyer_tel: "010-4242-4242",
	          buyer_addr: "서울특별시 강남구 신사동",
	          buyer_postcode: "01181"
	      }, function (rsp) { // callback - 특정동작이 모두 실행되고 나서 마지막에 실행되는 것
	          if (rsp.success) {
	              // 결제 성공 시 로직,
	              alert('결제 성공!');
	          } else {
	              // 결제 실패 시 로직,
	              alert('결제 실패!');
	              console.log(rsp);
	          }
	      });
	    }
	
</script>
</head>
<body>
	<h1>importTest.jsp</h1>
	
	<button onclick="requestPay()">결제하기</button> <!-- 결제하기 버튼 생성 -->
	
</body>
</html>