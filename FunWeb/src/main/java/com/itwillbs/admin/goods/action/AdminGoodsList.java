package com.itwillbs.admin.goods.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;
import com.itwillbs.admin.goods.db.GoodsDTO;

public class AdminGoodsList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : AdminGoodsList_execute 호출");
		
		// 세션제어(관리자여부)
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		if(id == null || !id.equals("admin")) {
			response.sendRedirect("./Main.me");
		}
		// DAO - 상품정보를 모두 가져오는 메서드
		AdminGoodsDAO dao = new AdminGoodsDAO();
		
		ArrayList goodsListAll = dao.getGoodsList();

		// 상품정보를 request 영역에 저장
		request.setAttribute("goodsListAll", goodsListAll);
		
		// 페이지 이동(./center/admin_goods_list.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./center/admin_goods_list.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
