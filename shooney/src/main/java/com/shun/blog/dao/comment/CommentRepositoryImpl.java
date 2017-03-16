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

@Repository
public class CommentRepositoryImpl extends AbstractDao<Integer, Comment> implements CommentRepository {

	static final Logger logger = LoggerFactory.getLogger(CommentRepositoryImpl.class);

	@SuppressWarnings("unchecked")
	public List<Comment> findAllComments(Paging paging) {
		int cPage = paging.getCurrentPage();
		int sType = paging.getSearchType();
		String sText = paging.getSearchText();
		int limit = paging.getLimit();

		// 검색 로직
		Criteria criteria = createEntityCriteria()
				.addOrder(Order.desc("commentId"))
				.setFirstResult((cPage - 1) * limit)
				.add(Restrictions.eq("delCheck", 0))
//				.add(Restrictions.eq("boardInComment", paging.getId()))
				.setMaxResults(limit).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);		
		
		if (paging.getSearchType() != 0 && sType == 2) {
			criteria.add(Restrictions.like("content", "%" + sText + "%"));
		} else if (paging.getSearchType() != 0 && sType == 3) {
			criteria.add(Restrictions.like("writer", "%" + sText + "%"));
		} else if (paging.getSearchType() != 0 && sType == 4) {
			criteria.add(Restrictions.like("content", "%" + sText + "%"));
		}

		logger.info(criteria.toString());
		List<Comment> comment = (List<Comment>)criteria.list();
		return comment;
	}

	@Override
	public int getCount(Paging paging) {
		String condition = "";
		condition = "WHERE COMMENT_BOARD_ID="+paging.getId();
		Query query = rawQuery("SELECT COUNT(*) FROM TB_COMMENT " + condition);
		return ((Number) query.uniqueResult()).intValue();
	}

	@Override
	public Comment findById(Long id) {
		Comment comment = getByLong(id);
		return comment;
	}

	@Override
	public void saveComment(Comment comment) {
		persist(comment);
	}

	@Override
	public void deleteCommentById(Long id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Comment comment = (Comment) crit.uniqueResult();
		delete(comment);
	}
}
