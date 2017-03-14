package com.shun.blog.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shun.blog.dao.board.BoardRepository;
import com.shun.blog.model.board.Board;
import com.shun.blog.model.common.Paging;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardDao;
	
	@Override
	public Board selectById(Long id) {
		return boardDao.selectById(id);
	}

	@Override
	public void insert(Board board) {
		boardDao.insert(board);
	}
	
	@Override
	public void update(Board board) {
		Board entity = boardDao.selectById(board.getId());
		//읽을시 쿠키 읽기
		if(board.getHits()==1){
			entity.setHits(entity.getHits()+1);
		} else if(entity != null){
			entity.setTitle(board.getTitle());
			entity.setContent(board.getContent());
			entity.setCreatedBy(board.getCreatedBy());
			entity.setModifiedBy(board.getModifiedBy());
			entity.setDepth(board.getDepth());
			entity.setEntityName(board.getEntityName());
			entity.setPortfolioType(board.getPortfolioType());
		}
	}

	@Override
	public void deleteById(Long id) {
		boardDao.deleteById(id);
	}
	
	@Override
	public List<Board> findAll(Paging paging) {
		return boardDao.findAll(paging);
	}

	@Override
	public int getCount(Paging paging) {
		return boardDao.getCount(paging);
	}
}
