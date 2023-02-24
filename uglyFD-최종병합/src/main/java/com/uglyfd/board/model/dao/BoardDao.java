package com.uglyfd.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uglyfd.board.model.vo.Board;
//import com.uglyfd.board.model.vo.Board;
//import com.uglyfd.board.model.vo.Reply;
import com.uglyfd.common.util.PageInfo;

import static com.uglyfd.common.jdbc.JDBCTemplate.close;

public class BoardDao {

    public int getBoardCount(Connection connection) {
        int count = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = "SELECT COUNT(*) FROM BOARD WHERE BC_NO='2' AND STATUS='Y'";

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
	      String query = "SELECT RNUM, NO, TITLE, M_ID, CREATE_DATE, ORIGINAL_FILENAME, READCOUNT, STATUS  "
	      		+ "FROM (\r\n"
	      		+ "    SELECT ROWNUM AS RNUM, NO,  TITLE, M_ID, CREATE_DATE, ORIGINAL_FILENAME, READCOUNT, STATUS "
	      		+ "    FROM ("
	      		+ "        SELECT  B.NO, B.TITLE, M.M_ID, B.CREATE_DATE, B.ORIGINAL_FILENAME, B.READCOUNT, B.STATUS "
	      		+ "        FROM BOARD B JOIN MEMBER M ON(B.WRITER_NO = M.M_NO) \r\n"
	      		+ "        WHERE B.STATUS = 'Y'  ORDER BY B.NO DESC\r\n"
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
	            board.setNo(rs.getInt("NO"));
	            board.setTitle(rs.getString("TITLE"));
	            board.setWriterId(rs.getString("M_ID"));
	            board.setCreateDate(rs.getDate("CREATE_DATE"));
	            board.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
	            board.setReadCount(rs.getInt("READCOUNT"));
	            board.setStatus(rs.getString("STATUS"));
	            
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
		String query = "UPDATE BOARD SET TITLE=?,CONTENT=?,ORIGINAL_FILENAME=?,RENAMED_FILENAME=?,MODIFY_DATE=SYSDATE WHERE NO=?";
		
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
		String query = "SELECT B.NO, "
							+ "B.TITLE, "
							+ "M.M_ID, "
							+ "B.READCOUNT, "
							+ "B.ORIGINAL_FILENAME, "
							+ "B.RENAMED_FILENAME, "
							+ "B.CONTENT, "
							+ "B.CREATE_DATE, "
							+ "B.MODIFY_DATE "
					  + "FROM BOARD B "
					  + "JOIN MEMBER M ON(B.WRITER_NO = M.M_NO) "
					  + "WHERE B.STATUS = 'Y' AND B.NO=?";
		
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board();
				
				board.setNo(rs.getInt("NO"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriterId(rs.getString("M_ID"));
				board.setReadCount(rs.getInt("READCOUNT"));
				board.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				board.setRenamedFileName(rs.getString("RENAMED_FILENAME"));
				board.setContent(rs.getString("CONTENT"));
				
//				board.setCreateDate(rs.getDate("CREATE_DATE"));
//				board.setUpdateDate(rs.getDate("UPDATE_DATE"));
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
		String query = "UPDATE BOARD SET STATUS=? WHERE NO=?";
		
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