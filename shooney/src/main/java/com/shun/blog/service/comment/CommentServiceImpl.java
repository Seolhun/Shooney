package com.shun.blog.service.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.dao.comment.CommentRepository;
import com.shun.blog.model.comment.Comment;
import com.shun.blog.model.common.Paging;

@Service
@Transactional(transactionManager="txManager")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentDao;
	
	
	public Comment findById(Long id) {
		return commentDao.findById(id);
	}

	public void saveComment(Comment Comment) {
		commentDao.saveComment(Comment);
	}
	
	public void updateComment(Comment comment) {
		Comment entity = commentDao.findById(comment.getCommentId());
		//읽을시 쿠키 읽기
		if(entity != null){
			
		}
	}

	@Override
	public void deleteUserById(Long id) {
		commentDao.deleteCommentById(id);
	}
	
	@Override
	public List<Comment> findAllComments(Paging paging) {
		return commentDao.findAllComments(paging);
	}

	@Override
	public int getCount(Paging paging) {
		return commentDao.getCount(paging);
	}
}
