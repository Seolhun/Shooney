package com.shun.blog.dao.board;

import java.util.List;

import com.shun.blog.model.board.Board;
import com.shun.blog.model.common.Paging;

public interface BoardDao {

	List<Board> findAllBoards(Paging paging);

	int getCount(Paging paging);

	Board findById(int id);

	void saveBoard(Board Board);

	void deleteBoardById(int id);

}
