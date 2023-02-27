package com.uglyfd.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.uglyfd.notice.model.service.BoardService;
import com.uglyfd.notice.model.vo.Board;

//게시글링크를 click시 상세조회하는 서블릿이다.
@WebServlet(name = "boardView", urlPatterns = { "/notice/view" })
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardViewServlet() {
    }

//   게시글은 로그인 없이도 조회할 수 있다.
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Board board = null;
    	
//   사용자가 click한 게시글의 번호값을 정수형으로 반환시켜서 b_no에 넘겨준다.
    	int b_no = Integer.parseInt(request.getParameter("no"));
    	
    	System.out.println("게시글 번호 : " + b_no);    	
    	
//    	1. 쿠키에 게시글을 조회한 이력이 있는지 확인
    	Cookie[] cookies = request.getCookies();	//조회수 조작을 방지하기 위한 쿠키설정
    	String boardHistory = "";
    	boolean hasRead = false;
    	
    	if(cookies != null) {
    		for(Cookie cookie : cookies) {
    			
    			if(cookie.getName().equals("boardHistory")) {
    				boardHistory = cookie.getValue();
    				
    				if(boardHistory.contains("|" + b_no + "|")) {
    					hasRead = true;		//true로 바뀌었다는 것은 이미 1번이상 읽었다는 의미이다.
    					
    					break;
    				}
    			}
    		}
    	}
//    	2. 읽지 않는 게시글이면 쿠키에 기록
    	if(!hasRead) {
    		Cookie cookie = new Cookie("boardHistory", boardHistory + "|" + b_no + "|");
    		
    		cookie.setMaxAge(-1);	//-1값을 주면 세션쿠키, 브라우저가 종료되면 쿠키가 삭제된다.
    		response.addCookie(cookie);
    	}
    	
//    게시글 번호(b_no)를 가지고 게시글을 조회하는 logic을 생성한다.
    	
    	board = new BoardService().getBoardByNo(b_no, hasRead);		//b_no(게시글번호)값을 가지고 DB에서 조회 후 결과 값을 얻어온다.
    	
    	System.out.println("view서블릿의 board : " + board);

    	request.setAttribute("board", board);				//DB조회 후 jsp페이지에 전달하기위한 request 객체 생성
    	request.getRequestDispatcher("/views/notice/view.jsp").forward(request, response);
	}

}
