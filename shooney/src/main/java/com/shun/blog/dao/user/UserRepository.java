package com.shun.blog.dao.user;

import java.util.List;

import com.shun.blog.model.common.Paging;
import com.shun.blog.model.user.User;

public interface UserRepository {

	User selectById(Long id);
	
	User selectByEmail(String email);
	
	User selectByNickname(String nickname);
	
	void insert(User user);
	
	void deleteByEmail(String email);
	
	List<User> selectList(Paging paging);
	
	int getCount(Paging paging);

}

