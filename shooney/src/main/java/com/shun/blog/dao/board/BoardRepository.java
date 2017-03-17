package com.shun.blog.dao.board;

import java.util.List;

import com.shun.blog.model.board.Board;
import com.shun.blog.model.common.Paging;

public interface BoardRepository {
	void insert(Board Board);
	
	Board selectById(Long id) throws Exception;
	
	List<Board> selectList(Paging paging) throws Exception;

	int getCount(Paging paging) throws Exception;

	void deleteById(Long id);

}
