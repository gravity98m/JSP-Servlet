package com.itwillbs.member.action;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.action.Action;
import com.itwillbs.member.action.ActionForward;
import com.itwillbs.member.db.MemberDAO;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberLoginAction_execute() 호출" );
		
		//  한글처리(생략)
		// 전달정보 저장(id, pw)
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		System.out.println("@@@@@ "+pw);
		
		// DAO 객체 생성 - 로그인 여부 체크메서드
		MemberDAO dao = new MemberDAO();
		int result = dao.memberLogin(id, pw);
		
		System.out.println("@@@@@ "+dao.memberLogin(id, pw));
	
		// 페이지 이동 (컨트롤러 X => 티켓 생성x)
		// JS 사용 페이지 이동
		response.setContentType("text/html; charset=UTF-8");
		// => 응답페이지의 형태를 html 형태로 사용
		
		PrintWriter out = response.getWriter();
		// =>응답페이지로 출력하는 통로를 준비
		// 체크 결과에 따른 페이지 이동(JS)
		
		if(result == 1) {
			out.print("<script>");
			out.print("alert('로그인 완료!');");
			out.print("location.href='./MemberMain.me';");
			out.print("</script>");
			out.close();
			
			return null; // 더이상 실행없이 컨트롤러로 전달
		} else {
			out.print("<script>");
			out.print("alert('!! 수정x');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
			return null;
		}			
	}

}
