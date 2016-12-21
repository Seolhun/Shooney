package com.shun.blog.dao.comment;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.shun.blog.dao.AbstractDao;
import com.shun.blog.model.comment.Comment;
import com.shun.blog.model.common.Paging;

@Repository("commentDao")
public class CommentDaoImpl extends AbstractDao<Integer, Comment> implements CommentDao {

	static final Logger logger = LoggerFactory.getLogger(CommentDaoImpl.class);

	@SuppressWarnings("unchecked")
	public List<Comment> findAllComments(Paging paging) {
		int cPage = paging.getcPage();
		int sType = paging.getsType();
		String sText = paging.getsText();
		int limit = paging.getLimit();

		// 검색 로직
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("id")).setFirstResult((cPage - 1) * limit)
				.setMaxResults(limit).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).add(Restrictions.eq("delCheck", 0))
				.add(Restrictions.eq("board_id", paging.getId()));

		if (paging.getsType() != 0 && sType == 2) {
			criteria.add(Restrictions.like("content", "%" + sText + "%"));
		} else if (paging.getsType() != 0 && sType == 3) {
			criteria.add(Restrictions.like("writer", "%" + sText + "%"));
		} else if (paging.getsType() != 0 && sType == 4) {
			criteria.add(Restrictions.like("content", "%" + sText + "%"));
		}

		logger.info(criteria.toString());
		List<Comment> comment = (List<Comment>)criteria.list();
		return comment;
	}

	@Override
	public int getCount(Paging paging) {
		String condition = "";
		condition = "WHERE board_id="+paging.getId();
		Query query = rawQuery("SELECT COUNT(*) FROM COMMENT " + condition);
		return ((Number) query.uniqueResult()).intValue();
	}

	@Override
	public Comment findById(int id) {
		Comment comment = getByKey(id);
		return comment;
	}

	@Override
	public void saveComment(Comment comment) {
		persist(comment);
	}

	@Override
	public void deleteCommentById(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Comment comment = (Comment) crit.uniqueResult();
		delete(comment);
	}
}
