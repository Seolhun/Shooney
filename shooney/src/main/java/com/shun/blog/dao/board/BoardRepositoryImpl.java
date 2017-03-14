package com.shun.blog.dao.board;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.shun.blog.dao.AbstractDao;
import com.shun.blog.model.board.Board;
import com.shun.blog.model.common.Paging;
import com.shun.blog.model.file.FileData;

@Repository("boardDao")
public class BoardRepositoryImpl extends AbstractDao<Integer, Board> implements BoardRepository {

	static final Logger logger = LoggerFactory.getLogger(BoardRepositoryImpl.class);

	@Override
	@SuppressWarnings("unchecked")
	public List<Board> findAll(Paging paging) {
		int cPage = paging.getCurrentPage();
		int sType = paging.getSearchType();
		String sText = paging.getSearchText();
		int limit = paging.getLimit();
		String portfolioType = paging.getPortfolioType();
		
		// 검색 로직
		Criteria criteria = createEntityCriteria()
				.addOrder(Order.desc("id")).add(Restrictions.eq("delCheck", 0))
				.setFirstResult((cPage - 1) * limit)
				.setMaxResults(limit).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		if (portfolioType != null) {
			criteria.add(Restrictions.eq("portfolioType", portfolioType));
		}

		if (paging.getSearchDate() != 0 && sType == 1) {
			criteria.add(Restrictions.like("title", "%" + sText + "%"));
		} else if (paging.getSearchDate() != 0 && sType == 2) {
			criteria.add(Restrictions.like("content", "%" + sText + "%"));
		} else if (paging.getSearchDate() != 0 && sType == 3) {
			criteria.add(Restrictions.like("writer", "%" + sText + "%"));
		} else if (paging.getSearchDate() != 0 && sType == 4) {
			criteria.add(Restrictions.like("title", "%" + sText + "%")).add(Restrictions.like("content", "%" + sText + "%"));
		}

		List<Board> boards = (List<Board>) criteria.list();
		logger.info("return : {}", boards);
		return boards;
	}

	@Override
	public int getCount(Paging paging) {
		String condition = "";
		String condition2 = "";
		if (paging.getPortfolioType() != null) {
			condition2 = " WHERE BOARD_PORTFOLIO_TYPE='" + paging.getPortfolioType().toLowerCase() + "'";
		}
		Query query = rawQuery("SELECT COUNT(*) FROM TB_BOARD " + condition + condition2);
		return ((Number) query.uniqueResult()).intValue();
	}

	@Override
	public Board selectById(Long id) {
		Board board = getByLong(id);
		return board;
	}

	@Override
	public void insert(Board board) {
		persist(board);
	}

	@Override
	public void deleteById(Long id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Board board = (Board) crit.uniqueResult();
		delete(board);
	}
}
