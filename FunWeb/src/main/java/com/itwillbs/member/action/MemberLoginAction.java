package com.itwillbs.member.action;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
//		System.out.println("@@@@@ "+pw); // 파라미터 전달되는지 확인
		
		// DAO 객체 생성 - 로그인 여부 체크메서드
		MemberDAO dao = new MemberDAO();
		int result = dao.memberLogin(id, pw);
	
		System.out.println(result + "@@@@@@@@@@@@@@@@@@@");
		
		// 체크 결과에 따른 페이지 이동(JS) -> js로 사용하기 위해 우선 형태를 변경해주어야 함
		if(result == 0) {
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('비밀번호 오류!');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null; // 더이상 실행없이 컨트롤러로 전달
		} 
		if(result == -1) {
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('회원 정보 없음!');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		}
		// result == 1 
		// 로그인 성공 -> 아이디 세션영역에 저장
		HttpSession session = request.getSession(); // java 파일에서는 session 내장객체가 없어서 생성.
		session.setAttribute("id", id);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.me");
		forward.setRedirect(true);		
		return forward;
	}
}
