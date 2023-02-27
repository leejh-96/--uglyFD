package com.uglyfd.board.model.service;

import static com.uglyfd.common.jdbc.JDBCTemplate.close;
import static com.uglyfd.common.jdbc.JDBCTemplate.commit;
import static com.uglyfd.common.jdbc.JDBCTemplate.rollback;
import static com.uglyfd.common.jdbc.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.uglyfd.board.model.dao.BoardDao;
import com.uglyfd.board.model.vo.Board;
import com.uglyfd.board.model.vo.Reply;
//import com.uglyfd.board.model.vo.Reply;
//import com.uglyfd.common.jdbc.JDBCTemplate;
import com.uglyfd.common.util.PageInfo;

public class BoardService {

    public int getBoardCount() {
        int count = 0;
        Connection connection = getConnection();
        System.out.println("service getboardcount 입니다.");
        count = new BoardDao().getBoardCount(connection);
        System.out.println("service getboardcount 입니다.");
        close(connection);

        return count;
    }

    public List<Board> getBoardInquire(PageInfo pageInfo) {
        List<Board> inquire = null;
        Connection connection = getConnection();
        System.out.println("service getBoardInquire 입니다.");
        inquire = new BoardDao().findAll(connection, pageInfo);
        System.out.println("service getBoardInquire 입니다.");
        close(connection);

        return inquire;
    }


	public Board getBoardByNo(int no, boolean hasRead) {
		Board board = null;
		Connection connection = getConnection();
		
		board = new BoardDao().findBoardByNo(connection, no);
		
		if(board != null && !hasRead) {
			int result = new BoardDao().updateReadCount(connection, board);
			
			if(result > 0) {
				commit(connection);
			} else {
				rollback(connection);
			}
		}
		
		close(connection);
		
		return board;
	}
	
	public int save(Board board) {
		int result = 0;
		Connection connection = getConnection();
		
		if(board.getNo() > 0) {
			result = new BoardDao().updateBoard(connection, board);
			
		} else {
			result = new BoardDao().insertBoard(connection, board);
		}
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		close(connection);
		
		return result;
	}

	public int delete(int no) {
		int result = 0;
		Connection connection = getConnection();
		
		result = new BoardDao().updateStatus(connection, no, "N");
		
		if(result > 0 ) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	public int saveReply(Reply reply) {
		int result = 0;
		Connection connection = getConnection();
		
		result = new BoardDao().insertReply(connection, reply);
		
		if(result > 0 ) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	public int replydelete(int no) {
		int result = 0;
		Connection connection = getConnection();
		
		result = new BoardDao().replyupdateStatus(connection, no, "N");
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		
		close(connection);
		
		return result;
	}


	}


