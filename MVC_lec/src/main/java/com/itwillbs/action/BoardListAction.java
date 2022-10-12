package com.itwillbs.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.db.BoardDAO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardListAction_execute() 호출 ");
		
		// BoardDAO 객체 생성
		BoardDAO dao = new BoardDAO();
		
		// 게시판 전체 글 개수 확인
		int cnt = dao.getBoardCount();
	
		System.out.println(" M : 전체 글 개수 : " +cnt+ "개");
		
		////////////////////////////////////////////////////////////////////////////
		//페이징 처리(1) -  페이지에서 몇 개를 보여줄 것인지
				
		// 한 페이지에 보여줄 글의 개수
		int pageSize = 5;
		
		// http://localhost:8088/JSP/board/boardList.jsp?pageNum=2
		// 서버가 켜져있을 때 ctrl을 누르면서 링크를 클릭하면 페이지로 이동됨. -> 주소창에 직접 입력하는 동작과 동일.
		
			
		// 현 페이지가 몇 페이지인지 확인
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
		pageNum = "1";
		}
		
		// 시작행 번호 계산하기 	1	11	21	31	41 ...
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize+1;
		
		// 끝행 번호 계산하기 	10	20	30	40 ...
		int endRow = currentPage * pageSize;
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////

		// 디비에 전체 글 리스트 가져오기
//		ArrayList boardListAll = dao.getBoardList();
		ArrayList boardListAll = dao.getBoardList(startRow,pageSize);
		
		/////////////////////////페이징 처리 (2)- 몇 페이지 인지//////////////////////////////////////////////
				
		// 글이 있을 때
		
		// 총 페이지 = 글 개수(총량) / 페이지당 출력
		// 	=> 만약, 나머지가 있을 때 페이지 1개 추가
		
		// 전체 페이지 수
		int pageCount =(cnt/pageSize)+(cnt%pageSize==0? 0:1);
		
		// 한 화면에 보여줄 페이지 수
		int pageBlock = 5;
		
		// 페이지블럭의 시작번호	1 ~ 10 => 1, 	11 ~ 20 => 11	21 ~ 30 => 21
		int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
		// 페이지블럭의 끝번호
		int endPage = startPage+pageBlock-1 ;
		
		if(endPage > pageCount){
		endPage = pageCount;
		}
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//System.out.println(" M : "+boardListAll);
		// 직접출력 -> 위임(대신 출력) view .jsp페이지에서 출력
		// Action -> jsp페이지 정보 전달 (request 영역객체 저장)
		request.setAttribute("boardListAll", boardListAll);
		//request.setAttribute("boardListAll", dao.getBoardList()); -> 최종 목적. 추가 데이터를 더하지 않는 경우 이 방식을 사용하는 것이 나음.
		request.setAttribute("totalCnt", cnt);
		
		// 페이징처리 정보 저장
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("totalCnt", cnt);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		// 페이지 이동준비 (티켓 생성)
		// ./board/boardList.jsp
		ActionForward forward = new ActionForward();
		forward.setPath("./board/boardList.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}
	
}
