package com.shun.blog.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shun.blog.dao.board.BoardDao;
import com.shun.blog.model.board.Board;
import com.shun.blog.model.common.Paging;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;
	
	
	public Board findById(int id) {
		return boardDao.findById(id);
	}

	public void saveBoard(Board board) {
		boardDao.saveBoard(board);
	}
	
	public void updateBoard(Board board) {
		Board entity = boardDao.findById(board.getId());
		//읽을시 쿠키 읽기
		if(board.getHits()==1){
			entity.setHits(entity.getHits()+1);
		} else if(entity != null){
			entity.setTitle(board.getTitle());
			entity.setContent(board.getContent());
			entity.setWriter(board.getWriter());
			entity.setDelCheck(board.getDelCheck());
			entity.setDepth(board.getDepth());
			entity.setEntityName(board.getEntityName());
			entity.setPfName(board.getPfName());
		}
	}

	@Override
	public void deleteBoardById(int id) {
		boardDao.deleteBoardById(id);
	}
	
	@Override
	public List<Board> findAllBoards(Paging paging) {
		return boardDao.findAllBoards(paging);
	}

	@Override
	public int getCount(Paging paging) {
		return boardDao.getCount(paging);
	}
}
