package com.uglyfd.member.mypage_cart.vo;

import java.util.Date;
import java.util.List;

import com.uglyfd.admin.model.vo.ProductFile;
import com.uglyfd.admin.model.vo.Review;
import com.uglyfd.member.model.vo.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

	
	
	
		private int loginMember;
		private int productNum;
		private int productCategoryNum;
		private int productCategory;
		private String productName;
		private int productPrice;
		private String productDetail;
		private int productAmount;
		private int sale;
		private int discount;
		private int productFileNum;
		private String originalFileName;
		private String renamedFileName;
		private List<Review> review;
		private List<ProductFile> file;
		private Date createDate;
		private Date modifyDate;
		
	
	
	
	
	
	
	
	
	
	
	
	
}
