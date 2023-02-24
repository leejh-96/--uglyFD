package com.uglyfd.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uglyfd.admin.model.service.ProductService;
import com.uglyfd.admin.model.vo.Admin_member;

@WebServlet(name = "adminmemberupdate", urlPatterns = { "/admin/member/update" })
public class AdminMemberUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public AdminMemberUpdate() {
       
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int result = 0;
		
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String addr = request.getParameter("addr");
		String mail = request.getParameter("mail");
		String phone = request.getParameter("phone");
		
		result = new ProductService().adminMemberUpdate(memberNo,id,password,addr,mail,phone);
		
		if (result > 0) {
			System.out.println("회원정보 최종 수정에 성공했습니다.");
			request.setAttribute("msg", "회원 정보 수정에 성공했습니다.");
			request.setAttribute("location", "/views/admin/admin_members.jsp");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}else {
			System.out.println("회원정보 수정에 최종 실패했습니다.");
			request.setAttribute("msg", "회원 정보 수정에 실패했습니다.");
			request.setAttribute("location", "/views/admin/admin_members.jsp");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
	}

}
