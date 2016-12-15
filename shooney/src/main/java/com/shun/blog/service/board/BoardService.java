package com.shun.blog.service.board;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.model.board.Board;
import com.shun.blog.model.common.Paging;

@Transactional(transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface BoardService {

	@Transactional(transactionManager="txManager", noRollbackFor={NullPointerException.class}, readOnly=true)
	Board findById(int id);

	void saveBoard(Board board);

	void updateBoard(Board board);

	void deleteUserById(int id);

	List<Board> findAllBoards(Paging paging);
	
	int getCount(Paging paging);
}