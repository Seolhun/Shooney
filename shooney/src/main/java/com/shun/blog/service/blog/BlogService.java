package com.shun.blog.service.blog;

import java.util.List;

import com.shun.blog.model.blog.Blog;
import com.shun.blog.model.common.Paging;

public interface BlogService {

	Blog selectById(Long id) throws Exception;

	void insert(Blog blog);

	void update(Blog blog) throws Exception;

	void deleteById(Long id);

	List<Blog> selectList(Paging paging) throws Exception;
	
	int getCount(Paging paging) throws Exception;
}