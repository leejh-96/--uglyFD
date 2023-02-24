package com.uglyfd.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uglyfd.admin.model.service.ProductService;

@WebServlet(name = "productdelete", urlPatterns = { "/product/delete" })
public class ProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ProductDeleteServlet() {
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int result = 0;
		
		int productNum = Integer.parseInt(request.getParameter("productNum"));
		String productName = request.getParameter("productName");
		
		result = new ProductService().productDelete(productNum,productName);
		
		if (result > 0) {
			System.out.println("삭제 성공");
			request.setAttribute("msg", "상품 삭제에 성공하셨습니다.");
			request.setAttribute("location", "/views/admin/admin_product_detail.jsp");
		}else {
			System.out.println("삭제 실패");
			request.setAttribute("msg", "상품 삭제에 실패하셨습니다.");
			request.setAttribute("location", "/views/admin/admin_product_detail.jsp");
		}
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
	}

}
