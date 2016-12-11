package com.shun.blog.service.portfolio.crawl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.dao.portfolio.crawl.ITworldBoardDao;
import com.shun.blog.model.portfolio.crawl.ITworldBoard;


@Service("ITworldBoardService")
@Transactional
public class ITworldBoardServiceImpl implements ITworldBoardService {

	@Autowired
	private ITworldBoardDao dao;

	public ITworldBoard findById(int id) {
		return dao.findById(id);
	}

	public void saveITworldBoard(ITworldBoard itWorldBoard) {
		dao.saveITworldBoard(itWorldBoard);
	}

	public void updateITworldBoard(ITworldBoard itWorldBoard) {
		ITworldBoard entity = dao.findById(itWorldBoard.getId());
		if (entity != null) {
			entity.setContent(itWorldBoard.getContent());
		}
	}

	@Override
	public void deleteUserById(int id) {
		dao.deleteUserById(id);
	}

	@Override
	public List<ITworldBoard> findAllITworldBoards() {
		return dao.findAllITworldBoards();
	}
}
