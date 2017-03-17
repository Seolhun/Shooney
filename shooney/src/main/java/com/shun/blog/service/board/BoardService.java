package com.shun.blog.service.board;

import java.util.List;

import com.shun.blog.model.board.Board;
import com.shun.blog.model.common.Paging;

public interface BoardService {

	Board selectById(Long id) throws Exception;

	void insert(Board board);

	void update(Board board) throws Exception;

	void deleteById(Long id);

	List<Board> selectList(Paging paging) throws Exception;
	
	int getCount(Paging paging) throws Exception;
}