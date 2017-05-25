package com.shun.blog.repository.blog;

import com.shun.blog.model.blog.Blog;
import com.shun.blog.model.common.Paging;
import com.shun.blog.repository.AbstractRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlogRepositoryImpl extends AbstractRepository<Long, Blog> implements BlogRepository {
	static final Logger LOG = LoggerFactory.getLogger(BlogRepositoryImpl.class);

	@Override
	public void insert(Blog blog) {
		persist(blog);
	}
	
	@Override
	public Blog selectById(Long id) throws Exception{
		return getByLong(id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Blog> selectList(Paging paging) throws Exception {
		int cPage = paging.getCurrentPage();
		int sType = paging.getSearchType();
		int sDate = paging.getSearchDate();
		String sText = paging.getSearchText();
		int limit = paging.getLimit();
		String blogType = paging.getBoardType();
		
		// 검색 로직
		Criteria criteria = createEntityCriteria()
				.addOrder(Order.desc("id"))
				.add(Restrictions.eq("delFlag", "N"))
				.setFirstResult((cPage - 1) * limit)
				.setMaxResults(limit).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		searchBlog(sText, sType, blogType, sDate, paging, criteria);

		return (List<Blog>) criteria.list();
	}
	
	@Override
	public int getCount(Paging paging) throws Exception {
		int sType = paging.getSearchType();
		int sDate = paging.getSearchDate();
		String sText = paging.getSearchText();
		String blogType = paging.getBoardType();

		// 검색 로직
		Criteria criteria = createEntityCriteria()
				.add(Restrictions.eq("delFlag", "N"));

		searchBlog(sText, sType, blogType, sDate, paging, criteria);
		return ((Number)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	@Override
	public void deleteById(Long id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Blog blog = (Blog) crit.uniqueResult();
		delete(blog);
	}

	private void searchBlog(String sText, int sType, String blogType, int sDate, Paging paging, Criteria criteria ){
		if (blogType != null) {
			criteria.add(Restrictions.eq("blogType", blogType));
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
	}
}
