package com.uglyfd.admin.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

	private int reviewNum;
	private int productNum;
	private int productCategoryNum;
	private String memberId;
	private String writerId;
	private String title;
	private String content;
	private Date createDate;
	private Date modifyDate;
	
}
