package com.shun.blog.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shun.blog.dao.user.UserProfileRepository;
import com.shun.blog.model.user.UserProfile;

@Service("userProfileService")
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	UserProfileRepository dao;

	@Override
	public UserProfile selectById(int id) {
		return dao.selectById(id);
	}

	@Override
	public UserProfile selectByType(String type) {
		return dao.selectByType(type);
	}

	@Override
	public List<UserProfile> selectList() {
		return dao.selectList();
	}
}
