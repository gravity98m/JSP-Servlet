package com.itwillbs.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.basket.db.BasketDAO;
import com.itwillbs.goods.db.GoodsDAO;
import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class OrderAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : OrderAddAction_execute() ");
		
		// 사용자 정보(세션제어 - 로그인 체크)
		// 한글처리(생략)
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 전달된 주문 정보( 배송지+결제 )
		request.getAttribute("o_r_addr2");
		request.getAttribute("o_r_msg");
		MemberDAO mDAO = new MemberDAO();
		MemberDTO memberDTO = mDAO.getMember(id);
		
		// 주문 상품정보(장바구니 + 상품정보)
		BasketDAO bkDAO = new BasketDAO();
		
		// 결제 호출 (JAVA 코드) // JAVA -> 모두 처리하고 되돌아옴 /ajax로 구현 -> 결과만 받아옴
		
		// 주문정보 저장
		
		// 메일,문자 전송 (책의 메일 구현 참고)
		new Thread(new Runnable() { // 멀티스레드 + 익명객체
			@Override
			public void run() {
				for(long i=0;i<1000000000L;i++); // 카운팅만 함(시간의 텀을 주기 위해)
					System.out.println("메일 전송 완료");
			}
		}).start();
		
		System.out.println(" 주문정보 저장완료! ");
		// 상품 개수 수정(판매량)
		
		// 장바구니 정보 삭제
		
		// 페이지 이동
		
		return null;
	}

}
