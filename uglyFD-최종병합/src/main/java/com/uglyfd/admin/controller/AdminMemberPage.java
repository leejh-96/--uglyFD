package com.uglyfd.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uglyfd.admin.model.service.ProductService;
import com.uglyfd.admin.model.vo.Admin_member;

@WebServlet(name = "adminmemberpage", urlPatterns = { "/admin/member/page" })
public class AdminMemberPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public AdminMemberPage() {

    }
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int result = 0;
		
		int memberNo = Integer.parseInt(request.getParameter("memberDelete"));
	
		System.out.println(memberNo);
	
		result = new ProductService().memberDelete(memberNo);
	
		System.out.println(result);
		if (result > 0) {
			System.out.println("멤버 삭제에 최종 성공했습니다.");
			request.getRequestDispatcher("/views/admin/admin_members.jsp").forward(request, response);
		}else {
			System.out.println("멤버 삭제에 최종 실패했습니다.");
			request.getRequestDispatcher("/views/admin/admin_members.jsp").forward(request, response);
		}
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String phone = request.getParameter("phone");
		
		Admin_member amember = null;
		
		amember = new ProductService().findByAdmin_Member(name, id, phone);
		
		if (amember != null) {
			System.out.println("member가 null이 아닙니다. 수정하기로 넘어가겠습니다.");
			request.setAttribute("amember", amember);
			request.getRequestDispatcher("/views/admin/admin_members_update.jsp").forward(request, response);
		}else {
			System.out.println("member가 null입니다.");
			request.setAttribute("msg", "잘못된 접근입니다.");
			request.setAttribute("location", "/views/admin/admin_members.jsp");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
	}

}
