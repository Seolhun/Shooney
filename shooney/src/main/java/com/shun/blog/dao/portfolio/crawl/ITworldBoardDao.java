package com.shun.blog.dao.portfolio.crawl;

import java.util.List;

import com.shun.blog.model.portfolio.crawl.ITworldBoard;

public interface ITworldBoardDao {

	ITworldBoard findById(int id);

	List<ITworldBoard> findAllITworldBoards();

	void saveITworldBoard(ITworldBoard itWorldBoard);

	void deleteUserById(int id);
}
