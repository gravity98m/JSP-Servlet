package com.itwillbs.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;

public class AdminGoodsModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : adminGoodsModifyAction_execute() 호출");
		
		// 로그인 세션 제어(생략)
		// 전달정보(gno)
		int gno = Integer.parseInt(request.getParameter("gno"));
	
		// DAO - 상품정보 가져오기
		AdminGoodsDAO dao = new AdminGoodsDAO();
//		GoodsDTO dto = dao.getadminGoods(gno); // 별도의 객체에 담지 않음
		
		// request 영역에 저장
		request.setAttribute("dto", dao.getadminGoods(gno));
		
		// 페이지 이동(./center/admin_goods_modify.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./center/admin_goods_modify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
