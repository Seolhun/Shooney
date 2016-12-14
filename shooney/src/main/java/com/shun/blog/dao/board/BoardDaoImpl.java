package com.shun.blog.dao.board;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.shun.blog.dao.AbstractDao;
import com.shun.blog.model.board.Board;
import com.shun.blog.model.common.Paging;

@Repository("boardDao")
public class BoardDaoImpl extends AbstractDao<Integer, Board> implements BoardDao {

	static final Logger logger = LoggerFactory.getLogger(BoardDaoImpl.class);

	@SuppressWarnings("unchecked")
	public List<Board> findAllBoards(Paging paging) {
		int cPage = paging.getcPage();
		int sType = paging.getsType();
		String sText = paging.getsText();
		int limit = paging.getLimit();
		String entityName = paging.getEntityName();
		String pfName = paging.getPfName();

		// 검색 로직
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("id")).setFirstResult((cPage - 1) * limit)
				.setMaxResults(limit).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		// 클래스에 객체 명을 따라간다.
		if (entityName != null) {
			criteria.add(Restrictions.eq("entityName", entityName)).add(Restrictions.eq("delCheck", 0));
		}

		if (pfName != null) {
			criteria.add(Restrictions.eq("pfName", pfName));
		}

		if (paging.getsType() != 0 && sType == 1) {
			criteria.add(Restrictions.like("title", "%" + sText + "%"));
		} else if (paging.getsType() != 0 && sType == 2) {
			criteria.add(Restrictions.like("content", "%" + sText + "%"));
		} else if (paging.getsType() != 0 && sType == 3) {
			criteria.add(Restrictions.like("writer", "%" + sText + "%"));
		} else if (paging.getsType() != 0 && sType == 4) {
			criteria.add(Restrictions.like("title", "%" + sText + "%"))
					.add(Restrictions.like("content", "%" + sText + "%"));
		}

		logger.info(criteria.toString());
		List<Board> boards = (List<Board>) criteria.list();
		return boards;
	}

	@Override
	public int getCount(Paging paging) {
		String condition = "";
		String condition2 = "";
		if (paging.getEntityName() != null) {
			condition = "WHERE entityname='" + paging.getEntityName() + "'";
		}
		if (paging.getPfName() != null) {
			condition2 = " AND pfname='" + paging.getPfName().toUpperCase() + "'";
		}
		Query query = rawQuery("SELECT COUNT(*) FROM BOARD " + condition + condition2);
		return ((Number) query.uniqueResult()).intValue();
	}

	@Override
	public Board findById(int id) {
		Board board = getByKey(id);
		return board;
	}

	@Override
	public void saveBoard(Board board) {
		persist(board);
	}

	@Override
	public void deleteUserById(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Board board = (Board) crit.uniqueResult();
		delete(board);
	}
}
