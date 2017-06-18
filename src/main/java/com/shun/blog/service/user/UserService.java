package com.shun.blog.service.user;

import java.util.List;

import com.shun.blog.model.common.Paging;
import com.shun.blog.model.user.User;

public interface UserService {
	void insert(User user);
	
	User selectById(Long id);

	User selectByEmail(String email);
	
	User selectByNickname(String nickName);

	void update(User user);
	
	void deleteById(Long id);
	
	void deleteByEmail(String email);
	
	List<User> selectList(Paging paging);
	
	int getCount(Paging paging);

	boolean isUserEmailUnique(String email);

	boolean isUserNicknameUnique(String nickname);

}