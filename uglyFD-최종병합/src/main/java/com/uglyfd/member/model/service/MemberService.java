package com.uglyfd.member.model.service;

import static com.uglyfd.common.jdbc.JDBCTemplate.close;
import static com.uglyfd.common.jdbc.JDBCTemplate.commit;
import static com.uglyfd.common.jdbc.JDBCTemplate.getConnection;
import static com.uglyfd.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;

import com.uglyfd.member.model.dao.MemberDao;
import com.uglyfd.member.model.vo.Member;

public class MemberService {

	public Member login(String userId, String userPwd) {
		Member member = this.findMemberById(userId);
		
		if(member == null || !member.getPassword().equals(userPwd)) {
			return null;
		}
		
		
		return member;
	}
	public int save(Member member) {
		int result = 0;
		Connection connection = getConnection();
		
		if(member.getNo() > 0) {
			result = new MemberDao().updateMember(connection, member);
		} else {
			result = new MemberDao().insertMember(connection, member);
		}
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		
		return result;
	}
	
	public boolean isDuplicateId(String userId) {
		return this.findMemberById(userId) != null;
	}

	public Member findMemberById(String userId) {
		Connection connection = getConnection();
		
		Member member = new MemberDao().findMemberById(connection, userId);
		
		close(connection);
		
		
		return member;
	}






}
