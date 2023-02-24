package com.uglyfd.admin.model.vo;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

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
