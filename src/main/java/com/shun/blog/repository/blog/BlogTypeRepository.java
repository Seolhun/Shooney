package com.shun.blog.repository.blog;

import com.shun.blog.model.blog.BlogType;
import java.util.List;

public interface BlogTypeRepository {
	void insert(BlogType blogType);
	
	BlogType selectById(Long id) throws Exception;
	
	List<BlogType> selectList() throws Exception;

	int getCount() throws Exception;

	void deleteById(Long id);

}
