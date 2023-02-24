package com.uglyfd.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uglyfd.member.model.service.MemberService;
import com.uglyfd.member.model.vo.Member;


@WebServlet(name = "enroll", urlPatterns = { "/enroll" })
public class EnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EnrollServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/views/member/enroll.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Member member = new Member();
		
		member.setId(request.getParameter("userId"));
		member.setPassword(request.getParameter("userPwd1"));
		member.setMail(request.getParameter("email"));
		member.setName(request.getParameter("userName"));
		member.setGender(request.getParameter("gender"));
		member.setPhone(request.getParameter("phone"));
		member.setAddr(request.getParameter("addr"));
		member.setBirth(request.getParameter("birth"));
		
		System.out.println(member);
		
		int result = new MemberService().save(member);
		
		System.out.println(result);
		
		if(result > 0) {
			request.setAttribute("msg", "UGLYFD에 오신걸 환영합니다.");
			request.setAttribute("location", "/");
			
		} else {
			request.setAttribute("msg", "회원가입에 실패하셨습니다.");
			request.setAttribute("location", "/enroll");
		}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
