package com.uglyfd.notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.uglyfd.common.util.FileRename;
import com.uglyfd.notice.model.service.BoardService;
import com.uglyfd.notice.model.vo.Board;

@WebServlet(name = "boardUpdate", urlPatterns = { "/notice/update" })
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardUpdateServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//    	HttpSession session = request.getSession(false);
//		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");

//    	if(loginMember != null) {	
		Board board = new BoardService().getBoardByNo(Integer.parseInt(request.getParameter("no")), true);
		System.out.println("updateservlet board : " + board.getB_no()+", "+board.getBo_con());
//    	if(board != null || loginMember.getNo() == board.getB_no()) {
		request.setAttribute("board", board);
		request.getRequestDispatcher("/views/notice/update.jsp").forward(request, response);
//    	}else {
//			request.setAttribute("msg", "잘못된 접근입니다.");
//			request.setAttribute("location", "/board/list");			
//			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
//    		} else {
//			request.setAttribute("msg", "로그인 후 수정해 주세요.");
//			request.setAttribute("location", "/");			
//			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
//		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
//    	HttpSession session = request.getSession(false);
//		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
		
//		if(loginMember != null) {
//		파일이 저장된 경로를 얻어온다.
			String path = getServletContext().getRealPath("/resources/upload/board");	//파일의 저장경로, D or C 드라이브에 저장하고 싶다면 해당경로로 재설정하면 된다.
			
//		파일의 최대 사이즈 지정(10MB)
			int maxSize = 10485760;
			
//		파일 인코딩 설정
			String encoding = "UTF-8";
			
//		DefaultFileRenamePolicy : 중복되는 이름 뒤에 1 ~ 9999를 붙인다.
			MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());	//파입업로드를 담당하는 클래스  (cos.jar 라이브러리안에 포함된 클래스)
						
			Board board = new Board();
			
			System.out.println("업데이트서블릿"+board.getB_no());
			board.setB_no(Integer.parseInt(mr.getParameter("no")));
			board.setBo_title(mr.getParameter("title"));
			board.setBo_con(mr.getParameter("content"));
			
			String originalFileName = mr.getOriginalFileName("upfile");
			String fileSystemName = mr.getFilesystemName("upfile");
			
			if(originalFileName != null && fileSystemName != null) {
//				기존에 업로드된 파일 삭제
				File file = new File(path +"/" + board.getRenamed_filename());
				
				if(file.exists()) {
					file.delete();
				}
				
//			변경된 파일로 업데이트하는 작업
				board.setOriginal_filename(originalFileName);
				board.setRenamed_filename(fileSystemName);
			}
			
			int result = new BoardService().save(board);	//영향받은 행의 개수를 return해준다.
			
			if(result > 0) {
				request.setAttribute("msg", "게시글 수정 성공");
				request.setAttribute("location", "/notice/view?no=" + board.getB_no());
			}else {
				request.setAttribute("msg", "게시글 수정 실패");
				request.setAttribute("location", "/notice/update?no=" + board.getB_no());
			}
			
			System.out.println(Integer.parseInt(mr.getParameter("no")));
			System.out.println(mr.getParameter("title"));
			System.out.println(mr.getParameter("writer"));
			System.out.println((mr.getParameter("content")));
//		}else {			
//				request.setAttribute("msg", "로그인 후 수정해 주세요.");
//				request.setAttribute("location", "/");			
//			}
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
