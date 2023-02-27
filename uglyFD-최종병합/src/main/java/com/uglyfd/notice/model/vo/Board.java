package com.uglyfd.notice.model.vo;

import java.util.Date;
import java.util.List;

//import com.uglyfd.board.model.vo.Reply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	private int b_no;
	
	private String bo_title;
	
	private Date bo_date;
	
	private Date bo_update;
	
	private String bo_con;
	
	private int bo_hit;
	
	private String bo_writer;
	
	private int m_no;	//MEMBER테이블 회원번호(외래키) 회원가입시 시퀀스를 통해서 회원들을 식별한다.  
	
	private String status;
	
	private int rowNum;
	
	private String original_filename;
	private String renamed_filename;
	
	private int readCount;
	
	private List<Reply> replies;	
}




