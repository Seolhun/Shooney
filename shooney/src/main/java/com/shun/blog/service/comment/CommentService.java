package com.shun.blog.service.comment;

import java.util.List;

import com.shun.blog.model.comment.Comment;
import com.shun.blog.model.common.Paging;

public interface CommentService {

	Comment findById(Long id);

	void saveComment(Comment comment);

	void updateComment(Comment comment);

	void deleteUserById(Long id);

	List<Comment> findAllComments(Paging paging);
	
	int getCount(Paging paging);
}