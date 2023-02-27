package com.uglyfd.notice.model.service;

import static com.uglyfd.common.jdbc.JDBCTemplate.close;
import static com.uglyfd.common.jdbc.JDBCTemplate.commit;
import static com.uglyfd.common.jdbc.JDBCTemplate.getConnection;
import static com.uglyfd.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.uglyfd.common.util.PageInfo;
import com.uglyfd.notice.model.dao.BoardDao;
import com.uglyfd.notice.model.vo.Board;
import com.uglyfd.notice.model.vo.Reply;;

public class BoardService {

	public int getBoardCount() {
		int count = 0;
		Connection connection = getConnection();
		
		count = new BoardDao().getBoardCount(connection);
		
		close(connection);
		
		return count;
	}

//	SELECT한 쿼리문의 결과를 list객체로 return해주는 메서드. 실제로 DB에서 DATA를 조회하는 작업은 Dao클래스를 통해서 이뤄진다. 따라서 connection을 얻어야 한다. 
	public List<Board> getBoardList(PageInfo pageInfo) {
		List<Board> list = null;
		Connection connection = getConnection();
		
		list = new BoardDao().findAll(connection, pageInfo);
		
		close(connection);
		return list;		//list를 return해준다.
	}

//	
	public Board getBoardByNo(int b_no, boolean hasRead) {
		Board board = null;
		Connection connection = getConnection();
		System.out.println("서비스findBoardByNo");
		board = new BoardDao().findBoardByNo(connection, b_no);
		
//	connection을 닫아주기 전에 게시글 조회수를 증가시켜주는 logic를 구현한다.
		if(board != null && !hasRead) {
			int result = new BoardDao().updateReadCount(connection, board);
			
			if(result > 0) {
				commit(connection);
			}else {
				rollback(connection);
			}
		}
		
		close(connection);	//return하기전에 connection을 닫아준다.
		
		return board;
	}

//게시글을 저장하는 역할을 담당하는 메서드이다.
	public int save(Board board) {
		int result = 0;
//	하나의 작업이 성공되면 트랜잭션 성공 -> 실패시 rollback처리를 통한 트랜잭션 실패처리로 구현해야 한다.
		Connection connection = getConnection();
		
//	게시글 수정하는 logic -> 기본키에 해당하는 값(b_no)이 있으면 수정 아니면 수정실패
		if(board.getB_no() != 0) {
//			update 작업
			result = new BoardDao().updateBoard(connection, board);
		}else {
//	실제 INSERT 작업은 BoardDao클래스에서 처리한다.
			result = new BoardDao().insertBoard(connection, board);
		}
		
		
//	DB처리가 성공할 시 commit, 실패 시 rollback처리  
		if(result > 0) {
			commit(connection);
		}else {
			rollback(connection);
		}
		close(connection);
		return result;
	}

	public int delete(int no) {
		int result = 0;
		Connection connection = getConnection();
		
		result = new BoardDao().updateStatus(connection, no, "N");
		
		if(result > 0) {
			commit(connection);
		}else {
			rollback(connection);
		}
		close(connection);
		return result;
	}

	public int saveReply(Reply reply) {
		int result = 0;
		Connection connection = getConnection();
		
//		댓글을 삽입하는 작업을 DAO클래스에게 넘긴다.
		result = new BoardDao().insertReply(connection, reply);
		
		if(result > 0) {
			commit(connection);
		}else {
			rollback(connection);
		}
		close(connection);
		
		return result;
	}

}
