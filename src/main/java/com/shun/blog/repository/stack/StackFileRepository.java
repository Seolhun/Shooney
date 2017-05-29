package com.shun.blog.repository.stack;

import com.shun.blog.model.stack.Stack;
import com.shun.blog.model.stack.StackFile;

import java.util.List;

public interface StackFileRepository {
	void insert(StackFile stackFile);
	
	StackFile selectById(Long id) throws Exception;

	StackFile selectByName(String name) throws Exception;
	
	List<StackFile> selectList(Stack stack) throws Exception;

	int getCount() throws Exception;

	void deleteById(Long id);

}
