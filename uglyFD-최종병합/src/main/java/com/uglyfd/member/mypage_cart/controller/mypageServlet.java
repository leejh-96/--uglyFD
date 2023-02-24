package com.uglyfd.member.mypage_cart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uglyfd.member.model.vo.Member;


@WebServlet(name = "mypage", urlPatterns = { "/mypage/mypage" })
public class mypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public mypageServlet() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Member loginmember = (session == null) ? null : (Member) session.getAttribute("loginmember");
		
		
		if(loginmember != null) {
			request.getRequestDispatcher("/views/mypage/mypage.jsp").forward(request, response);			
		} else {
			request.setAttribute("msg", "로그인 후 이용해 주세요.");
			request.setAttribute("location", "/");
			
			
		request.getRequestDispatcher("/views/mypage/mypage.jsp").forward(request, response);
	}
	}
	

}
