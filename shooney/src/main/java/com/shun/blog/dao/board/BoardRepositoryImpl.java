package com.shun.blog.dao.board;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.shun.blog.dao.AbstractDao;
import com.shun.blog.model.board.Board;
import com.shun.blog.model.common.Paging;

@Repository
public class BoardRepositoryImpl extends AbstractDao<Integer, Board> implements BoardRepository {
	static final Logger LOG = LoggerFactory.getLogger(BoardRepositoryImpl.class);

	@Override
	public void insert(Board board) {
		LOG.info("param : insert : {}", board.toString());
		persist(board);
	}
	
	@Override
	public Board selectById(Long id) throws Exception{
		Board board = getByLong(id);
		LOG.info("return : selectById : {}", board.toString());
		return board;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Board> selectList(Paging paging) throws Exception {
		LOG.info("param : selectList : {}", paging.toString());
		int cPage = paging.getCurrentPage();
		int sType = paging.getSearchType();
		int sDate = paging.getSearchDate();
		String sText = paging.getSearchText();
		int limit = paging.getLimit();
		String portfolioType = paging.getPortfolioType();
		
		// 검색 로직
		Criteria criteria = createEntityCriteria()
				.addOrder(Order.desc("boardId"))
				.add(Restrictions.eq("delCheck", 0))
				.setFirstResult((cPage - 1) * limit)
				.setMaxResults(limit).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		if (portfolioType != null) {
			criteria.add(Restrictions.eq("portfolioType", portfolioType));
		}
		
		//날짜 이용.
		if(sDate != 0){
			
		}

		//검색어 이용.
		if(sText!=""){
			if (sType == 1) {
				criteria.add(Restrictions.like("title", "%" + sText + "%"));
			} else if (paging.getSearchDate() != 0 && sType == 2) {
				criteria.add(Restrictions.like("content", "%" + sText + "%"));
			} else if (paging.getSearchDate() != 0 && sType == 3) {
				criteria.add(Restrictions.like("createdBy", "%" + sText + "%"));
			} else if (paging.getSearchDate() != 0 && sType == 4) {
				criteria.add(Restrictions.like("title", "%" + sText + "%")).add(Restrictions.like("content", "%" + sText + "%"));
			}	
		}
		
		
		List<Board> boards = (List<Board>) criteria.list();
		return boards;
	}
	
	@Override
	public int getCount(Paging paging) throws Exception {
		LOG.info("param : getCount : {}", paging.toString());
		int sType = paging.getSearchType();
		int sDate = paging.getSearchDate();
		String sText = paging.getSearchText();
		
		// 검색 로직
		Criteria criteria = createEntityCriteria();
				
		//날짜 이용.
		if(sDate != 0){
			
		}

		//검색어 이용.
		if(sText!=""){
			if (sType == 1) {
				criteria.add(Restrictions.like("title", "%" + sText + "%"));
			} else if (paging.getSearchDate() != 0 && sType == 2) {
				criteria.add(Restrictions.like("content", "%" + sText + "%"));
			} else if (paging.getSearchDate() != 0 && sType == 3) {
				criteria.add(Restrictions.like("createdBy", "%" + sText + "%"));
			} else if (paging.getSearchDate() != 0 && sType == 4) {
				criteria.add(Restrictions.like("title", "%" + sText + "%")).add(Restrictions.like("content", "%" + sText + "%"));
			}	
		}
		
		Integer totalResult = ((Number)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		LOG.info("param : getCount : {}", totalResult);
		return totalResult;
	}

//	@Override
//	public int getCount(Paging paging) throws Exception {
//		LOG.info("param : getCount : {}", paging.toString());
//		String condition = "";
//		String condition2 = "";
//		if (paging.getPortfolioType() != null) {
//			condition2 = " WHERE BOARD_PORTFOLIO_TYPE='" + paging.getPortfolioType().toLowerCase() + "'";
//		}
//		Query query = rawQuery("SELECT COUNT(*) FROM TB_BOARD " + condition + condition2);
//		return ((Number) query.uniqueResult()).intValue();
//	}

	@Override
	public void deleteById(Long id) {
		LOG.info("param : deleteById : {}", id);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Board board = (Board) crit.uniqueResult();
		delete(board);
	}
}
