package com.shun.blog.repository.comment;

import java.util.List;

import com.shun.blog.model.comment.Comment;
import com.shun.blog.model.common.Paging;

public interface CommentRepository {
	List<Comment> findAllComments(Paging paging);

	int getCount(Paging paging);

	Comment findById(Long id);

	void saveComment(Comment comment);

	void deleteCommentById(Long id);
}
