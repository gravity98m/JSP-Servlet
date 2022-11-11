package com.itwillbs.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.basket.db.BasketDAO;
import com.itwillbs.basket.db.BasketDTO;

public class BasketAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BasketAddAction_execute()");
		
		// 세션제어(id)
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		} // 갔다가 로그인하고 다시 상품페이지로 돌아오도록 할 것
		
		// 장바구니 정보 저장 (구매상품정보)
		// 1) 전달정보 저장
		BasketDTO dto = new BasketDTO(); 
		dto.setB_g_amount(Integer.parseInt(request.getParameter("amount")));
		dto.setB_g_color(request.getParameter("color"));
		dto.setB_g_num(Integer.parseInt(request.getParameter("gno")));
		dto.setB_g_size(request.getParameter("size"));
		dto.setB_m_id(id);
		// detail페이지에서 name파라미터 확인
		// 장바구니 번호도 계산해서 사용할 예정
		
		System.out.println(" M : "+dto);
		
		// 2) DB에 저장
		// - 기존에 동일옵션의 정보가 있는지 체크
		BasketDAO dao = new BasketDAO();
		boolean isUpdate = dao.checkBasket(dto);
		
		// - O : 수량 update 	x : 정보 insert
		if(!isUpdate) {
			// 정보 추가(insert)
			dao.basketAdd(dto);
			System.out.println(" M : 장바구니 추가");
		}
		// 사용자의 선택에 따라서 장바구니 페이지로 이동/상품리스트로 이동
		String isMove = request.getParameter("isMove");
		System.out.println(" M : isMove : "+isMove);
		
		if(isMove.equals("true")) {
			// 페이지 이동
			forward.setPath("./BasketList.ba");
			forward.setRedirect(true);
		}else {
			// 페이지 이동
			forward.setPath("./GoodsList.go");
			forward.setRedirect(true);
			
		}
		
		
		return forward;
	}

}
