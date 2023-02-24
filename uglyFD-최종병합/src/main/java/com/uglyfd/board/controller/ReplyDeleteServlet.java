package com.uglyfd.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.uglyfd.board.model.service.BoardService;
//import com.uglyfd.board.model.vo.Board;
//import com.uglyfd.board.model.vo.Reply;
//import com.uglyfd.member.model.vo.Member;


@WebServlet(name = "replydelete", urlPatterns = { "/reply/delete" })
public class ReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ReplyDeleteServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
		int no = Integer.parseInt(request.getParameter("no"));
		
		System.out.println("댓글 번호 : " + no);
		
		result = new BoardService().replydelete(no);
           
           if (result > 0) {
              request.setAttribute("msg", "댓글 삭제 성공");
              request.setAttribute("location", "/board/view?no=" + no);
           } else {
              request.setAttribute("msg", "댓글 삭제 실패");
              request.setAttribute("location", "/board/view?no=" + no);
           }
           
           request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
        } 
     
        
	}
