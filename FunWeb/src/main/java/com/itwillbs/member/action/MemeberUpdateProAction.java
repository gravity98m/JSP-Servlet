package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemeberUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemeberUpdateProAction_execute()");
		
		// 한글처리(필터)
		// 전달된 데이터 저장(수정정보)
		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("email"));
		dto.setAddr(request.getParameter("addr"));
		dto.setTel(request.getParameter("tel"));
		String birth = request.getParameterValues("birth")[0]+"-"+request.getParameterValues("birth")[1]+"-"+request.getParameterValues("birth")[2];
		System.out.println(" M : "+birth);
		dto.setBirth(request.getParameter("birth"));
		dto.setGender(request.getParameter("gender"));
		
		System.out.println(" M : " +dto);
		
		// DAO - 회원정보 수정
		MemberDAO dao = new MemberDAO();
		int result = dao.updateMemeber(dto);
		
		System.out.println(" M : result : "+result);
		
		// 페이지 이동(js)
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(result == -1) {
			out.print("<script>");
			out.print("alert('회원정보 없음');");
			out.print("history.back()");
			out.print("</script>");
			out.close();
			
		}else if(result == 0){
			out.print("<script>");
			out.print("alert('비밀번호 오류');");
			out.print("history.back()");
			out.print("</script>");
			out.close();
			
		}else { // result == 1
			out.print("<script>");
			out.print("alert('비밀번호 오류');");
			out.print("history.back()");
			out.print("</script>");
			out.close();
			
		}
		
		return null;
	}

}
