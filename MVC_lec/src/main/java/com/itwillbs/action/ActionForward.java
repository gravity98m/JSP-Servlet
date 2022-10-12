package com.itwillbs.action;

public class ActionForward {
	// 컨트롤러에서 페이지 이동할 때 필요한 
	// 이동주소, 이동방법을 저장하는 객체
	private String path; // 이동주소
	private boolean isRedirect; 	// 이동방법
	// 	이동방법 - true		: redirect방식 이동
	//			 - false	: forward방식 이동
	
	public ActionForward() {
		System.out.println(" 페이지 이동 준비 (티켓 준비)");
	}
	
	// alt shift s + r => set(),get()
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() { // get() 역할. isXXX인 변수는 get메서드가 이러한 형태로 나타남
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
}
