package com.shun.blog.service.board;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.model.board.Board;
import com.shun.blog.model.common.Paging;

@Transactional(transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface BoardService {

	@Transactional(transactionManager="txManager", noRollbackFor={NullPointerException.class}, readOnly=true)
	Board selectById(Long id);

	void insert(Board board);

	void update(Board board);

	void deleteById(Long id);

	List<Board> findAll(Paging paging);
	
	int getCount(Paging paging);
}