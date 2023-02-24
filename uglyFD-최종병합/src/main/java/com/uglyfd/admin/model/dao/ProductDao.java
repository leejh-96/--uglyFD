package com.uglyfd.admin.model.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.uglyfd.admin.model.vo.*;
import static com.uglyfd.common.jdbc.JDBCTemplate.*;

public class ProductDao {

	public int insertProduct(Connection connection, Product product) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String query = "INSERT INTO product VALUES ( "
				+ "seq_product_p_no.nextval, "
				+ "?, "
				+ "?, "
				+ "?, "
				+ "DEFAULT, "
				+ "?, "
				+ "DEFAULT, "
				+ "DEFAULT, "
				+ "DEFAULT, "
				+ "DEFAULT, "
				+ "DEFAULT "
				+ ")";
		
		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, product.getProductCategoryNum());
			pstmt.setString(2, product.getProductName());
			pstmt.setInt(3, product.getProductPrice());
			pstmt.setString(4, product.getProductDetail());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertProductFile(Connection connection, Product product, ProductFile productFile) {
		
		int result = 0; 
		PreparedStatement pstmt = null;
		String query = "INSERT INTO product_file VALUES ( "
						+ "SEQ_PRODUCT_FILE_PF_NO.nextval, "
						+ "(select P_NO from product "
						+ "where P_NAME = ? AND P_PRICE = ?), "
						+ "?, "
						+ "DEFAULT, "
						+ "DEFAULT, "
						+ "?, "
						+ "?, "
						+ "DEFAULT, "
						+ "DEFAULT "
						+ ")";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, product.getProductName());
			pstmt.setInt(2, product.getProductPrice());
			System.out.println("dao/서브쿼리끝");
			pstmt.setInt(3, productFile.getProductCategoryNum());
			pstmt.setString(4, productFile.getOriginalFileName());
			pstmt.setString(5, productFile.getRenamedFileName());

			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("dao/catch");
		} finally {
			System.out.println("dao/finally");
			close(pstmt);
		}
		return result;
	}

	public Product findByProduct(Connection connection, int productCategoryNum, String productName) {
		
		Product product = new Product();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT p_no,pc_no,p_name,p_price,p_sale,p_datail,p_amount,p_dis,p_create,p_update  "
				+ "FROM PRODUCT "
				+ "WHERE PC_NO = ? AND P_NAME = ? AND P_STATUS = 'Y' ";
		
		try {
			pstmt = connection.prepareStatement(query);
		
			pstmt.setInt(1, productCategoryNum);
			pstmt.setString(2, productName);
			
			rs = pstmt.executeQuery();
		
			if (rs.next()) {
				
				product.setProductNum(rs.getInt("P_NO"));
				product.setProductCategoryNum(rs.getInt("PC_NO"));
				product.setProductName(rs.getString("P_NAME"));
				product.setProductPrice(rs.getInt("P_PRICE"));
				product.setSale(rs.getInt("P_SALE"));
				product.setProductDetail(rs.getString("P_DATAIL"));
				product.setProductAmount(rs.getInt("P_AMOUNT"));
				product.setDiscount(rs.getInt("P_DIS"));
				product.setCreateDate(rs.getDate("P_CREATE"));
				product.setModifyDate(rs.getDate("P_UPDATE"));
				
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return product;
		
	}

	public ProductFile findByProductFile(Connection connection, int productNum) {
		
		ProductFile productfile = new ProductFile();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT "
						+ "    pf_no, "
						+ "    p_no, "
						+ "    pc_no, "
						+ "    pf_date, "
						+ "    pf_update, "
						+ "    pf_original_filename, "
						+ "    pf_renamed_filename, "
						+ "    pf_filesize, "
						+ "    pf_filetype "
						+ "FROM "
						+ "    product_file "
						+ "where p_no = ? ";
		
		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, productNum);
		
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				productfile.setProductFileNum(rs.getInt("PF_NO"));
				productfile.setProductNum(rs.getInt("P_NO"));
				productfile.setCreateDate(rs.getDate("PF_DATE"));
				productfile.setModifyDate(rs.getDate("PF_UPDATE"));
				productfile.setOriginalFileName(rs.getString("PF_ORIGINAL_FILENAME"));
				productfile.setRenamedFileName(rs.getString("PF_RENAMED_FILENAME"));
				productfile.setFileSize(rs.getInt("PF_FILESIZE"));
				productfile.setFileType(rs.getString("PF_FILETYPE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return productfile;
	}

	public int productStockUpdate(Connection connection, InOut io) {
		System.out.println("dao로 넘어옴 productStockUpdate");
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO IN_OUT VALUES ( "
				+ "    seq_in_out_io_no.nextval, "
				+ "    ?, "
				+ "    ?, "
				+ "    ?, "
				+ "    DEFAULT, "
				+ "    ? "
				+ ") ";
		
		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, io.getProductNum());
			pstmt.setInt(2, io.getProductCategoryNum());
			pstmt.setInt(3, io.getProductStock());
			pstmt.setString(4, io.getStatus());
			
			result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("재고등록 성공");
				
			}else {
				System.out.println("재고등록 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public InOut productStockSearch(Connection connection, InOut io) {
		
//		int stockcount = 0;
		InOut inout = new InOut();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select p_amount  "
						+ "from product "
						+ "where P_STATUS = 'Y' AND P_NO = ? ";
		
		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, io.getProductNum());
			
			rs = pstmt.executeQuery();
		
			if (rs.next()) {
				inout.setProductStock(rs.getInt("P_AMOUNT"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return inout;
	}
//  검색 기능은 다시 생각해보기
	public List<InOut> findByInOut(Connection connection, PageInfo pageInfo) {

		List<InOut> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT NUM, IO_NO,P_NAME,P_PRICE,IO_COUNT,IO_STATUS,IO_DATE "
						+ "FROM "
						+ "( "
						+ "SELECT ROWNUM AS NUM, IO_NO,P_NAME,P_PRICE,IO_COUNT,IO_STATUS,IO_DATE "
						+ "FROM (SELECT IO.IO_NO,P.P_NAME,P.P_PRICE,IO.IO_COUNT,IO.IO_STATUS,IO.IO_DATE  "
						+ "FROM PRODUCT P  "
						+ "JOIN IN_OUT IO ON (P.P_NO = IO.P_NO) "
						+ "WHERE P.P_STATUS = 'Y' "
						+ "ORDER BY IO.IO_NO DESC) "
						+ ") "
						+ "WHERE NUM BETWEEN ? AND ? ";
		
		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				InOut io = new InOut();
				io.setNum(rs.getInt("NUM"));
				io.setInOutNum(rs.getInt("IO_NO"));
				io.setProductName(rs.getString("P_NAME"));
				io.setProductPrice(rs.getInt("P_PRICE"));
				io.setProductStock(rs.getInt("IO_COUNT"));
				io.setStatus(rs.getString("IO_STATUS"));
				io.setInOutDate(rs.getDate("IO_DATE"));
				
				list.add(io);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int getIoListCount(Connection connection) {

		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*)  "
						+ "FROM IN_OUT IO "
						+ "JOIN PRODUCT PD ON (IO.P_NO = PD.P_NO) "
						+ "WHERE PD.P_STATUS = 'Y' ";
		
		try {
			pstmt = connection.prepareStatement(query);

			rs = pstmt.executeQuery();
		
			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	public int productUpdate(Connection connection, Product product) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE product "
					+ "SET "
					+ "    pc_no = ? , "
					+ "    p_name = ? , "
					+ "    P_PRICE = ? , "
					+ "    P_DATAIL = ? "
					+ "WHERE p_no = ? AND P_STATUS = 'Y' ";
		
		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, product.getProductCategoryNum());
			pstmt.setString(2, product.getProductName());
			pstmt.setInt(3, product.getProductPrice());
			pstmt.setString(4, product.getProductDetail());
			pstmt.setInt(5, product.getProductNum());
//			pstmt.setString(6, product.getProductName());
		
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int productFileUpdate(Connection connection, ProductFile productFile) {

		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE product_file "
						+ "SET "
						+ "    PF_ORIGINAL_FILENAME = ? , "
						+ "    PF_RENAMED_FILENAME = ? "
						+ "WHERE p_no = ?  ";
		
		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setString(1, productFile.getOriginalFileName());
			pstmt.setString(2, productFile.getRenamedFileName());
			pstmt.setInt(3, productFile.getProductNum());
		
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int productDelete(Connection connection, int productNum, String productName) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE product "
						+ "SET "
						+ "    P_STATUS = 'N' "
						+ "WHERE p_no = ? AND p_name = ? AND P_STATUS = 'Y' ";
		
		try {
			pstmt = connection.prepareStatement(query);
		
			pstmt.setInt(1, productNum);
			pstmt.setString(2, productName);
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Product> findByProductCategory(Connection connection, int productCategoryNum) {
		
		List<Product> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT P.P_NO,P.PC_NO,P.P_NAME,P.P_PRICE,P.P_DATAIL,P.P_AMOUNT,P.P_DIS,PF.PF_NO,PF.PF_ORIGINAL_FILENAME,PF.PF_RENAMED_FILENAME "
						+ "FROM PRODUCT P "
						+ "JOIN PRODUCT_FILE PF ON (P.P_NO = PF.P_NO) "
						+ "WHERE P.P_NO = PF.P_NO AND P.PC_NO = ? AND P_STATUS = 'Y' ";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, productCategoryNum);
			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {

				Product product = new Product();
				product.setProductNum(rs.getInt("P_NO"));
				product.setProductCategoryNum(rs.getInt("PC_NO"));
				product.setProductName(rs.getString("P_NAME"));
				product.setProductPrice(rs.getInt("P_PRICE"));
				product.setProductDetail(rs.getString("P_DATAIL"));
				product.setProductAmount(rs.getInt("P_AMOUNT"));
				product.setDiscount(rs.getInt("P_DIS"));
				product.setProductFileNum(rs.getInt("PF_NO"));
				product.setOriginalFileName(rs.getString("PF_ORIGINAL_FILENAME"));
				product.setRenamedFileName(rs.getString("PF_RENAMED_FILENAME"));	
				
				list.add(product);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public Product findByProduct(Connection connection, int productNum) {
		
		Product product = new Product();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT "
						+ "    p_no, "
						+ "    pc_no, "
						+ "    p_name, "
						+ "    p_price, "
						+ "    p_sale, "
						+ "    p_datail, "
						+ "    p_amount, "
						+ "    p_dis, "
						+ "    p_create, "
						+ "    p_update "
						+ "FROM "
						+ "    product "
						+ "WHERE P_NO = ? AND P_STATUS = 'Y' ";
		
		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, productNum);
			
			rs = pstmt.executeQuery();
		
			if (rs.next()) {
				
				product.setProductNum(rs.getInt("P_NO"));
				product.setReview(this.getReview(connection, productNum));
				product.setProductCategoryNum(rs.getInt("PC_NO"));
				product.setProductName(rs.getString("P_NAME"));
				product.setProductPrice(rs.getInt("P_PRICE"));
				product.setSale(rs.getInt("P_SALE"));
				product.setProductDetail(rs.getString("P_DATAIL"));
				product.setProductAmount(rs.getInt("P_AMOUNT"));
				product.setDiscount(rs.getInt("P_DIS"));
				product.setCreateDate(rs.getDate("P_CREATE"));
				product.setModifyDate(rs.getDate("P_UPDATE"));
				
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return product;
	}

	public List<Review> getReview(Connection connection, int productNum){
		
		List<Review> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT "
					+ "    R.re_no, "
					+ "    R.re_name, "
					+ "    R.re_date, "
					+ "    R.re_update, "
					+ "    R.re_title, "
					+ "    R.RE_CONTENT, "
					+ "    R.m_id, "
					+ "    R.p_no, "
					+ "    R.pc_no "
					+ "FROM "
					+ "    REVIEW R "
					+ "JOIN PRODUCT P ON (R.P_NO = P.P_NO) "
					+ "WHERE P.P_NO = ? AND P.P_STATUS = 'Y' "
					+ "ORDER BY r.re_date DESC ";
		
		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, productNum);
		
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				
				Review review = new Review();
				
				review.setReviewNum(rs.getInt("RE_NO"));
				review.setWriterId(rs.getString("RE_NAME"));
				review.setCreateDate(rs.getDate("RE_DATE"));
				review.setModifyDate(rs.getDate("RE_UPDATE"));
				review.setTitle(rs.getString("RE_TITLE"));
				review.setContent(rs.getString("RE_CONTENT"));
				review.setMemberId(rs.getString("M_ID"));
				review.setProductNum(rs.getInt("P_NO"));
				review.setProductCategoryNum(rs.getInt("PC_NO"));
				
				list.add(review);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int productReview(String loginMemberId, Connection connection, int productNum, int productCategoryNum, String review) {
		//, String loginMemberId 매개변수에 넣기
		int result = 0;
		
		PreparedStatement pstmt = null;
		String query = "INSERT INTO review ( "
									+ "    re_no, "
									+ "    re_name, "
									+ "    re_date, "
									+ "    re_update, "
									+ "    re_title, "
									+ "    re_content, "
									+ "    m_id, "
									+ "    p_no,  "
									+ "    pc_no, "
									+ "    re_status "
									+ ") VALUES ( "
									+ "    SEQ_REVIEW_RE_NO.NEXTVAL, "
									+ "    DEFAULT, "
									+ "    DEFAULT, "
									+ "    DEFAULT, "
									+ "    DEFAULT, "
									+ "    ?, "
									+ "    ?, "
									+ "    ?, "
									+ "    ?, "
									+ "    DEFAULT "
									+ ") ";
		
		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setString(1, review);
			pstmt.setString(2, loginMemberId);
			pstmt.setInt(3, productNum);
			pstmt.setInt(4, productCategoryNum);
		
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int getReviewCount(Connection connection, int productNum) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT count(*) "
				+ "FROM review "
				+ "WHERE P_NO = ? AND RE_STATUS = 'Y' ";
		
		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, productNum);
			
			rs = pstmt.executeQuery();
		
			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	public List<Review> findByReview(Connection connection, PageInfo pageInfo) {
		
		List<Review> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT NUM, RE_DATE,RE_UPDATE,RE_CONTENT,M_ID "
						+ "FROM(SELECT ROWNUM AS NUM, RE_DATE,RE_UPDATE,RE_CONTENT,M_ID "
						+ "FROM(SELECT "
						+ "    re_date, "
						+ "    re_update, "
						+ "    re_content, "
						+ "    m_id "
						+ "FROM review "
						+ "WHERE RE_STATUS = 'Y' "
						+ "ORDER BY RE_DATE DESC) "
						+ ") "
						+ "WHERE NUM BETWEEN ? AND ? ";
		
		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());
			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				
				Review review = new Review();
				
				review.setReviewNum(rs.getInt("NUM"));
				review.setCreateDate(rs.getDate("RE_DATE"));
				review.setModifyDate(rs.getDate("RE_UPDATE"));
				review.setContent(rs.getString("RE_CONTENT"));
				review.setMemberId(rs.getString("M_ID"));
				
				list.add(review);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public List<Product> mainFindByProduct(Connection connection) {
		
		List<Product> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT P.p_no, "
						+ "    P.pc_no, "
						+ "    P.p_name, "
						+ "    P.p_price, "
						+ "    P.p_sale, "
						+ "    P.p_datail, "
						+ "    P.p_amount, "
						+ "    P.p_dis, "
						+ "    P.p_create, "
						+ "    P.p_update, "
						+ "    PF.PF_NO, "
						+ "    PF.PF_ORIGINAL_FILENAME, "
						+ "    PF.PF_RENAMED_FILENAME "
						+ "FROM PRODUCT P "
						+ "JOIN PRODUCT_FILE PF ON (P.P_NO = PF.P_NO) "
						+ "WHERE P.P_STATUS = 'Y' ";
		
		try {
			pstmt = connection.prepareStatement(query);

			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				
				Product product = new Product();
				
				product.setProductNum(rs.getInt("P_NO"));
				product.setProductCategoryNum(rs.getInt("PC_NO"));
				product.setProductName(rs.getString("P_NAME"));
				product.setProductPrice(rs.getInt("P_PRICE"));
				product.setSale(rs.getInt("P_SALE"));
				product.setProductDetail(rs.getString("P_DATAIL"));
				product.setProductAmount(rs.getInt("P_AMOUNT"));
				product.setDiscount(rs.getInt("P_DIS"));
				product.setCreateDate(rs.getDate("P_CREATE"));
				product.setModifyDate(rs.getDate("P_UPDATE"));
				product.setProductFileNum(rs.getInt("PF_NO"));
				product.setOriginalFileName(rs.getString("PF_ORIGINAL_FILENAME"));
				product.setRenamedFileName(rs.getString("PF_RENAMED_FILENAME"));
				
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public Admin_member findByAdmin_Member(Connection connection, String name, String id, String phone) {
		
		Admin_member amember = new Admin_member();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT "
						+ "    m_no, "
						+ "    m_id, "
						+ "    m_pwd, "
						+ "    m_name, "
						+ "    m_birth, "
						+ "    m_gender, "
						+ "    m_mail, "
						+ "    m_phone, "
						+ "    m_addr, "
						+ "    m_create, "
						+ "    m_update "
						+ "FROM "
						+ "    member "
						+ "WHERE M_NAME = ? AND M_ID = ? AND M_PHONE = ? AND M_GRADE = 2";
		
		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, phone);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				amember.setNo(rs.getInt("M_NO"));
				amember.setId(rs.getString("M_ID"));
				amember.setPassword(rs.getString("M_PWD"));
				amember.setName(rs.getString("M_NAME"));
				amember.setBirth(rs.getDate("M_BIRTH"));
				amember.setGender(rs.getString("M_GENDER"));
				amember.setMail(rs.getString("M_MAIL"));
				amember.setPhone(rs.getString("M_PHONE"));
				amember.setAddr(rs.getString("M_ADDR"));
				amember.setCreateDate(rs.getDate("M_CREATE"));
				amember.setUpdateDate(rs.getDate("M_UPDATE"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return amember;
	}

	public int adminMemberUpdate(Connection connection, int memberNo, String id, String password, String addr, String mail, String phone) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE member "
							+ "SET "
							+ "    M_ID = ?, "
							+ "    M_PWD = ?, "
							+ "    M_ADDR = ?, "
							+ "    M_MAIL = ?, "
							+ "    M_PHONE = ? "
							+ "WHERE m_no = ? AND m_grade = 2 ";
		
		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, addr);
			pstmt.setString(4, mail);
			pstmt.setString(5, phone);
			pstmt.setInt(6, memberNo);
		
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int memberDelete(Connection connection, int memberNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "DELETE FROM member "
						+ "WHERE m_no = ? and m_grade = 2 ";
		
		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, memberNo);
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
