package com.shun.blog.service.github;

import com.shun.blog.model.Github.Github;
import com.shun.blog.model.common.AjaxResult;

import java.util.List;

public interface GithubService {
	void insertGithub(Github github) throws Exception;

	Github selectGithubById(Long githubId) throws Exception;

	List<Github> findAllGithub(Github github) throws Exception;

	void updateGithub(Github github) throws Exception;

	AjaxResult deleteGithub(Github github) throws Exception;

	List<Github> findAllByAdmin(Github github) throws Exception;
}