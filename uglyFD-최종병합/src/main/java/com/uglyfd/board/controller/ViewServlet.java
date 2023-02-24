package com.uglyfd.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uglyfd.board.model.service.BoardService;
import com.uglyfd.board.model.vo.Board;


@WebServlet(name = "board", urlPatterns = { "/board/view" })
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ViewServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Board board = null;
		int no = Integer.parseInt(request.getParameter("no"));
		
		System.out.println("게시물 번호 : " + no);
		
		Cookie[] cookies = request.getCookies();
		String boardHistory = "";
		boolean hasRead = false;
		
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("boardHistory")) {
					boardHistory = cookie.getValue();
					
					if(boardHistory.contains("|" + no + "|")) {
						hasRead = true;
						
						break;
					}
				}
			}
		}
		if(!hasRead) {
			Cookie cookie = new Cookie("boardHistory", boardHistory + "|" + no + "|");
			
			cookie.setMaxAge(-1);
			response.addCookie(cookie);

		}
		
		board = new BoardService().getBoardByNo(no, hasRead);
		
		System.out.println(board);
		
		request.setAttribute("board", board);
		request.getRequestDispatcher("/views/board/view.jsp").forward(request, response);
	}

}
