package com.itwillbs.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MemberFrontController extends HttpServlet {

	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" Member - doProcess() ");
		
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
		if(command.equals("/MemberJoin.me")) {
			System.out.println(" C : /MemberJoin.me 호출");
			System.out.println(" C : [패턴1] DB 사용X, view 이동");
			
			forward = new ActionForward();
			forward.setPath("./member/join.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/MemberJoinAction.me")) {
			System.out.println(" C : /MemberJoinAction.me 호출 ");
			System.out.println(" C : [패턴2] DB사용o, 페이지 이동");
			
			// MemberJoinAction() 객체
			action = new MemberJoinAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberLogin.me")) {
			System.out.println(" C : /MemberLogin.me 호출");
			System.out.println(" C : [패턴1] DB 사용X, view 이동");
		
			forward = new ActionForward();
			forward.setPath("./member/login.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/MemberIdCheck.me")) {
			System.out.println("MemberIdCheck.me 호출");
			System.out.println(" C : [패턴1] DB 사용X, view 이동");
			
			forward = new ActionForward();
			forward.setPath("./member/idCheck.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/MemberIdCheckAction.me")) {
			System.out.println(" C : /MemberIdCheckAction.me 호출");
			System.out.println(" C : [패턴3] DB사용ㅇ, view출력");
			
			// MemberIdCheckAction 객체
			action = new MemberIdCheckAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/MemberLoginAction.me")) {
			System.out.println(" C : /MemberLoginAction.me 호출");
			System.out.println(" C : [패턴2] DB사용o, 페이지 이동");
			
			// MemberLoginAction 객체 
			action = new MemberLoginAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else if(command.equals("/Main.me")) {
			System.out.println(" C : /Main.me 호출 ");
			System.out.println(" C : [패턴1] DB사용X, view이동");
			
			forward = new ActionForward();
			forward.setPath("./main/main.jsp");
			forward.setRedirect(false);			
		}
		else if(command.equals("/MemberLogout.me")) {
			System.out.println(" C : /MemberLogout.me 호출");
			System.out.println(" C : [패턴2] 비지니스로직, view이동"); // 작업을 수행 O -> 비지니스 로직

			// MemberLogoutAction 객체
			action = new MemberLogoutAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberInfo.me")) {
			System.out.println(" C : /MemberInfo.me 호출");
			System.out.println(" C : [패턴3] DB사용O, 페이지출력");
			
			// MemberInfoAction 객체
			action = new MemberInfoAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberUpdate.me")) {
			System.out.println(" C : /MemeberUpdate.me 호출");
			System.out.println(" C : [패턴3] DB사용O, 페이지출력");
			
			// MemberUpdateAction 객체
			action = new MemberUpdateAction();
			
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
		System.out.println(" Member - doGet()");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" Member - doPost()");
		doProcess(request, response);
	}

	
}
