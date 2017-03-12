package com.shun.blog.dao.board;

import java.util.List;

import com.shun.blog.common.model.Paging;
import com.shun.blog.model.board.Board;

public interface BoardDao {

	List<Board> findAllBoards(Paging paging);

	int getCount(Paging paging);

	Board findById(Long id);

	void saveBoard(Board Board);

	void deleteBoardById(Long id);

}
