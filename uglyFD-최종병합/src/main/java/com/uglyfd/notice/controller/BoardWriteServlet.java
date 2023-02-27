package com.uglyfd.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
//import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.uglyfd.common.util.FileRename;
import com.uglyfd.member.model.vo.Member;
import com.uglyfd.notice.model.service.BoardService;
import com.uglyfd.notice.model.vo.Board;

// 게시글을 작성하는 작업을 처리하는 동적 페이지
@WebServlet(name = "boardWrite", urlPatterns = { "/notice/write" })
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardWriteServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			request.getRequestDispatcher("/views/notice/write.jsp").forward(request, response);
		}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//	파일이 저장된 경로를 얻어온다.
		String path = getServletContext().getRealPath("/resources/upload/board"); // 파일의 저장경로, D or C 드라이브에 저장하고 싶다면
																					// 해당경로로 재설정하면 된다.

//	파일의 최대 사이즈 지정(10MB)
		int maxSize = 10485760;

//	파일 인코딩 설정
		String encoding = "UTF-8";

//	DefaultFileRenamePolicy : 중복되는 이름 뒤에 1 ~ 9999를 붙인다.
		MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename()); // 파입업로드를 담당하는
																										// 클래스 (cos.jar
																										// 라이브러리안에 포함된
																										// 클래스)

		Board board = new Board();

//	폼 파라미터로 넘어온 값들
		board.setBo_title(mr.getParameter("title"));
		board.setBo_writer("writer"); // 수업내용에서 board.setWriterNo(loginMember.getNo()); 대신에 적어준 코드
		board.setBo_con(mr.getParameter("content"));
//		board.setM_no(Integer.parseInt(mr.getParameter("m_no")) );

//		System.out.println(board.getM_no() + board.getBo_writer() + board.getBo_con());	

//	파일에 대한 정보를 가져올 떄
		board.setRenamed_filename(mr.getFilesystemName("upfile")); // 실제 서버에 저장되는 파일명
		board.setOriginal_filename(mr.getOriginalFileName("upfile")); // 원본 파일명

		int result = new BoardService().save(board); // JDBC에서 INSERT, UPDATE, DELETE 결과값은 정수 값으로 반환시켜주므로 int형 변수에 담아준다.

		if (result > 0) {
			request.setAttribute("msg", "게시글 등록 성공");
			request.setAttribute("location", "/notice/list");
		} else {
			request.setAttribute("msg", "게시글 등록 실패");
			request.setAttribute("location", "/notice/list");
		}
		System.out.println("BoardWriteServlet : " + board);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
