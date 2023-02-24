package com.uglyfd.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uglyfd.admin.model.service.ProductService;
import com.uglyfd.admin.model.vo.InOut;
import com.uglyfd.admin.model.vo.Product;
import com.uglyfd.admin.model.vo.ProductFile;

@WebServlet(name = "productStockUpdate", urlPatterns = { "/productstockupdate" })
public class ProductStockUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductStockUpdateServlet() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int productCategoryNum = Integer.parseInt(request.getParameter("productCategoryNum"));
		String productName = request.getParameter("productName");
		int productNum = Integer.parseInt(request.getParameter("productNum"));
		
		ProductFile productfile = new ProductService().findByProductFile(productNum);
		System.out.println(productfile.getOriginalFileName());
		System.out.println(productfile.getRenamedFileName());
		
		request.setAttribute("productfile", productfile);
		
//==================================================================================================
		Product product = new ProductService().findByProduct(productCategoryNum, productName);
		
		request.setAttribute("product", product);
		
		request.getRequestDispatcher("/views/admin/admin_stock.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
		
		InOut io = new InOut();
		io.setProductNum(Integer.parseInt(request.getParameter("productNum")));
		io.setProductCategoryNum(Integer.parseInt(request.getParameter("productCategoryNum")));
		io.setProductStock(Integer.parseInt(request.getParameter("stock")));
		io.setStatus(request.getParameter("inout"));

		result = new ProductService().productStockUpdate(io);
		
		if (result == -10) {
			System.out.println("출고할 상품이 부족합니다.");
			request.setAttribute("msg", "출고할 상품이 부족합니다. 재고를 확인한 후 다시 등록해주세요.");
			request.setAttribute("location", "/views/admin/admin_product_detail.jsp");
		}else {
			if (result > 0) {
				System.out.println("재고등록에 최종 성공했습니다.");
				request.setAttribute("msg", "재고가 정상적으로 등록되었습니다.");
				request.setAttribute("location", "/views/admin/admin_product_detail.jsp");
			}else {
				System.out.println("재고등록에 최종 실패했습니다.");
				request.setAttribute("msg", "재고 등록에 실패했습니다.");
				request.setAttribute("location", "/views/admin/admin_stock.jsp");
			}
		}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
	}

}
