package com.uglyfd.admin.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFile {

	private int productFileNum;
	private int productNum;
	private int productCategoryNum;
//	private int productCategory;
	private String originalFileName;
	private String renamedFileName;
	private int fileSize;
	private String fileType;
	private Date createDate;
	private Date modifyDate;
	
}
