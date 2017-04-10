package com.shun.blog.repository.comment;

import java.util.List;

import com.shun.blog.model.comment.Comment;
import com.shun.blog.model.common.Paging;

public interface CommentRepository {
	List<Comment> findAllComments(Paging paging) throws Exception;

	int getCount(Paging paging) throws Exception;

	Comment findById(Long id) throws Exception;

	void saveComment(Comment comment);

	void deleteCommentById(Long id);
}
