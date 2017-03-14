package com.shun.blog.dao.user;

import java.util.List;

import com.shun.blog.model.common.Paging;
import com.shun.blog.model.user.User;

public interface UserRepository {

	User findById(Long id);
	
	User findByEmail(String email);
	
	User findByNickname(String nickname);
	
	void save(User user);
	
	void deleteByEmail(String email);
	
	List<User> findAllUsers(Paging paging);
	
	int getCount(Paging paging);

}

