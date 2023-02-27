package com.uglyfd.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uglyfd.notice.model.service.BoardService;

@WebServlet(name = "boardDelete", urlPatterns = { "/notice/delete" })
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDeleteServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
		// 로그인 체크 & 본인 게시글 여부 확인 (직접 적용시켜 보세요.)
		int no = Integer.parseInt(request.getParameter("no"));
		
		System.out.println("게시글 번호 : " + no);
		
//	BOARD게시판의 STATUS속성을 N으로 바꿔줌으로써 게시글을 삭제하는 것처럼 보이게 한다.
		result = new BoardService().delete(no);
		
		if(result > 0) {
			request.setAttribute("msg", "게시글 삭제 성공");
			request.setAttribute("location", "/notice/list");
		} else {
			request.setAttribute("msg", "게시글 삭제 실패");
			request.setAttribute("location", "/notice/view?no=" + no);
		}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
