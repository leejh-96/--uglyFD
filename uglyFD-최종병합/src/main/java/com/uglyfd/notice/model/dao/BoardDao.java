package com.uglyfd.notice.model.dao;

import static com.uglyfd.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.uglyfd.board.model.vo.Board;
import com.uglyfd.common.util.PageInfo;
import com.uglyfd.notice.model.vo.Board;
import com.uglyfd.notice.model.vo.Reply;

public class BoardDao {

	public int getBoardCount(Connection connection) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM NBOARD WHERE STATUS = 'Y'";	//SELECT문의 결과문은 항상 resultSet으로 반환한다.
		
		try {
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);	//DB에서 1번 컬럼의 값을 가져온다.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return count;
	}

//	select쿼리문을 통해서 조회되는 data가 없으면 null값이 아닌 빈 list를 반환해준다. 대신에 data가 있으면 list OBJECT에 담아서 하나하나 return해준다.
	public List<Board> findAll(Connection connection, PageInfo pageInfo) {
		List<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT RNUM, NB_NO, NBO_TITLE, NBO_WRITER, NBO_DATE, ORIGINAL_FILENAME, NBO_HIT, STATUS "
					 + "FROM ( "
					 + "    SELECT ROWNUM AS RNUM, NB_NO, NBO_TITLE, NBO_WRITER, NBO_DATE, ORIGINAL_FILENAME, NBO_HIT, STATUS "
					 + "    FROM ( "
					 + "        SELECT  B.NB_NO, B.NBO_TITLE, B.NBO_WRITER, B.NBO_DATE, B.ORIGINAL_FILENAME, B.NBO_HIT, B.STATUS "
					 + "        FROM MEMBER M  "
					 + "        INNER JOIN NBOARD B ON(M.M_NO = B.NM_NO)  "			
					 + "        WHERE B.STATUS = 'Y'  ORDER BY B.NB_NO DESC "
					 + "    ) "
					 + ") "
					 + "WHERE RNUM BETWEEN ? and ?";
		
		try {
			pstmt = connection.prepareStatement(query);
//			pageInfo클래스에서 현재 페이지의 맨 처음과 끝에있는 페이지를 반환한다.
			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());
			
			rs =pstmt.executeQuery();
			
			while(rs.next()) {
//	조회되는 컬럼명을 data로 읽어서 객체에 set메서드를 통해서 넣어준다.
				Board board = new Board();
				board.setB_no(rs.getInt("NB_NO")); 		
				board.setRowNum(rs.getInt("RNUM"));
				board.setBo_writer((rs.getString("NBO_WRITER")));
				board.setBo_title(rs.getString("NBO_TITLE"));
				board.setBo_date(rs.getDate("NBO_DATE"));
				board.setOriginal_filename(rs.getString("ORIGINAL_FILENAME"));
				board.setBo_hit((rs.getInt("NBO_HIT")));
				board.setStatus(rs.getString("STATUS"));
				
				list.add(board);
				
				System.out.println("BoardDao"+board.getB_no());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	public Board findBoardByNo(Connection connection, int b_no) {
		Board board = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT  B.NB_NO,  "
								+ " B.NBO_TITLE,  "
								+ " B.NBO_WRITER, "
								+ " B.NBO_HIT,  "
								+ " B.ORIGINAL_FILENAME,  "
								+ " B.RENAMED_FILENAME,  "
								+ " B.NBO_CON, "
								+ " B.NBO_DATE,  "
								+ " B.NBO_UPDATE "
//								+ " B.BO_WRITER "
								+ "FROM MEMBER M "
								+ "INNER JOIN NBOARD B ON(M.M_NO = B.NM_NO) "							
								+ "WHERE B.STATUS = 'Y' AND B.NB_NO=?";
		
		
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, b_no);			
			
			rs =pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board();
			
				board.setB_no(rs.getInt("NB_NO"));
				board.setBo_title(rs.getString("NBO_TITLE"));
				board.setBo_writer(rs.getString("NBO_WRITER"));
				board.setBo_hit(rs.getInt("NBO_HIT"));
				board.setOriginal_filename(rs.getString("ORIGINAL_FILENAME"));
				board.setRenamed_filename((rs.getString("RENAMED_FILENAME")));
				board.setBo_con(rs.getString("NBO_CON"));
//				댓글 조회
				board.setReplies(this.getRepliesByNo(connection, b_no));
				board.setBo_date(rs.getDate("NBO_DATE"));
				board.setBo_date(rs.getDate("NBO_UPDATE"));
//				board.setBo_writer(rs.getString("BO_WRITER"));
				System.out.println("다오 findBoardByNo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return board;
	}

	public int insertBoard(Connection connection, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO NBOARD VALUES(SEQ_NBOARD_NO.NEXTVAL, ?, DEFAULT, DEFAULT, ?, DEFAULT, '관리자',1 , DEFAULT, ?, ?)";
		System.out.println(query);
		try {
			pstmt = connection.prepareStatement(query);			
			
			pstmt.setString(1, board.getBo_title());
			pstmt.setString(2, board.getBo_con());
//			pstmt.setString(3, board.getBo_writer());
//			pstmt.setInt(3, board.getM_no());
			pstmt.setString(3, board.getOriginal_filename());
			pstmt.setString(4, board.getRenamed_filename());
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
		
	}

	public int updateBoard(Connection connection, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE NBOARD SET NBO_TITLE=?,NBO_CON=?,ORIGINAL_FILENAME=?,RENAMED_FILENAME=?,NBO_UPDATE=SYSDATE WHERE NB_NO=?";
		System.out.println(query);
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, board.getBo_title());
			pstmt.setString(2, board.getBo_con());
			pstmt.setString(3, board.getOriginal_filename());
			pstmt.setString(4, board.getRenamed_filename());
			pstmt.setInt(5, board.getB_no());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

//	게시글을 삭제하는 메서드
	public int updateStatus(Connection connection, int no, String status) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "UPDATE NBOARD SET STATUS=? WHERE NB_NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, status);
			pstmt.setInt(2, no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	
//	게시글의 댓글을 조회하는 메서드이다. (댓글이 있을수도 있고, 없을수도 있으므로 List클래스로 구현한다.)
	public List<Reply> getRepliesByNo(Connection connection, int no){
	      List<Reply> replies = new ArrayList<>();
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String query = 
	            "SELECT R.NNO, "
	                   + "R.NBOARD_NO, "
	                   + "R.NCONTENT, "
	                   + "M.M_ID, "
	                   + "R.NCREATE_DATE, "
	                   + "R.NMODIFY_DATE "
	              + "FROM NREPLY R "
	              + "JOIN MEMBER M ON(R.NWRITER_NO = M.M_NO) "
	              + "WHERE R.NSTATUS='Y' AND NBOARD_NO=? "
	              + "ORDER BY R.NNO DESC";   
	      
	      try {
	         pstmt = connection.prepareStatement(query);
	         pstmt.setInt(1, no);
	         rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	            Reply reply = new Reply();
	            
	            reply.setNo(rs.getInt("NNO"));
	            reply.setBoardNo(rs.getInt("NBOARD_NO"));
	            reply.setContent(rs.getString("NCONTENT"));
	            reply.setWriterId(rs.getString("M_ID"));
	            reply.setCreateDate(rs.getDate("NCREATE_DATE"));
	            reply.setModifyDate(rs.getDate("NMODIFY_DATE"));
	            
	            replies.add(reply);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }finally {
	         close(rs);
	         close(pstmt);
	      }
	      return replies;
	   }

//	게시글에 댓글을 삽입해주는 메서드이다.
	public int insertReply(Connection connection, Reply reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO NREPLY VALUES(SEQ_NREPLY_NO.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT)";
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, reply.getBoardNo());
			pstmt.setInt(2, reply.getWriterNo());
			pstmt.setString(3, reply.getContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

//	게시글의 조회수를 증가시켜주는 메서드
	public int updateReadCount(Connection connection, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE NBOARD SET NBO_HIT=? WHERE NB_NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			board.setBo_hit(board.getBo_hit()+1);
			
			pstmt.setInt(1, board.getBo_hit());
			pstmt.setInt(2, board.getB_no());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
}
