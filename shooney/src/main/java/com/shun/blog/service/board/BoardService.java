package com.shun.blog.service.board;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.common.model.Paging;
import com.shun.blog.model.board.Board;

@Transactional(transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface BoardService {

	@Transactional(transactionManager="txManager", noRollbackFor={NullPointerException.class}, readOnly=true)
	Board findById(Long id);

	void saveBoard(Board board);

	void updateBoard(Board board);

	void deleteBoardById(Long id);

	List<Board> findAllBoards(Paging paging);
	
	int getCount(Paging paging);
}