package com.uglyfd.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uglyfd.admin.model.service.ProductService;
import com.uglyfd.admin.model.vo.Product;
import com.uglyfd.admin.model.vo.ProductFile;
import com.uglyfd.member.model.vo.Member;

@WebServlet(name = "productreview", urlPatterns = { "/review/update" })
public class ProductReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductReviewServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = ((HttpServletRequest)request).getSession(false);
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		
		if (loginMember != null) {
			
			int productNum = Integer.parseInt(request.getParameter("productNum"));
			String loginMemberId = request.getParameter("loginMemberId");
			int productCategoryNum = Integer.parseInt(request.getParameter("productCategoryNum"));
			String review = request.getParameter("review");
			
			System.out.println(productNum);
			System.out.println(loginMemberId);
			System.out.println(productCategoryNum);
			System.out.println(review);
			
			int result = 0;
			//loginMemberId, 매개변수에 넣기
			result = new ProductService().productReview(loginMemberId,productNum,productCategoryNum,review);
			
			if (result > 0) {
				System.out.println("댓글 등록이 최종 성공했습니다.");
				
				//test
//				ProductFile productFile = null;
//				Product product = null;
//				
//	    		productFile = new ProductService().findByProductFile(productNum);
//	    		product = new ProductService().findByProduct(productNum);
//	    		
//	    		request.setAttribute("loginMember", loginMember);
//	    		request.setAttribute("productFile", productFile);
//	    		request.setAttribute("product", product);
//				response.sendRedirect("views/admin/product_detail_page.jsp");
//	    		
				
				request.setAttribute("msg", "댓글이 정상적으로 등록되었습니다.");
				request.setAttribute("location", "/product/category");
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			}else {
				System.out.println("댓글 등록이 최종 실패했습니다.");
				request.setAttribute("msg", "댓글 등록에 실패했습니다..");
				request.setAttribute("location", "/product/category");
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			}
			
		}else {
    		request.setAttribute("msg", "로그인 후 작성해 주세요.");
    		request.setAttribute("location", "/views/member/login.jsp");
    		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	}

}
