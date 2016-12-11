package com.shun.blog.dao.portfolio.crawl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.shun.blog.dao.AbstractDao;
import com.shun.blog.model.portfolio.crawl.ITworldBoard;

@Repository("ITworldBoardDao")
public class ITworldBoardDaoImpl extends AbstractDao<Integer, ITworldBoard> implements ITworldBoardDao {

	static final Logger logger = LoggerFactory.getLogger(ITworldBoardDaoImpl.class);
	
	public ITworldBoard findById(int id) {
		ITworldBoard ITworldBoard = getByKey(id);
		return ITworldBoard;
	}
	
	@SuppressWarnings("unchecked")
	public List<ITworldBoard> findAllITworldBoards() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<ITworldBoard> ITworldBoards =(List<ITworldBoard>) criteria.list();
		return ITworldBoards;
	}

	public void saveITworldBoard(ITworldBoard itWorldBoard) {
		persist(itWorldBoard);
	}

	public void deleteUserById(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		ITworldBoard ITworldBoard = (ITworldBoard)crit.uniqueResult();
		delete(ITworldBoard);
	}
}
