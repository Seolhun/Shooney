package com.shun.blog.service.comment;

import java.util.List;

import com.shun.blog.model.comment.Comment;

public interface CommentService {
	Comment findById(Long id) throws Exception;

	void saveComment(Comment comment) throws Exception;
	
	List<Comment> findAllComments(Comment comment) throws Exception;
	
	int getCount(Comment comment) throws Exception;

	void updateComment(Comment comment) throws Exception;

	void deleteUserById(Long id);
}