package com.shun.blog.repository.user;

import java.util.List;

import com.shun.blog.model.user.UserProfile;

public interface UserProfileRepository {

	List<UserProfile> selectList();

	UserProfile selectByType(String type);

	UserProfile selectById(int id);
}
