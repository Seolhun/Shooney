package com.shun.blog.dao.board;

import java.util.List;

import com.shun.blog.model.board.Board;
import com.shun.blog.model.common.Paging;

public interface BoardRepository {

	List<Board> selectList(Paging paging);

	int getCount(Paging paging);

	Board selectById(Long id);

	void insert(Board Board);

	void deleteById(Long id);

}
