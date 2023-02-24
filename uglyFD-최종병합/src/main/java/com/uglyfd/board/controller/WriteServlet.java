package com.uglyfd.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.uglyfd.board.model.service.BoardService;
import com.uglyfd.board.model.vo.Board;
import com.uglyfd.common.util.FileRename;
import com.uglyfd.member.model.vo.Member;


@WebServlet(name = "Write", urlPatterns = { "/board/write" })
public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public WriteServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
        Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
        
        if(loginMember != null) {
    	request.getRequestDispatcher("/views/board/write.jsp").forward(request, response);
	} else {
		request.setAttribute("msg", "로그인 후 작성해 주세요.");
		request.setAttribute("location", "/");
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}
    }
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
        Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
        
        if(loginMember != null) {
        	
    	String path = getServletContext().getRealPath("/resources/upload/board");
    	
    	int maxSize = 104857600;
    	
    	String encoding = "UTF-8";
    	
    	MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());
    	
    	Board board = new Board();
    	
    	
    	board.setWriterNo(loginMember.getNo());
    	board.setTitle(mr.getParameter("title"));
    	board.setContent(mr.getParameter("content"));
    	board.setRenamedFileName(mr.getFilesystemName("upfile"));
    	board.setOriginalFileName(mr.getOriginalFileName("upfile"));

        int result = new BoardService().save(board);
    	
        if(result > 0) {
        	request.setAttribute("msg", "게시글 등록 성공");
        	request.setAttribute("location", "/board/inquire");
        } else {
        	request.setAttribute("msg", "게시글 등록 실패");
        	request.setAttribute("location", "/board/inquire");
        }
    	
        } else {
    		request.setAttribute("msg", "로그인 후 작성해 주세요.");
    		request.setAttribute("location", "/");
    	}
        request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    }
}
