package com.shun.blog.service.blog;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.repository.blog.BlogRepository;
import com.shun.blog.model.blog.Blog;
import com.shun.blog.model.common.Paging;

@Service
@Transactional(transactionManager="txManager")
public class BlogServiceImpl implements BlogService {
	static final Logger LOG = LoggerFactory.getLogger(BlogServiceImpl.class);

	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	public BlogServiceImpl(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}
	
	@Override
	public Blog selectById(Long id) throws Exception{
		LOG.info("return : selectById : {}",id);
		Blog blog=blogRepository.selectById(id);
		return blog;
	}

	@Override
	public void insert(Blog blog) {
		LOG.info("param : insert : {}",blog.toString());
		blogRepository.insert(blog);
	}
	
	@Override
	public void update(Blog blog) throws Exception{
		LOG.info("param : update : {}",blog.toString());
		Blog entity = blogRepository.selectById(blog.getBlogId());
		//읽을시 쿠키 읽기
		if(blog.getHits()==1){
			entity.setHits(entity.getHits()+1);
		} else if(entity != null){
			entity.setTitle(blog.getTitle());
			entity.setContent(blog.getContent());
			entity.setCreatedBy(blog.getCreatedBy());
			entity.setModifiedBy(blog.getModifiedBy());
			entity.setDepth(blog.getDepth());
			entity.setEntityName(blog.getEntityName());
			entity.setPortfolioType(blog.getPortfolioType());
		}
	}

	@Override
	public void deleteById(Long id) {
		LOG.info("param : deleteById : {}",id);
		blogRepository.deleteById(id);
	}
	
	@Override
	public List<Blog> selectList(Paging paging) throws Exception{
		LOG.info("param : selectList : {}",paging.toString());
		return blogRepository.selectList(paging);
	}

	@Override
	public int getCount(Paging paging) throws Exception{
		LOG.info("param : getCount : {}",paging.toString());
		return blogRepository.getCount(paging);
	}
}
