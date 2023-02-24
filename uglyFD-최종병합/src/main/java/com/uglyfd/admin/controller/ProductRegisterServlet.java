package com.uglyfd.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.uglyfd.admin.model.service.ProductService;
import com.uglyfd.admin.model.vo.Product;
import com.uglyfd.admin.model.vo.ProductFile;
import com.uglyfd.admin.utill.FileRename;

@WebServlet(name = "productRegister", urlPatterns = { "/product/register" })
public class ProductRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductRegisterServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int result = 0;
		
		//상품의 첨부파일이 저장될 경로
		String path = getServletContext().getRealPath("/resources/upload/product");
    	System.out.println(path);
		
    	//상품의 첨부파일 크기를 10MB byte 단위로 지정
		int maxSize = 10485760;
		
		//상품의 첨부파일 인코딩 설정
		String encoding = "UTF-8";
		
		//MultipartRequest 객체 생성
		MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());
		
		//Product 객체를 생성해서 넘어온 파라미터 값 저장
		Product product = new Product();
		//ProductFile 객체를 생성해서 넘어온 파라미터 값들을 저장
		ProductFile productFile = new ProductFile();
		
		productFile.setProductCategoryNum(Integer.parseInt(mr.getParameter("productCategoryNum")));
		productFile.setRenamedFileName(mr.getFilesystemName("upfile"));
		productFile.setOriginalFileName(mr.getOriginalFileName("upfile"));
		
		product.setProductCategoryNum(Integer.parseInt(mr.getParameter("productCategoryNum")));
		product.setProductName(mr.getParameter("productName"));
		product.setProductPrice(Integer.parseInt(mr.getParameter("productPrice")));
//		product.setDiscount(Integer.parseInt(mr.getParameter("discount")));
		product.setProductDetail(mr.getParameter("productDetail"));
		
		System.out.println(productFile.getProductCategoryNum());
		System.out.println(productFile.getOriginalFileName());
		System.out.println(productFile.getRenamedFileName());
		System.out.println(product.getProductCategoryNum());
		System.out.println(product.getProductName());
		System.out.println(product.getProductPrice());
		System.out.println(product.getProductDetail());
		
		
		result = new ProductService().save(product, productFile);
		
		if (result > 1) {
			System.out.println("상품등록과 첨부파일 등록 성공!");
			request.setAttribute("msg", "상품이 성공적으로 등록되었습니다.");
			request.setAttribute("location", "/views/admin/admin_product.jsp");
		}else {
			System.out.println("상품등록 또는 첨부파일 등록 실패!");
			request.setAttribute("msg", "상품등록에 실패하셨습니다.");
			request.setAttribute("location", "/views/admin/admin_product.jsp");
		}
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
