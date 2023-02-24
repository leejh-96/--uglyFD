package com.uglyfd.board.controller;

import java.io.File;
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


@WebServlet(name = "update", urlPatterns = { "/board/update" })
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
        Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
        
        if(loginMember != null) {
        	Board board = new BoardService().getBoardByNo(Integer.parseInt(request.getParameter("no")), true);
        	
    	if(board != null && loginMember.getId().equals(board.getWriterId())) {
    		request.setAttribute("board", board);
    		request.getRequestDispatcher("/views/board/update.jsp").forward(request, response);
    	} else {
    		request.setAttribute("msg", "잘못된 접근입니다..");
    		request.setAttribute("location", "/board/inquire");
    		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    	}
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
    	
    	Board board = new BoardService().getBoardByNo(Integer.parseInt(mr.getParameter("no")), true);

    	board.setTitle(mr.getParameter("title"));
    	board.setContent(mr.getParameter("content"));
    	
    	String originalFileName = mr.getOriginalFileName("upfile");
    	String filesystemName = mr.getFilesystemName("upfile");
    	
    	if(originalFileName != null && filesystemName != null) {
    		File file = new File(path + "/" + board.getRenamedFileName());
    		
    		if(file.exists()) {

    			board.setOriginalFileName(originalFileName);
    			board.setRenamedFileName(filesystemName);
    		}
    		
    		int result = new BoardService().save(board);
    		
    		if(result > 0) {
    			request.setAttribute("msg", "게시글 수정 성공");
    			request.setAttribute("location", "/board/view?no=" + board.getNo());
    		} else {
    			request.setAttribute("msg", "게시글 수정 실패");
    			request.setAttribute("location", "/board/update?no=" + board.getNo());	    			
    		}
    	} else {
    		request.setAttribute("msg", "잘못된 접근입니다.");
    		request.setAttribute("location", "/board/inquire");				
		}	    	
	} else {
		request.setAttribute("msg", "로그인 후 수정해 주세요.");
		request.setAttribute("location", "/");			
	}
	
	request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
}
}