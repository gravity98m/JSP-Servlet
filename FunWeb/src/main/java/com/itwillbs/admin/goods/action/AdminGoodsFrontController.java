package com.itwillbs.admin.goods.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.ag")
public class AdminGoodsFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : doProcess() 호출");
		
		// 1. 가상주소 계산
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI : " + requestURI);
		String ctxPath = request.getContextPath();
		System.out.println(" C : ctxPath : " + ctxPath);
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command : " + command);
		
		System.out.println(" C : 1. 가상주소 계산 끝 \n");
		Action action = null;
		ActionForward forward = null;
		
		// 2. 가상주소 매핑(패턴1,2,3)
		if(command.equals("/AdminGoodsAdd.ag")) {
			System.out.println(" C : /AdminGoodsAdd.ag 호출 ");
			System.out.println(" C : 패턴1");
			
			forward = new ActionForward();
			forward.setPath("./center/admin_goods_write.jsp");
			forward.setRedirect(false);
		}
		else if (command.equals("/ActionGoodsAddAction.ag")) {
			System.out.println(" C : /ActionGoodsAddAction.ag 호출");
			System.out.println(" C : 패턴2");
			
			// ActionGoodsAddAction() 객체 생성
			action = new ActionGoodsAddAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminGoodsList.ag")) {
			System.out.println(" C : /AdminGoodsList.ag 호출");
			System.out.println(" C : 패턴3");
			
			// AdminGoodsList() 객체 생성
			action = new AdminGoodsListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/adminGoodsModify.ag")) {
			System.out.println(" C : /adminGoodsModify.ag 호출");
			System.out.println(" C : 패턴3) 이동x");
			
			// AdminGoodsModifyAction() 객체
			action = new AdminGoodsModifyAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminGoodsModifyPro.ag")) {
			System.out.println(" C : /AdminGoodsModifyPro.ag 호출");
			System.out.println(" C : 패턴2 "); // 보여주지 않고 이동
			
			// AdminGoodsModifyProAction() 객체
			action = new AdminGoodsModifyProAction();
			
			try {
				forward  = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/adminGoodsDelete.ag")) {
			System.out.println(" C : /adminGoodsDelete.ag 호출");
			System.out.println(" C : 패턴2"); // 삭제
			
			// AdminGoodsDeleteAction() 객체
			action = new AdminGoodsDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		System.out.println(" C : 2. 가상주소 매핑(패턴1,2,3) 끝 \n");
		// 3. 페이지 이동
		if(forward != null) {
			if(forward.isRedirect()) { // true
				System.out.println(" C : sendRedirect() : " + forward.getPath());
				response.sendRedirect(forward.getPath());
			}else {	// false
				System.out.println(" C : forward() : " + forward.getPath());
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
		System.out.println(" C : 3. 페이지 이동 끝 \n");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : doGet() ");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : doPost() ");
		doProcess(request, response);
	
	}
	
}
