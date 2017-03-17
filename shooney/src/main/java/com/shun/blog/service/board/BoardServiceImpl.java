package com.shun.blog.service.board;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.dao.board.BoardRepository;
import com.shun.blog.model.board.Board;
import com.shun.blog.model.common.Paging;

@Service
@Transactional(transactionManager="txManager")
public class BoardServiceImpl implements BoardService {
	static final Logger LOG = LoggerFactory.getLogger(BoardServiceImpl.class);

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	public BoardServiceImpl(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	@Override
	public Board selectById(Long id) throws Exception{
		LOG.info("return : selectById : {}",id);
		Board board=boardRepository.selectById(id);
		return board;
	}

	@Override
	public void insert(Board board) {
		LOG.info("param : insert : {}",board.toString());
		boardRepository.insert(board);
	}
	
	@Override
	public void update(Board board) throws Exception{
		LOG.info("param : update : {}",board.toString());
		Board entity = boardRepository.selectById(board.getBoardId());
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
		LOG.info("param : deleteById : {}",id);
		boardRepository.deleteById(id);
	}
	
	@Override
	public List<Board> selectList(Paging paging) throws Exception{
		LOG.info("param : selectList : {}",paging.toString());
		return boardRepository.selectList(paging);
	}

	@Override
	public int getCount(Paging paging) throws Exception{
		LOG.info("param : getCount : {}",paging.toString());
		return boardRepository.getCount(paging);
	}
}
