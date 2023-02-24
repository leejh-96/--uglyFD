package com.uglyfd.admin.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InOut {

	private int num;
	private int inOutNum;
	private int productPrice;
	private int productNum;
	private int productCategoryNum;
	private int productStock;
	private String status;
	private String productName;
	private Date inOutDate;
	
}
