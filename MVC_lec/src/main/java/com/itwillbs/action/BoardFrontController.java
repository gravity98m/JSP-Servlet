package com.itwillbs.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 컨트롤러 => 서블릿
// @WebServlet("*.bo") : 주소가 ~~~~.bo 주소의 형태면 해당 컨트롤러 처리

@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
	
	
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" BoardFrontController - doProcess() 호출");
		System.out.println(" GET / POST 방식 상관없이 한 번에 처리 ");
		
		///////////////////////1. 가상주소 계산 /////////////////////////////
		// URL => 웹 실행 주소
		// URI => URL - (프로토콜,IP,PORT번호)
//		System.out.println(request.getRequestURI());
//		System.out.println(request.getRequestURL());
		System.out.println(" C : (1단계 시작) 가상주소 계산 시작 -----------------");
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI : " + requestURI);
		
		String ctxPath = request.getContextPath();
		System.out.println(" C : ctxPath : " + ctxPath);
		
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command : " + command);
		
		System.out.println(" C : (1단계 끝) 가상주소 계산 완료 -----------------");
		///////////////////////1. 가상주소 계산 /////////////////////////////
		
		///////////////////////////2. 가상주소 매핑 /////////////////////////////
		System.out.println(" C : (2단계 시작) 가상주소 매핑 시작 -----------------");
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/BoardWrite.bo")) {
			System.out.println(" C : /BoardWrite.bo 호출");
			System.out.println(" C : [패턴1] DB사용 X, view페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./board/writeForm.jsp");
			forward.setRedirect(false);	// jsp페이지는 나오면 안되므로 forwarding 해야함
			
		}else if(command.equals("/BoardWriteAction.bo")){
			System.out.println(" C : /BoardWriteAction.bo 호출");
			System.out.println(" C : [패턴2] DB사용 O, 페이지 이동(화면전환)");
			
			// BoardWriteAction() 객체 생성
			// 강한결합 (결합도 높다)
			//BoardWriteAction bwa = new BoardWriteAction();
			// 약한결합(결합도 낮다)
			action = new BoardWriteAction(); // 타입이 다르면 업캐스팅! 상속관계!
			
			try {
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} // BoardWriteAction.bo
		else if (command.equals("/BoardList.bo")) {
			System.out.println(" C : /BoardList.bo 호출");
			System.out.println(" C : [패턴3] DB사용o, view출력 ");
			
			// BoardListAction 객체 생성
			action = new BoardListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} //BoardList.bo
		else if(command.equals("/BoardContent.bo")) {
			System.out.println(" C : /BoardContent.bo 호출");
			System.out.println(" C : [패턴3] DB사용o, view출력 ");
			
			// BoardContentAction 객체 생성
			action = new BoardContentAction();
			
			try {
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // BoardContent.bo
		else if(command.equals("/BoardUpdate.bo")) {
			System.out.println(" C : /BoardUpdate.bo 호출");
			System.out.println(" C : [패턴3] DB사용o, view출력 ");
			
			// BoardUpdateAction 객체 생성
			action = new BoardUpdateAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardUpdateProAction.bo")) {
			System.out.println(" C : /BoardUpdateProAction.bo 호출");
			System.out.println(" C : [패턴2] DB사용 O, 페이지 이동(화면전환)");
			
			// BoardUpdateProAction 객체 생성
			action = new BoardUpdateProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/BoardDelete.bo")) {
			System.out.println(" C : /BoardDelete.bo 호출");
			System.out.println(" C : [패턴1] DB사용 x, view페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./board/deleteForm.jsp");
			forward.setRedirect(false);
			
		}
		else if (command.equals("/BoardDeleteAction.bo")) {
			System.out.println(" C : /BoardDeleteAction.bo 호출");
			System.out.println(" C : [패턴2] DB사용 O, 페이지 이동(화면전환)");
			
			// BoardDeleteAction () 객체
			action = new BoardDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		else if (command.equals("/BoardReWrite.bo")) {
			System.out.println(" C : /BoardReWrite.bo 호출");
			System.out.println(" C : [패턴1] DB사용 x, view페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./board/reWriteForm.jsp");
			forward.setRedirect(false);
		}
		else if (command.equals("/BoardReWriteAction.bo")) {
			System.out.println(" C : /BoardReWriteAction.bo 호출");
			System.out.println(" C : [패턴2] DB사용 O, 페이지 이동(화면전환)");
			
			// BoardReWriteAction 객체 - execute()
			try {
				action = new BoardReWriteAction();
				forward = action.execute(request, response);
				
				//forward = new BoardReWriteAction().execute(request, response);
				// => 나쁜 코드 호출 형태(가비지 생성)
				// 비추천. new BoardReWriteAction()만 있으면 '가비지 콜'로 한 번만 사용할 수 있음. 쓸모없이 메모리만 차지해서 불필요함.
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else if (command.equals("/BoardFileWrite.bo")) {
			System.out.println(" C : /BoardFileWrite.bo 호출 ");
			System.out.println(" C : [패턴1] DB 사용 x, view페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./board/fWriteForm.jsp");
			forward.setRedirect(false);
			
		}
		else if(command.equals("/BoardFileWriteAction.bo")) {
			System.out.println(" C : /BoardFileWriteAction.bo 호출 ");
			System.out.println(" C : [패턴2] DB사용 O, 페이지 이동(화면전환)");
			
			action = new BoardFileWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(" C : (2단계 끝) 가상주소 매핑 완료 -----------------");
		///////////////////////////2. 가상주소 매핑 /////////////////////////////
		
		/////////////////////////3. 페이지 이동 ///////////////////////////////
		System.out.println(" C : (3단계 시작) 페이지 이동 시작 -----------------");
		
		if(forward != null) { // 이동정보가 있을 때(티켓이 있을때)
			
			if(forward.isRedirect()) {	// true
				System.out.println(" C : 이동방식 : "+forward.isRedirect()+", 주소 : "+forward.getPath());
				response.sendRedirect(forward.getPath());
				
			}else {	// false
				System.out.println(" C : 이동방식 : "+forward.isRedirect()+", 주소 : "+forward.getPath());
				
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
		
		
		
		
		System.out.println(" C : (3단계 끝) 페이지 이동 완료 -----------------");
		/////////////////////////3. 페이지 이동 ///////////////////////////////
	}
	
	// http://localhost:8088/MVC/board (web.xml 매핑)
	// http://localhost:8088/MVC/board2 (어노테이션)
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\n BoardFrontController - doGet() 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\n BoardFrontController - doPost() 호출");
		doProcess(request, response);
	}
	
}
