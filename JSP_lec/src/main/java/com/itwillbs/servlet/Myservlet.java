package com.itwillbs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/itwill")
public class Myservlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" doGet() 메서드 호출 ");
		System.out.println(" GET 방식으로 주소를 호출하면 실행되는 메서드");
		
		response.getWriter().append("<h1> Hello! </h1>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" doPost() 메서드 호출 ");
		System.out.println(" POST 방식으로 주소를 호출하면 실행되는 메서드");

	
	}

}
