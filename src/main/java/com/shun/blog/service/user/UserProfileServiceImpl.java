package com.shun.blog.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.repository.user.UserProfileRepository;
import com.shun.blog.model.user.UserProfile;

@Service
@Transactional(transactionManager="txManager")
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	UserProfileRepository userProfileRepository;

	@Override
	public UserProfile selectById(int id) {
		return userProfileRepository.selectById(id);
	}

	@Override
	public UserProfile selectByType(String type) {
		return userProfileRepository.selectByType(type);
	}

	@Override
	public List<UserProfile> selectList() {
		return userProfileRepository.selectList();
	}
}
