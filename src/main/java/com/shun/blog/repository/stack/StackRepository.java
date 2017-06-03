package com.shun.blog.repository.stack;


import com.shun.blog.model.stack.Stack;

import java.util.List;


public interface StackRepository {
	void insert(Stack stack);
	
	Stack selectById(Long id) throws Exception;

	Stack selectByName(String name) throws Exception;

	List<Stack> selectListForAdmin(Stack stack) throws Exception;

	List<Stack> selectListForAdminWithError(Stack stack) throws Exception;

    List<Stack> selectList(Stack stack) throws Exception;

	int getCount() throws Exception;

	void deleteById(Long id);

}
