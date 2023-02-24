package com.uglyfd.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uglyfd.admin.model.service.ProductService;
import com.uglyfd.admin.model.vo.Admin_member;


@WebServlet(name = "adminmembersearch", urlPatterns = { "/admin/member" })
public class AdminMemberSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public AdminMemberSearch() {
       
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String phone = request.getParameter("phone");
		
		System.out.println(name);
		System.out.println(id);
		System.out.println(phone);
		
		Admin_member amember = new Admin_member();
		
		amember = new ProductService().findByAdmin_Member(name,id,phone);
		
		System.out.println(amember);
		
		if (amember != null && amember.getName()!=null) {
			System.out.println("회원검색에 최종 성공");
			request.setAttribute("amember", amember);
			request.getRequestDispatcher("/views/admin/admin_members.jsp").forward(request, response);
		}else if (amember.getName() == null) {
			System.out.println("회원검색에 최종 실패");
			request.setAttribute("msg", "검색된 회원이 없습니다.");
			request.setAttribute("amember", amember);
			request.setAttribute("location", "/views/admin/admin_members.jsp");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	}

}
