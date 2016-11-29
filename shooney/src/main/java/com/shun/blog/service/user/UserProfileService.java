package com.shun.blog.service.user;

import java.util.List;

import com.shun.blog.model.user.UserProfile;

public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);

	List<UserProfile> findAll();
}
