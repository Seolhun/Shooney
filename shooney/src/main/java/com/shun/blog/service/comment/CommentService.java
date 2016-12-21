package com.shun.blog.service.comment;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.model.comment.Comment;
import com.shun.blog.model.common.Paging;

@Transactional(transactionManager="txManager", noRollbackFor={NullPointerException.class})
public interface CommentService {

	@Transactional(transactionManager="txManager", noRollbackFor={NullPointerException.class}, readOnly=true)
	Comment findById(int id);

	void saveComment(Comment comment);

	void updateComment(Comment comment);

	void deleteUserById(int id);

	List<Comment> findAllComments(Paging paging);
	
	int getCount(Paging paging);
}