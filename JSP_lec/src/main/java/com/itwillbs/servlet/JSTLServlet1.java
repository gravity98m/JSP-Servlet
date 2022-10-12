package com.itwillbs.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.MemberBean;

@WebServlet("/jstl1")
public class JSTLServlet1 extends HttpServlet{

	// http://localhost:8088/JSP_lec/jstl1
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// MemberBean 객체를 사용해서 사용자 한 명의 정보 생성
		// 사용자의 정보를 core_set2.jsp 페이지에서 출력
		// (id, pw, name,age,gender,email,regdate)
		MemberBean mb = new MemberBean();
		
		mb.setId("abc123");
		mb.setPw("1234");
		mb.setName("김회원");
		mb.setAge(20);
		mb.setGender("여");
		mb.setEmail("abc123@email.com");
		mb.setRegdate(new Timestamp(System.currentTimeMillis()));
		
		//request 영역에 저장
		request.setAttribute("memberBean", mb);
		
		// 회원정보 2개 더 생성 (총 3개) => ArrayList에 저장 => set2.jsp 출력(마지막 회원정보만 출력)
		List memberList = new ArrayList();
		
		for(int i=0;i<3;i++) {
			MemberBean mb2 = new MemberBean();
			mb2.setId("abc123"+i);
			mb2.setPw("1234"+i);
			mb2.setName("김회원"+i);
			mb2.setAge(20+i);
			mb2.setGender("여");
			mb2.setEmail("abc123"+i+"@email.com");
			mb2.setRegdate(new Timestamp(System.currentTimeMillis()));
			
			memberList.add(mb2);
		}
			System.out.println(memberList);
			
			request.setAttribute("memberList", memberList);
		
//		MemberBean mb2 = new MemberBean();
//		mb2.setId("def123");
//		mb2.setPw("1234");
//		mb2.setName("김회이");
//		mb2.setAge(20);
//		mb2.setGender("여");
//		mb2.setEmail("def123@email.com");
//		mb2.setRegdate(new Timestamp(System.currentTimeMillis()));
//
//		MemberBean mb3 = new MemberBean();
//		mb3.setId("ghi123");
//		mb3.setPw("1234");
//		mb3.setName("김회삼");
//		mb3.setAge(20);
//		mb3.setGender("남");
//		mb3.setEmail("ghi123@email.com");
//		mb3.setRegdate(new Timestamp(System.currentTimeMillis()));
//		
//		List members = new ArrayList();
//		
//		members.add(mb);
//		members.add(mb2);
//		members.add(mb3);
//		
//		request.setAttribute("members", members);
		
		
		
		// forward
		RequestDispatcher dis = request.getRequestDispatcher("./jstl/core_set2.jsp");
		dis.forward(request, response);
		
		
		
	
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
}
