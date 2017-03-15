package com.shun.blog.dao.user;

import java.util.List;

import com.shun.blog.model.user.UserProfile;

public interface UserProfileRepository {

	List<UserProfile> selectList();

	UserProfile selectByType(String type);

	UserProfile selectById(int id);
}
