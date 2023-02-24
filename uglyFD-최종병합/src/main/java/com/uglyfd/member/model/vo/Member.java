package com.uglyfd.member.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	private int no;
	
	private String id;

	private String password;
	
	private String name;
	
	private String birth;
	
	private String gender;
	
	private String mail;
	
	private String phone;
	
	private String addr;
	
	private Date createDate;
	
	private Date updateDate;
	
	private int grade;


}
