package com.uglyfd.admin.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin_member {

	private int no;
	
	private String id;

	private String password;
	
	private String name;
	
	private Date birth;
	
	private String gender;
	
	private String mail;
	
	private String phone;
	
	private String addr;
	
	private Date createDate;
	
	private Date updateDate;
	
	private int grade;
	
}








	
	


