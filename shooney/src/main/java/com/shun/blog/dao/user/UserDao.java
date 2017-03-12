package com.shun.blog.dao.user;

import java.util.List;

import com.shun.blog.common.model.Paging;
import com.shun.blog.model.user.User;

public interface UserDao {

	User findById(int id);
	
	User findByEmail(String email);
	
	User findByNickname(String nickname);
	
	void save(User user);
	
	void deleteByEmail(String email);
	
	List<User> findAllUsers(Paging paging);
	
	int getCount(Paging paging);

}

