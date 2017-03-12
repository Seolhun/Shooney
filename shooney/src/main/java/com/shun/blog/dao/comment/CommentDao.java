package com.shun.blog.dao.comment;

import java.util.List;

import com.shun.blog.common.model.Paging;
import com.shun.blog.model.comment.Comment;

public interface CommentDao {
	List<Comment> findAllComments(Paging paging);

	int getCount(Paging paging);

	Comment findById(Long id);

	void saveComment(Comment comment);

	void deleteCommentById(Long id);
}
