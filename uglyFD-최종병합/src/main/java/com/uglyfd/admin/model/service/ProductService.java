package com.uglyfd.admin.model.service;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;

import com.uglyfd.admin.model.dao.ProductDao;
import com.uglyfd.admin.model.vo.*;
import static com.uglyfd.common.jdbc.JDBCTemplate.*;

public class ProductService {

	public int save(Product product, ProductFile productFile) {
		
		int result1 = 0;
		int result2 = 0;
		
		Connection connection = getConnection();
		result1 = new ProductDao().insertProduct(connection, product);
		
		if (result1 > 0) {
			System.out.println("상품등록 성공!");
			commit(connection);
			
			result2 = new ProductDao().insertProductFile(connection, product, productFile);
			
			if (result2>0) {
				System.out.println("첨부파일등록 성공!");
				commit(connection);
			}else {
				System.out.println("첨부파일등록 실패!");
				rollback(connection);
			}
		}else {
			System.out.println("상품등록 실패!");
			rollback(connection);
		}
		
		close(connection);
		
		return result1+result2;
	}
	public Product findByProduct(int productNum) {
		
		Product product = null;
		
		Connection connection = getConnection();
		
		product = new ProductDao().findByProduct(connection,productNum);
		
		if (product != null) {
			System.out.println("product가 null 이 아님");
			close(connection);
		}else {
			System.out.println("product가 null입니다.");
			close(connection);
		}
		return product;
	}
	

	public Product findByProduct(int productCategoryNum, String productName) {
		
		Connection connection = getConnection();
		
		Product product = new ProductDao().findByProduct(connection,productCategoryNum,productName);
		
		if (product.getProductName() != null) {
			System.out.println("product가 null이 아닙니다.");
			close(connection);
			return product;
		}else {
			System.out.println("product가 null입니다.");
			close(connection);
			return product;
		}
		
	}

	public ProductFile findByProductFile(int productNum) {
		
		Connection connection = getConnection();
		
		ProductFile productfile = new ProductDao().findByProductFile(connection, productNum);
		
		if (productfile.getOriginalFileName() != null) {
			System.out.println("원본파일명이 null이 아닙니다.");
			close(connection);
			return productfile;
		}else {
			System.out.println("원본파일명이 null입니다.");
			close(connection);
			return productfile;
		}
	}

	public int productStockUpdate(InOut io) {

		int result = 0;
		int stockCount = 0;
		System.out.println("서비스로 넘어옴 productStockUpdate");
		Connection connection = getConnection();
		
		if (io.getStatus().equals("입고")) {
			
			result = new ProductDao().productStockUpdate(connection,io);
			
			if (result > 0) {
				System.out.println("재고가 정상적으로 등록되었습니다.");
				commit(connection);
			}else {
				System.out.println("재고가 등록에 실패했습니다.");
				rollback(connection);
			}
			
		}else {
			
			InOut inout = new ProductDao().productStockSearch(connection,io);
			
			if (inout.getProductStock() - io.getProductStock() > 0) {
				
				result = new ProductDao().productStockUpdate(connection,io);
				
				if (result > 0) {
					System.out.println("출고가 정상적으로 등록되었습니다.");
					commit(connection);
				}else {
					System.out.println("출고 등록에 실패했습니다.");
					rollback(connection);
				}
			
			}else {
				
				close(connection);
				return stockCount = -10;
			}
		}
		
		close(connection);
		
		return result;
	}

	public List<InOut> findByInOut(PageInfo pageInfo) {
		
		List<InOut> list = null;
		
		Connection connection = getConnection();
		
		list = new ProductDao().findByInOut(connection,pageInfo);
		
		if (list == null) {
			System.out.println("list가 null입니다.");
			close(connection);
		}else {
			System.out.println("list가 null이 아닙니다.");
			System.out.println(list);
			close(connection);
		}
		return list;
	}

	public int getIoListCount() {
		
		int listCount = 0;
		
		Connection connection = getConnection();
		
		listCount = new ProductDao().getIoListCount(connection);
		
		if (listCount > 0) {
			System.out.println("listCount = "+listCount );
			close(connection);
			
		}else {
			System.out.println("입출고 list가 없음");
			close(connection);
		}
		return listCount;
	}

	public int productUpdate(Product product, ProductFile productFile) {

		int result = 0;
		
		Connection connection = getConnection();
		
		result = new ProductDao().productUpdate(connection,product);
		
		if (result > 0) {
			System.out.println("상품이 정상적으로 업데이트 되었습니다.");
			result = new ProductDao().productFileUpdate(connection,productFile);
			
			if (result > 0) {
				System.out.println("상품첨부파일이 정상적으로 업데이트 되었습니다.");
				commit(connection);
				close(connection);
			}else {
				System.out.println("상품첨부파일 업데이트에 실패했습니다.");
				rollback(connection);
				close(connection);
			}
			
		}else {
			System.out.println("상품 업데이트에 실패했습니다.");
			close(connection);
		}
		return result;
	}

