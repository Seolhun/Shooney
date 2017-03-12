package com.shun.blog.dao.user;

import java.util.List;

import com.shun.blog.model.user.UserProfile;

public interface UserProfileRepository {

	List<UserProfile> findAll();

	UserProfile findByType(String type);

	UserProfile findById(int id);
}
