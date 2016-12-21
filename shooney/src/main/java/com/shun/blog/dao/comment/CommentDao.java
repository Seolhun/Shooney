package com.shun.blog.dao.comment;

import java.util.List;

import com.shun.blog.model.comment.Comment;
import com.shun.blog.model.common.Paging;

public interface CommentDao {
	List<Comment> findAllComments(Paging paging);

	int getCount(Paging paging);

	Comment findById(int id);

	void saveComment(Comment comment);

	void deleteCommentById(int id);
}
