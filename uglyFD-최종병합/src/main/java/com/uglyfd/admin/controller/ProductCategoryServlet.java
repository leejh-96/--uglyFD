package com.uglyfd.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uglyfd.admin.model.service.ProductService;
import com.uglyfd.admin.model.vo.Product;

@WebServlet(name = "productCategory", urlPatterns = { "/product/category" })
public class ProductCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductCategoryServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	int productCategoryNum = 0;
			
			try {
				productCategoryNum = Integer.parseInt(request.getParameter("productCategoryNum"));
				
				System.out.println(productCategoryNum);
				
			} catch (NumberFormatException e) {
				productCategoryNum = 1;
			}
			
			List<Product> list = null;
			
			list = new ProductService().findByProductCategory(productCategoryNum);
			
			System.out.println(list);
			
			request.setAttribute("list", list);
			request.setAttribute("productCategoryNum", productCategoryNum);
			request.getRequestDispatcher("/views/admin/product_page.jsp").forward(request, response);
			
		}
    	
    }