	public int productDelete(int productNum, String productName) {
		
		int result = 0;
		
		Connection connection = getConnection();
		
		result = new ProductDao().productDelete(connection,productNum,productName);
		
		if (result > 0) {
			System.out.println("상품이 정상적으로 삭제되었습니다.");
			commit(connection);
			close(connection);
		}else {
			System.out.println("상품 삭제에 실패했습니다.");
			rollback(connection);
			close(connection);
		}
		return result;
	}

	public List<Product> findByProductCategory(int productCategoryNum) {
		
		List<Product> list = null;
		Connection connection = getConnection();
		
		list = new ProductDao().findByProductCategory(connection,productCategoryNum);
		
		if (list != null) {
			System.out.println("list를 성공적으로 가져왔습니다.");
			close(connection);
		}else {
			System.out.println("list를 가져오는데 실패했습니다.");
			close(connection);
		}
		return list;
	}
	public int productReview(String loginMemberId, int productNum, int productCategoryNum, String review) {
		//, String loginMemberId매개변수에 넣기
		int result = 0;
		
		Connection connection = getConnection();
		//,loginMemberId 매개변수에 넣기
		result = new ProductDao().productReview(loginMemberId,connection,productNum,productCategoryNum,review);
		
		if (result > 0) {
			System.out.println("댓글 등록에 성공했습니다.");
			commit(connection);
			close(connection);
		}else {
			System.out.println("댓글 등록에 실패했습니다.");
			rollback(connection);
			close(connection);
		}
		return result;
	}
	public int getReviewCount(int productNum) {
		
		int listCount = 0;
		
		Connection connection = getConnection();
		
		listCount = new ProductDao().getReviewCount(connection,productNum);
		
		if (listCount > 0) {
			System.out.println("게시글의 총 개수 조회에 성공");
			close(connection);
		}else {
			System.out.println("게시글 총 개수 조회 실패");
			close(connection);
		}
		return listCount;
	}
	public List<Review> findByReview(PageInfo pageInfo) {
		
		List<Review> list = null;
		
		Connection connection = getConnection();
		
		list = new ProductDao().findByReview(connection,pageInfo);
		
		if (list != null) {
			System.out.println("리뷰댓글을 성공적으로 불러왔습니다.");
			close(connection);
		}else {
			System.out.println("리뷰댓글을 불러오는데 실패했습니다.");
			close(connection);
		}
		return list;
	}
	public List<Product> mainFindByProduct() {
		
		List<Product>list = null;
		
		Connection connection = getConnection();
		
		list = new ProductDao().mainFindByProduct(connection);
			
		if (list != null) {
			System.out.println("첫 메인페이지 상품 list 가져오기 성공");
			close(connection);
		}else {
			System.out.println("첫 메인페이지 상품 list 가져오기 실패");
			close(connection);
		}
		return list;
	}

	public Admin_member findByAdmin_Member(String name, String id, String phone) {

		Admin_member amember = new Admin_member();
		
		Connection connection = getConnection();
		
		amember = new ProductDao().findByAdmin_Member(connection,name,id,phone);
		
		if (amember != null && amember.getName()!=null) {
			System.out.println("회원이 정상적으로 검색되었습니다.");
			close(connection);
		}else if (amember.getName() == null) {
			System.out.println("회원 검색에 실패했습니다.");
			close(connection);
		}
		
		return amember;
	}
	public int adminMemberUpdate(int memberNo, String id, String password, String addr, String mail, String phone) {
		
		int result = 0;
		
		Connection connection = getConnection();
		
		result = new ProductDao().adminMemberUpdate(connection,memberNo,id,password,addr,mail,phone);
		
		if (result > 0) {
			
			System.out.println("회원정보가 정상적으로 수정되었습니다.");
			commit(connection);
			close(connection);
		}else {
			System.out.println("회원정보 수정에 실패했습니다.");
			rollback(connection);
			close(connection);
		}
		return result;
	}
	public int memberDelete(int memberNo) {
		
		int result = 0;
		
		Connection connection = getConnection();
		
		result = new ProductDao().memberDelete(connection,memberNo);
		
		if (result > 0) {
			System.out.println("회원이 정상적으로 삭제되었습니다.");
			commit(connection);
			close(connection);
		}else {
			System.out.println("회원 삭제에 실패했습니다.");
			rollback(connection);
			close(connection);
		}
		return result;
	}
	

	

	

}
