package com.uglyfd.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uglyfd.board.model.vo.Board;
import com.uglyfd.board.model.vo.Reply;
//import com.uglyfd.board.model.vo.Board;
//import com.uglyfd.board.model.vo.Reply;
import com.uglyfd.common.util.PageInfo;

import static com.uglyfd.common.jdbc.JDBCTemplate.close;

public class BoardDao {

    public int getBoardCount(Connection connection) {
        int count = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT COUNT(*) FROM BOARD WHERE BC_NO='2' AND B_STATUS='Y'";

        try {
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                count = rs.getInt(1); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
        }

        return count;
    }

	public List<Board> findAll(Connection connection, PageInfo pageInfo) {	
		List<Board> inquire = new ArrayList<>();
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String query = "SELECT RNUM, B_NO, B_TITLE, M_ID, B_CREATE_DATE, B_ORIGINAL_FILENAME, B_READCOUNT, B_STATUS  "
	      		+ "FROM (\r\n"
	      		+ "    SELECT ROWNUM AS RNUM, B_NO,  B_TITLE, M_ID, B_CREATE_DATE, B_ORIGINAL_FILENAME, B_READCOUNT, B_STATUS "
	      		+ "    FROM ("
	      		+ "        SELECT  B.B_NO, B.B_TITLE, M.M_ID, B.B_CREATE_DATE, B.B_ORIGINAL_FILENAME, B.B_READCOUNT, B.B_STATUS "
	      		+ "        FROM BOARD B JOIN MEMBER M ON(B.B_WRITER_NO = M.M_NO) \r\n"
	      		+ "        WHERE B.B_STATUS = 'Y'  ORDER BY B.B_NO DESC\r\n"
	      		+ "    )\r\n"
	      		+ ")\r\n"
	      		+ "		WHERE RNUM BETWEEN ? and ?";
	      
	      try {
	         pstmt = connection.prepareStatement(query);
	         
	         pstmt.setInt(1, pageInfo.getStartList());
	         pstmt.setInt(2, pageInfo.getEndList());
	         
	         rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	            Board board = new Board();
	            
	            board.setRowNum(rs.getInt("RNUM"));
	            board.setNo(rs.getInt("B_NO"));
	            board.setTitle(rs.getString("B_TITLE"));
	            board.setWriterId(rs.getString("M_ID"));
	            board.setCreateDate(rs.getDate("B_CREATE_DATE"));
	            board.setOriginalFileName(rs.getString("B_ORIGINAL_FILENAME"));
	            board.setReadCount(rs.getInt("B_READCOUNT"));
	            board.setStatus(rs.getString("B_STATUS"));
	            
	            inquire.add(board);
	            

	
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close(rs);
	         close(pstmt);
	      }
	      
	      return inquire;
	   }

	public int updateBoard(Connection connection, Board board) {
		int result = 0; 
		PreparedStatement pstmt = null;
		String query = "UPDATE BOARD SET B_TITLE=?,B_CONTENT=?,B_ORIGINAL_FILENAME=?,B_RENAMED_FILENAME=?,B_UPDATE_DATE=SYSDATE WHERE B_NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getOriginalFileName());
			pstmt.setString(4, board.getRenamedFileName());
			pstmt.setInt(5, board.getNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertBoard(Connection connection, Board board) {
		int result = 0;
        PreparedStatement pstmt = null;
        String query = "INSERT INTO BOARD VALUES(SEQ_BOARD_NO.NEXTVAL,DEFAULT,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";

        try {
            pstmt = connection.prepareStatement(query);

            pstmt.setInt(1, board.getWriterNo());
            pstmt.setString(2, board.getTitle());
            pstmt.setString(3, board.getContent());
            pstmt.setString(4, board.getOriginalFileName());
            pstmt.setString(5, board.getRenamedFileName());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }

        return result;
    }

	public Board findBoardByNo(Connection connection, int no) {
		Board board = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT B.B_NO, "
							+ "B.B_TITLE, "
							+ "M.M_ID, "
							+ "B.B_READCOUNT, "
							+ "B.B_ORIGINAL_FILENAME, "
							+ "B.B_RENAMED_FILENAME, "
							+ "B.B_CONTENT, "
							+ "B.B_CREATE_DATE, "
							+ "B.B_UPDATE_DATE "
					  + "FROM BOARD B "
					  + "JOIN MEMBER M ON(B.B_WRITER_NO = M.M_NO) "
					  + "WHERE B.B_STATUS = 'Y' AND B.B_NO=?";
		
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board();
				
				board.setNo(rs.getInt("B_NO"));
				board.setTitle(rs.getString("B_TITLE"));
				board.setWriterId(rs.getString("M_ID"));
				board.setReadCount(rs.getInt("B_READCOUNT"));
				board.setOriginalFileName(rs.getString("B_ORIGINAL_FILENAME"));
				board.setRenamedFileName(rs.getString("B_RENAMED_FILENAME"));
				board.setContent(rs.getString("B_CONTENT"));
				
				board.setReplies(this.getRepliesByNo(connection, no));
				board.setCreateDate(rs.getDate("B_CREATE_DATE"));
				board.setUpdateDate(rs.getDate("B_UPDATE_DATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return board;
}


	public int updateStatus(Connection connection, int no, String status) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE BOARD SET B_STATUS=? WHERE B_NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, status);
			pstmt.setInt(2, no);			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public List<Reply> getRepliesByNo(Connection connection, int no) {
		List<Reply> replies = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query ="SELECT R.NO, R.BOARD_NO, R.CONTENT, M.M_ID, R.CREATE_DATE, R.MODIFY_DATE\r\n"
				+ "FROM REPLY R\r\n"
				+ "JOIN MEMBER M ON(R.WRITER_NO = M.M_NO)\r\n"
				+ "WHERE R.STATUS='Y' AND BOARD_NO=?\r\n"
				+ "ORDER BY R.NO DESC";
		
		try {
			pstmt = connection.prepareCall(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Reply reply = new Reply();
				
				reply.setNo(rs.getInt("NO"));
//				reply.setBcNo(rs.getInt("BC_NO"));
				reply.setBoardNo(rs.getInt("BOARD_NO"));
				reply.setContent(rs.getString("CONTENT"));
				reply.setWriterId(rs.getString("M_ID"));
				reply.setCreateDate(rs.getDate("CREATE_DATE"));
				reply.setUpdateDate(rs.getDate("MODIFY_DATE"));
				
				replies.add(reply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		
		return replies;
	}

	public int insertReply(Connection connection, Reply reply) {
		int result = 0;
        PreparedStatement pstmt = null;
        String query = "INSERT INTO REPLY VALUES(SEQ_REPLY_NO.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT)";

        try {
            pstmt = connection.prepareStatement(query);

            pstmt.setInt(1, reply.getBoardNo());
            pstmt.setInt(2, reply.getWriterNo());
            pstmt.setString(3, reply.getContent());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }

        return result;
   
	}

	public int updateReadCount(Connection connection, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query="UPDATE BOARD SET B_READCOUNT=? WHERE B_NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			board.setReadCount(board.getReadCount() + 1);
			
			pstmt.setInt(1, board.getReadCount());
			pstmt.setInt(2, board.getNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
				
		return result;
	}

	public int replyupdateStatus(Connection connection, int no, String status) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query ="UPDATE REPLY SET STATUS=? WHERE NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, status);
			pstmt.setInt(2, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		
		
		return result;
	}


}