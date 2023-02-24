package com.uglyfd.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uglyfd.board.model.service.BoardService;
//import com.uglyfd.board.model.vo.Board;
import com.uglyfd.board.model.vo.Reply;
import com.uglyfd.member.model.vo.Member;


@WebServlet(name = "reply", urlPatterns = { "/board/reply" })
public class ReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReplyServlet() {
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int result = 0;
		   HttpSession session = request.getSession();
		      Member loginMember = (session == null) ? null:(Member)session.getAttribute("loginMember");
		      int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		      String content = request.getParameter("content");

		      
		      Reply reply = new Reply();

		      
		      reply.setBoardNo(boardNo);
		      reply.setWriterNo(loginMember.getNo());
		      reply.setContent(content);
		      
		      result = new BoardService().saveReply(reply);
		      
	            
		            if(result > 0) {
		            	request.setAttribute("msg", "댓글 등록 성공");
		            	request.setAttribute("location", "/board/view?no=" + boardNo);
		            } else {
		            	request.setAttribute("msg", "댓글 등록 실패");
		            	request.setAttribute("location", "/board/view?no=" + boardNo);
		            }
		            request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		        	}
		        }

	