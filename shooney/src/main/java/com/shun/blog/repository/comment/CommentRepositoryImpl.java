package com.shun.blog.repository.comment;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.shun.blog.model.blog.Blog;
import com.shun.blog.model.comment.Comment;
import com.shun.blog.model.common.Paging;
import com.shun.blog.repository.AbstractRepository;

@Repository
public class CommentRepositoryImpl extends AbstractRepository<Long, Comment> implements CommentRepository {
	static final Logger LOG = LoggerFactory.getLogger(CommentRepositoryImpl.class);
	
	@Override
	public Comment findById(Long id) {
		Comment comment = getByLong(id);
		return comment;
	}

	@Override
	public void saveComment(Comment comment) {
		persist(comment);
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findAllComments(Paging paging) {
		int cPage = paging.getCurrentPage();
		int sType = paging.getSearchType();
		int sDate = paging.getSearchDate();
		String sText = paging.getSearchText();
		int limit = paging.getLimit();
		
		Blog blog=new Blog();
		blog.setBlogId(paging.getId());

		LOG.info("param : comments = {}",paging.toString());
		
		// 검색 로직
		Criteria criteria = createEntityCriteria()
				.addOrder(Order.desc("commentId"))
				.setFirstResult((cPage - 1) * limit)
				.add(Restrictions.eq("delCheck", 0))
				.add(Restrictions.eq("blogInComment", blog))
				.setMaxResults(limit).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);		
		
		if(sText!=""){
			if (paging.getSearchType() != 0 && sType == 2) {
				criteria.add(Restrictions.like("content", "%" + sText + "%"));
			} else if (paging.getSearchType() != 0 && sType == 3) {
				criteria.add(Restrictions.like("writer", "%" + sText + "%"));
			} else if (paging.getSearchType() != 0 && sType == 4) {
				criteria.add(Restrictions.like("content", "%" + sText + "%"));
			}
		}
		
		List<Comment> comment = (List<Comment>)criteria.list();
		return comment;
	}
	
	@Override
	public int getCount(Paging paging) throws Exception {
		int sType = paging.getSearchType();
		int sDate = paging.getSearchDate();
		String sText = paging.getSearchText();
		
		Blog blog=new Blog();
		blog.setBlogId(paging.getId());
		
		// 검색 로직
		Criteria criteria = createEntityCriteria()
				.add(Restrictions.eq("delCheck", 0))
				.add(Restrictions.eq("blogInComment", blog));
		
				
		//날짜 이용.
		if(sDate != 0){
			
		}

		//검색어 이용.
		if(sText!=""){
			if (paging.getSearchType() != 0 && sType == 2) {
				criteria.add(Restrictions.like("content", "%" + sText + "%"));
			} else if (paging.getSearchType() != 0 && sType == 3) {
				criteria.add(Restrictions.like("writer", "%" + sText + "%"));
			} else if (paging.getSearchType() != 0 && sType == 4) {
				criteria.add(Restrictions.like("content", "%" + sText + "%"));
			}
		}
		
		Integer totalResult = ((Number)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		return totalResult;
	}

	@Override
	public void deleteCommentById(Long id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Comment comment = (Comment) crit.uniqueResult();
		delete(comment);
	}
}
