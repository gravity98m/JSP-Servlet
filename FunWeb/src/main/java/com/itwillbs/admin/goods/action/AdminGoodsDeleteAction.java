package com.itwillbs.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;
import com.itwillbs.admin.goods.db.GoodsDTO;

public class AdminGoodsDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


		System.out.println(" M : AdminGoodsDeleteAction_execute() 호출");
		
		int gno = Integer.parseInt(request.getParameter("gno"));
		
		AdminGoodsDAO dao = new AdminGoodsDAO();
		dao.adminRemoveGoods(gno);
		
		ActionForward forward = new ActionForward();
		forward.setPath("AdminGoodsList.ag");
		forward.setRedirect(true);
		
		return forward;
	}

}
