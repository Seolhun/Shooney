package com.shun.blog.repository.user;

import java.util.List;

import com.shun.blog.model.user.UserAttempts;


public interface UserAttemptsRepository {
	
	void insert(UserAttempts userAttempts);
	
	UserAttempts selectById(Long id);
	
	UserAttempts selectByEmail(String email);
	
	List<UserAttempts> selectList(UserAttempts userAttempts);
	
	boolean delete(Long id);
}
