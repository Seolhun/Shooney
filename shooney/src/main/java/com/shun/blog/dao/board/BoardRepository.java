package com.shun.blog.dao.board;

import java.util.List;

import com.shun.blog.model.board.Board;
import com.shun.blog.model.common.Paging;

public interface BoardRepository {
	void insert(Board Board);
	
	Board selectById(Long id);
	
	List<Board> selectList(Paging paging);

	int getCount(Paging paging);

	void deleteById(Long id);

}
