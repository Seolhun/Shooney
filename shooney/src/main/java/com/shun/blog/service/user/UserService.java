package com.shun.blog.service.user;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.model.common.Paging;
import com.shun.blog.model.user.User;

@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface UserService {

	User findById(int id);

	User findByEmail(String email);
	
	User findByNickname(String nickName);

	void saveUser(User user);

	void updateUser(User user);
	
	void deleteUserByEmail(String email);

	List<User> findAllUsers(Paging paging);
	
	int getCount(Paging paging);

	boolean isUserEmailUnique(String email);

	boolean isUserNicknameUnique(String nickname);

}