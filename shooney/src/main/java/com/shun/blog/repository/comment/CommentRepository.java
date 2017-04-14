package com.shun.blog.repository.comment;

import java.util.List;

import com.shun.blog.model.comment.Comment;

public interface CommentRepository {
	void saveComment(Comment comment);
	
	Comment findById(Long id) throws Exception;

	List<Comment> findAllComments(Comment comment) throws Exception;

	int getCount(Comment comment) throws Exception;

	void deleteCommentById(Long id);
}
