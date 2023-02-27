package com.uglyfd.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uglyfd.member.model.vo.Member;
import com.uglyfd.notice.model.service.BoardService;
import com.uglyfd.notice.model.vo.Reply;

@WebServlet(name = "noticeReply", urlPatterns = { "/notice/reply" })
public class NoticeReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NoticeReplyServlet() {
       
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 int result = 0;
	      // 로그인 체크 (직접 적용시켜 보세요.)
	      HttpSession session = request.getSession(false);
	      Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
	      
	      if (loginMember != null) {
	    	  
	    	  int boardNo = Integer.parseInt(request.getParameter("boardNo"));
	    	  String content = request.getParameter("content");
	    	  
	    	  System.out.println(loginMember);
	    	  System.out.println(boardNo);
	    	  System.out.println(content);
	    	  
	    	  
	    	  
	    	  Reply reply = new Reply();
	    	  
	    	  reply.setBoardNo(boardNo);
	    	  reply.setWriterNo(loginMember.getNo());
	    	  reply.setContent(content);
	    	  
	    	  result = new BoardService().saveReply(reply);
	    	  
	    	  if (result > 0) {
	    		  request.setAttribute("msg", "댓글 등록 성공");
	    		  request.setAttribute("location", "/notice/view?no=" + boardNo);
	    	  } else {
	    		  request.setAttribute("msg", "댓글 등록 실패");
	    		  request.setAttribute("location", "/notice/view?no=" + boardNo);
	    	  }
	    	  
	    	  request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			
		}else {
			
			request.setAttribute("msg", "로그인 후 이용가능합니다.");
			request.setAttribute("location", "/views/member/login.jsp");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			
		}
	      
	      
	      
	      

		
	}

}
