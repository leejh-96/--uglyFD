package com.uglyfd.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uglyfd.board.model.service.BoardService;
import com.uglyfd.board.model.vo.Board;
import com.uglyfd.common.util.PageInfo;

@WebServlet(name = "Inquire", urlPatterns = { "/board/inquire" })
public class InquireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public InquireServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int page = 0;
    	int listCount = 0;
    	PageInfo pageInfo = null;
    	List<Board> inquire = null;

    	try {
    		page = Integer.parseInt(request.getParameter("page"));
    	} catch (NumberFormatException e) {
    		page = 1;
    	}
    	
    	listCount = new BoardService().getBoardCount();
    	pageInfo = new PageInfo(page, 5, listCount, 10);
    	inquire = new BoardService().getBoardInquire(pageInfo);
    	
    	System.out.println(inquire);
    	
    	request.setAttribute("pageInfo", pageInfo);
    	request.setAttribute("inquire", inquire);
    	request.getRequestDispatcher("/views/board/inquire.jsp").forward(request, response);
	}

}
