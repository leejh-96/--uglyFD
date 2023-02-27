package com.uglyfd.notice.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

// 댓글과 관련된 내용을 작성할 때 필요한 클래스 
public class Reply {
	private int no;
	
	private int boardNo;
	
	private int writerNo;
	
	private String writerId;
	
	private String content;	
	
	private Date createDate;
	
	private Date modifyDate;
}