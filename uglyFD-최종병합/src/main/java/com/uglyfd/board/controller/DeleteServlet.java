package com.uglyfd.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uglyfd.board.model.service.BoardService;
import com.uglyfd.board.model.vo.Board;
import com.uglyfd.member.model.vo.Member;


@WebServlet(name = "Delete", urlPatterns = { "/board/delete" })
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
		int no = Integer.parseInt(request.getParameter("no"));
		
		System.out.println("게시글 번호 : " + no);

		result = new BoardService().delete(no);
		
		Board board = new Board();
		
     HttpSession session = request.getSession();
     Member loginMember = (session == null) ? null:(Member)session.getAttribute("loginMember");
     
     if (loginMember != null){
     
        board = new BoardService().getBoardByNo(Integer.parseInt(request.getParameter("no")), true);
     
        if (board != null && loginMember.getId().equals(board.getWriterId())) {
     
           result = new BoardService().delete(board.getNo());
           
           if (result > 0) {
              request.setAttribute("msg", "게시글 삭제 성공");
              request.setAttribute("location", "/board/inquire");
           } else {
              request.setAttribute("msg", "게시글 삭제 실패");
              request.setAttribute("location", "/board/view?no=" + no);
           }
           
           request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
        } 
     }
        
	}
}
