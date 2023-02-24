package com.uglyfd.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uglyfd.admin.model.service.ProductService;
import com.uglyfd.admin.model.vo.InOut;
import com.uglyfd.admin.model.vo.PageInfo;

@WebServlet(name = "productinout", urlPatterns = { "/productinout" })
public class ProductInOutSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductInOutSearchServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 0;
		int listCount = 0;
		PageInfo pageInfo = null;
		List<InOut> list = null;
		
		try {
			page = Integer.parseInt(request.getParameter("page"));
			System.out.println(page);
			if (page == 0) {
				page = 1;
			}
		}catch (NumberFormatException e) {
			page = 1;
		}
		
		//db에서 가져온 데이터 행의 총 개수 listCount
		listCount = new ProductService().getIoListCount();
		pageInfo = new PageInfo(page, 10, listCount, 10);
		list = new ProductService().findByInOut(pageInfo);
		
		if ((listCount != 0) && (list != null)) {
			System.out.println(list);
			request.setAttribute("list", list);
			request.setAttribute("pageInfo", pageInfo);
			request.getRequestDispatcher("/views/admin/admin_inout.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/views/admin/admin_inout.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
