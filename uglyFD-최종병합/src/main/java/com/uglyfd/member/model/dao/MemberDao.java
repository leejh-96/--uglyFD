package com.uglyfd.member.model.dao;

import static com.uglyfd.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.uglyfd.member.model.vo.Member;

public class MemberDao {


	public Member findMemberById(Connection connection, String userId) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM MEMBER WHERE M_ID=?";
		
		try {
            pstmt = connection.prepareStatement(query);

            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                member = new Member();
                
                member.setNo(rs.getInt("M_NO"));
                member.setId(rs.getString("M_ID"));
                member.setPassword(rs.getString("M_PWD"));
                member.setName(rs.getString("M_NAME"));
                member.setBirth(rs.getString("M_BIRTH"));
                member.setGender(rs.getString("M_GENDER"));
                member.setMail(rs.getString("M_MAIL"));
                member.setPhone(rs.getString("M_PHONE"));
                member.setAddr(rs.getString("M_ADDR"));
                member.setCreateDate(rs.getDate("M_CREATE"));
                member.setUpdateDate(rs.getDate("M_UPDATE"));
                member.setGrade(rs.getInt("M_GRADE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	close(rs);
        	close(pstmt);
        }

		
		return member;
	}

	public int insertMember(Connection connection, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO MEMBER VALUES(SEQ_MEMBER_M_NO.NEXTVAL,?,?,?,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT)";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getBirth());
			pstmt.setString(5, member.getGender());
			pstmt.setString(6, member.getMail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getAddr());
			
			result = pstmt.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateMember(Connection connection, Member member) {
		return 0;
	}



	
}
