package com.uglyfd.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uglyfd.admin.model.service.ProductService;
import com.uglyfd.admin.model.vo.Product;

@WebServlet(name = "productStock", urlPatterns = { "/productStock" })
public class ProductStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductStockServlet() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int productCategoryNum = Integer.parseInt( request.getParameter("productCategoryNum"));
		String productName = request.getParameter("productName");
		
		Product product = new ProductService().findByProduct(productCategoryNum,productName);
		
		if (product.getProductName() != null) {
			System.out.println("product가 null이 아닙니다.");
			System.out.println(product);
			request.setAttribute("product", product);
			request.getRequestDispatcher("/views/admin/admin_product_detail.jsp").forward(request, response);
		}else {
			System.out.println("product가 null입니다.");
			request.setAttribute("msg", "등록된 상품이 없습니다.");
			request.setAttribute("location", "/views/admin/admin_product_detail.jsp");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
