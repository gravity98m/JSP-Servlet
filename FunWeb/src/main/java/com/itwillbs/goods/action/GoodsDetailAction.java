package com.itwillbs.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.GoodsDTO;
import com.itwillbs.goods.db.GoodsDAO;

public class GoodsDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : GoodsDetailAction_execute 호출");
		
		// 전달정보 저장(gno)
		int gno = Integer.parseInt(request.getParameter("gno"));
		
		// DAO - getGoods(gno)
		GoodsDAO dao = new GoodsDAO();
		GoodsDTO dto = dao.getGoods(gno);
		
		// request 영역에 저장
		request.setAttribute("dto", dto);

		// 페이지 이동 (./company/goods_detail.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./company/goods_detail.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
