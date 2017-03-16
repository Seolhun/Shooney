package com.shun.blog.service.board;

import java.util.List;

import com.shun.blog.model.board.Board;
import com.shun.blog.model.common.Paging;

public interface BoardService {

	Board selectById(Long id);

	void insert(Board board);

	void update(Board board);

	void deleteById(Long id);

	List<Board> selectList(Paging paging);
	
	int getCount(Paging paging);
}