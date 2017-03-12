package com.shun.blog.service.user;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.model.user.UserAttempts;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface UserAttemptsService {
	
	void insert(UserAttempts userAttempts);

	UserAttempts selectById(Long id);
	
	UserAttempts selectByEmail(String email);
	
	List<UserAttempts> selectList(UserAttempts userAttempts);
	
	void update(UserAttempts userAttempts);

	Boolean delete(Long id);
}
