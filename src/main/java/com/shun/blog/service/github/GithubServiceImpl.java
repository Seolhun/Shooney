package com.shun.blog.service.github;

import com.shun.blog.model.Github.Github;
import com.shun.blog.model.common.AjaxResult;
import com.shun.blog.repository.blog.BlogRepository;
import com.shun.blog.repository.comment.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager="txManager")
public class GithubServiceImpl implements GithubService {

	private CommentRepository commentRepository;
	private BlogRepository blogRepository;
	
	@Autowired
	public GithubServiceImpl(CommentRepository commentRepository, BlogRepository blogRepository) {
		this.commentRepository=commentRepository;
		this.blogRepository=blogRepository;
	}

	@Override
	public void insertGithub(Github github) throws Exception {

	}

	@Override
	public Github selectGithubById(Long githubId) throws Exception {
		return null;
	}

	@Override
	public List<Github> findAllGithub(Github github) throws Exception {
		return null;
	}

	@Override
	public void updateGithub(Github github) throws Exception {

	}

	@Override
	public AjaxResult deleteGithub(Github github) throws Exception {
		return null;
	}

	@Override
	public List<Github> findAllByAdmin(Github github) throws Exception {
		return null;
	}
}
