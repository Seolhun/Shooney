package com.shun.blog.service.user;

import java.util.List;

import com.shun.blog.model.user.UserAttempts;

public interface UserAttemptsService {
	
	void insert(UserAttempts userAttempts);

	UserAttempts selectById(Long id);
	
	UserAttempts selectByEmail(String email);
	
	List<UserAttempts> selectList(UserAttempts userAttempts);
	
	void update(UserAttempts userAttempts);

	Boolean delete(Long id);
}
