package com.shun.blog.service.user;

import java.util.List;

import com.shun.blog.model.user.UserProfile;
public interface UserProfileService {

	UserProfile selectById(int id);

	UserProfile selectByType(String type);

	List<UserProfile> selectList();
}
