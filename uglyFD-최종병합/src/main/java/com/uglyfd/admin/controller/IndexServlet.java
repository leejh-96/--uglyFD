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

@WebServlet("/main/home.do")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public IndexServlet() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> list = null;
		
		list = new ProductService().mainFindByProduct();
		
		if (list != null) {
			request.setAttribute("list", list);
			request.getRequestDispatcher("/views/admin/uglyFD.jsp").forward(request, response);
		}else {
			System.out.println("메인가져오기 실패");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
