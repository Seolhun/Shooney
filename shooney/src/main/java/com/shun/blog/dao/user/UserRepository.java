package com.shun.blog.dao.user;

import java.util.List;

import com.shun.blog.model.common.Paging;
import com.shun.blog.model.user.User;

public interface UserRepository {
	void insert(User user);

	User selectById(Long id);
	
	User selectByEmail(String email);
	
	User selectByNickname(String nickname);
	
	List<User> selectList(Paging paging);
	
	int getCount(Paging paging);
	
	void deleteById(Long id);
	
	void deleteByEmail(String email);
}

