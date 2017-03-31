package com.shun.blog.dao.blog;

import java.util.List;

import com.shun.blog.model.blog.Blog;
import com.shun.blog.model.common.Paging;

public interface BlogRepository {
	void insert(Blog Board);
	
	Blog selectById(Long id) throws Exception;
	
	List<Blog> selectList(Paging paging) throws Exception;

	int getCount(Paging paging) throws Exception;

	void deleteById(Long id);

}
