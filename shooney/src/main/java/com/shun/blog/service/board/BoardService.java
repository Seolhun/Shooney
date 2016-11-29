package com.shun.blog.service.board;

import java.util.List;

import com.shun.blog.model.board.Board;
import com.shun.blog.model.common.Paging;

public interface BoardService {

	Board findById(int id);

	void saveBoard(Board board);

	void updateBoard(Board board);

	void deleteUserById(int id);

	List<Board> findAllBoards(Paging paging);
	
	int getCount(Paging paging);
}