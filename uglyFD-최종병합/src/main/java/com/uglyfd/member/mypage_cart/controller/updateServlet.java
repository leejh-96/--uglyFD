package com.uglyfd.member.mypage_cart.controller;

import java.io.IOException;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uglyfd.member.model.service.MemberService;
import com.uglyfd.member.model.vo.Member;

@WebServlet(name = "myPageServlet", urlPatterns = { "/mypage/update" })
public class updateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public updateServlet() {
        
    }



	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
		Member member = null;
		
		HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
		
		if (loginMember != null) {
			member = new Member();
						
			member.setNo(loginMember.getNo());
			member.setPassword(request.getParameter("userPwd1"));
			member.setMail(request.getParameter("email"));
			member.setName(request.getParameter("userName"));
			member.setBirth(request.getParameter("birth"));
//			member.setGender(request.getParameter("gender"));
			member.setPhone(request.getParameter("phone"));
			member.setAddr(request.getParameter("addr"));
			
			result = new MemberService().save(member);
			
			if (result > 0) {
				session.setAttribute("loginMember", new MemberService().findMemberById(member.getId()));
				
				request.setAttribute("msg", "회원 정보 수정 완료");
				request.setAttribute("location", "/login");
			}else {
				
				request.setAttribute("msg", "회원 정보 수정 실패");
				request.setAttribute("location", "/mypage/myrevise");
			}
			
	} else {
			request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");			
		}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}
		
		
		
		
	}



