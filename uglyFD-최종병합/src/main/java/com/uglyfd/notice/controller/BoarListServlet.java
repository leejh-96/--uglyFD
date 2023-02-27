package com.uglyfd.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.uglyfd.common.util.PageInfo;
import com.uglyfd.notice.model.service.BoardService;
import com.uglyfd.notice.model.vo.Board;

@WebServlet(name = "boardList", urlPatterns = { "/notice/list" })
public class BoarListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoarListServlet() {
    }

//    게시판 페이지는 로그인을 하지 않고 조회가능하도록 만든다.
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int page = 0;
    	int listCount = 0;			//listCount : 전체 리스트의 수
    	PageInfo pageInfo = null;
    	List<Board> list = null;	//listLimit 한 페이지에 표시될 리스트(게시글)의 수를 1줄 1줄 OBJECT로 받아오기 위해 list로 구현한다.
    	
    	
//   사용자가 page번호를 안주거나 숫자형태가 아니면 catch문을 통해 page를 1로 지정해 준다.
    	try {
    		page = Integer.parseInt(request.getParameter("page"));    		
    	}catch(NumberFormatException e) {
    		page = 1;
    	}
    	
    	listCount = new BoardService().getBoardCount();
    	pageInfo = new PageInfo(page, 10, listCount, 10);
    	list = new BoardService().getBoardList(pageInfo);
    	
    	System.out.println("BoardListServlet : " + list);
    	
    	request.setAttribute("pageInfo", pageInfo);
    	request.setAttribute("list", list);		//list라는 이름으로 list 객체를 담아서 보내준다.
    	request.getRequestDispatcher("/views/notice/list.jsp").forward(request, response);
	}

}
