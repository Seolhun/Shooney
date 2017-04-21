package com.shun.blog.repository.comment;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.shun.blog.model.blog.Blog;
import com.shun.blog.model.comment.Comment;
import com.shun.blog.repository.AbstractRepository;

@Repository
public class CommentRepositoryImpl extends AbstractRepository<Long, Comment> implements CommentRepository {
	static final Logger LOG = LoggerFactory.getLogger(CommentRepositoryImpl.class);
	
	@Override
	public void saveComment(Comment comment) {
		persist(comment);
	}
	
	@Override
	public Comment findById(Long id) {
		Comment comment = getByLong(id);
		return comment;
	}
	
	@Override
	public Comment getLatest(Comment Comment) throws Exception {
		Criteria criteria = createEntityCriteria()
				.addOrder(Order.desc("commentId"))
				.add(Restrictions.eq("delCheck", 0))
				.add(Restrictions.eq("delCheck", 0))
				.add(Restrictions.eq("createdBy", Comment.getCreatedBy()))
				.add(Restrictions.eq("blogInComment", Comment.getBlogInComment()))
				.setMaxResults(1)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		Comment comment = (Comment)criteria.uniqueResult();
		return comment;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> selectListByBlog(Blog blog) {
		Criteria criteria = createEntityCriteria()
				.addOrder(Order.desc("commentId"))
				.add(Restrictions.eq("delCheck", 0))
				.add(Restrictions.eq("blogInComment", blog))
				.setFirstResult(0)
				.setMaxResults(10)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Comment> comments =criteria.list();
		return comments;
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> findAllComments(Comment comment) {
		int cPage = comment.getPaging().getCurrentPage();
		int sType = comment.getPaging().getSearchType();
		int sDate = comment.getPaging().getSearchDate();
		String sText = comment.getPaging().getSearchText();
		int limit = comment.getPaging().getLimit();
		
		// 검색 로직
		Criteria criteria = createEntityCriteria()
				.addOrder(Order.desc("commentId"))
				.add(Restrictions.eq("delCheck", 0))
				.add(Restrictions.eq("blogInComment", comment.getBlogInComment()))
				//-1을 통해 0부터 시작되어야 하지만, 기존 블로그 Detail에서 10개를 가져오기 떄문에, -1를 없앤다.
				.setFirstResult((cPage) * limit)
				.setMaxResults(limit)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);		
		
		commentSearch(criteria, sType, sText);
		
		List<Comment> comments = criteria.list();
		return comments;
	}
	
	@Override
	public int getCount(Comment comment) throws Exception {
		int sType = comment.getPaging().getSearchType();
		int sDate = comment.getPaging().getSearchDate();
		String sText = comment.getPaging().getSearchText();
		
		// 검색 로직
		Criteria criteria = createEntityCriteria()
				.addOrder(Order.desc("commentId"))
				.add(Restrictions.eq("delCheck", 0))
				.add(Restrictions.eq("blogInComment", comment.getBlogInComment()));
				
		//날짜 이용.
		if(sDate != 0){
			
		}

		//검색어 이용.
		commentSearch(criteria, sType, sText);
		
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
	
	private void commentSearch(Criteria criteria, int sType, String sText){
		if(sText!=""){
			if (sType != 0 && sType == 2) {
				criteria.add(Restrictions.like("content", "%" + sText + "%"));
			} else if (sType != 0 && sType == 3) {
				criteria.add(Restrictions.like("createdBy", "%" + sText + "%"));
			} else if (sType != 0 && sType == 4) {
				criteria.add(Restrictions.like("content", "%" + sText + "%"))
					.add(Restrictions.like("createdBy", "%" + sText + "%"));;
			}
		}
	}
}
