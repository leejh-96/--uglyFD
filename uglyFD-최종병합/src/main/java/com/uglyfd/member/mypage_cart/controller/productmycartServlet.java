package com.uglyfd.member.mypage_cart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uglyfd.admin.model.service.ProductService;
import com.uglyfd.admin.model.vo.Product;
//import com.uglyfd.member.model.service.MemberService;
import com.uglyfd.member.model.vo.Member;


@WebServlet(name = "productmycart", urlPatterns = { "/product/mycart" })
public class productmycartServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;
  
    public productmycartServlet() {
      
    }



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int result = 0;
//		Product product = null;
		// 1. 로그인 된 사용자인지 체크
//		HttpSession session = request.getSession(false);
//		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
		
		
//		if (product != null) {
//			product = new Product();
//						
//			product.setProductNum(product.getProductNum());
//			product.setProductCategory(product.getProductCategory()); 
//			product.setProductName(request.getParameter("productName"));
//			product.setProductAmount(product.getProductAmount());
//			product.setProductPrice(product.getProductPrice());
//			
//			
//			
//			result = new ProductService().save(product);
			
		request.getRequestDispatcher("/views/mypage/mycart.jsp").forward(request, response);
//		response.sendRedirect("/views/mypage/mycart.jsp");
		

				}
}
//	}