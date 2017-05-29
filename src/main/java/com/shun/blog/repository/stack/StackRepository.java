package com.shun.blog.repository.stack;


import com.shun.blog.model.stack.Stack;

import java.util.Set;

public interface StackRepository {
	void insert(Stack stack);
	
	Stack selectById(Long id) throws Exception;

	Stack selectByName(String name) throws Exception;

	Set<Stack> selectList(Stack stack) throws Exception;

	int getCount() throws Exception;

	void deleteById(Long id);

}
