package com.itwillbs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TestServlet1 extends HttpServlet{
	
	// http://localhost:8088/JSP/test1  (get)
	// alt shift s + v
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 호출");
		
		doPost(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() 호출");

		// request 영역에 정보 저장
		//request.setAttribute("cnt", new Integer(1000);
		request.setAttribute("cnt", 1000);
		
		
		//response.getWriter().append("<h1> HTML 메세지 작성하기 </h1>");
		
//		response.setContentType("text/html; charset=UTF-8");
//		
//		PrintWriter out = response.getWriter();
//		out.print("<h1> HTML 메세지 작성하기2222 </h1>");
//		out.close();
		
		// forward 방식 - 포워딩
		//	1. 페이지 이동시 주소 변경 X , 화면 변경 O
		// 	2. request 내장객체 정보를 전달가능
		// <jsp:forward> (JSP 코드 X)
		
		RequestDispatcher dis 
			= request.getRequestDispatcher("./el/Attribute.jsp"); // 이동할 주소
		
		dis.forward(request, response);
		
		
	}
	
	
}
