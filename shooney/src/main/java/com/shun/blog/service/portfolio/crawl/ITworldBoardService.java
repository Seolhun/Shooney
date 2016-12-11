package com.shun.blog.service.portfolio.crawl;

import java.util.List;

import com.shun.blog.model.portfolio.crawl.ITworldBoard;

public interface ITworldBoardService {
	
	ITworldBoard findById(int id);
	
	void saveITworldBoard(ITworldBoard itWorldBoard);
	
	void updateITworldBoard(ITworldBoard itWorldBoard);
	
	void deleteUserById(int id);

	List<ITworldBoard> findAllITworldBoards(); 
}