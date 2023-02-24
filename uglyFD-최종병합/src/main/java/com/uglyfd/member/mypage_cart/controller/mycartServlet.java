package com.uglyfd.member.mypage_cart.controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uglyfd.admin.model.service.ProductService;
import com.uglyfd.member.mypage_cart.mypage_cart_service.CartService;
import com.uglyfd.member.mypage_cart.vo.Cart;


@WebServlet(name = "mycart", urlPatterns = { "/mypage/mycart" })
public class mycartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public mycartServlet() {
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
//		String product = request.getParameter("productNum");
//		
//		System.out.println(product);
		
//		Cart cart =  null;
//		
//		int loginMemberNum = Integer.parseInt(request.getParameter("loginMemberNum"));
//		int productNum = Integer.parseInt(product);
//		int productCategoryNum = Integer.parseInt(request.getParameter("productCategoryNum"));
//		String productName = request.getParameter("productName");
//		int productPrice = Integer.parseInt(request.getParameter("productPrice")); 
//		int productAcount = Integer.parseInt(request.getParameter("productAcount"));
//		
//		System.out.println(loginMemberNum);
//		System.out.println(productNum);
//		System.out.println(productCategoryNum);
//		System.out.println(productName);
//		System.out.println(productPrice);
//		System.out.println(productAcount);
//		
//		
//		
//		
//		
//		
//		cart = new CartService().insertCart(productNum,loginMemberNum);
		
		
		request.getRequestDispatcher("/views/mypage/mycart.jsp").forward(request, response);
		
		
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
