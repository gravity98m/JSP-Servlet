package com.itwillbs.basket.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.ba")
public class BasketFrontController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" Basket - doProcess() ");
		
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
		
		// 2. 가상주소 매핑 끝
		if(command.equals("/BasketAddAction.ba")) {
			System.out.println(" C : /BasketAddAction.ba 호출");
			System.out.println(" C : [패턴2]");
			
			// BasketAddAction() 
			action = new BasketAddAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BasketList.ba")) {
			System.out.println(" C : /BasketList.ba 호출");
			System.out.println(" C : [패턴3]");
			
			// BasketListAction() 
			action = new BasketListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BasketDeleteAction.ba")) {
			System.out.println(" C : /BasketDeleteAction.ba 호출");
			System.out.println(" C : [패턴2] "); // 리다이렉트
			
			// BasketDeleteAction() 생성
			action = new BasketDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		System.out.println(" C : 2. 가상주소 매핑 끝 \n");
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
		System.out.println(" Basket - doGet()");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" Basket - doPost()");
		doProcess(request, response);
	}
	
	
}
