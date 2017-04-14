package com.shun.blog.service.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.model.comment.Comment;
import com.shun.blog.repository.comment.CommentRepository;

@Service
@Transactional(transactionManager="txManager")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public Comment findById(Long id) throws Exception{
		return commentRepository.findById(id);
	}

	@Override
	public void saveComment(Comment Comment) {
		commentRepository.saveComment(Comment);
	}
	
	@Override
	public List<Comment> findAllComments(Comment comment) throws Exception{
		return commentRepository.findAllComments(comment);
	}

	@Override
	public int getCount(Comment comment) throws Exception{
		return commentRepository.getCount(comment);
	}
	
	@Override
	public void updateComment(Comment comment) throws Exception{
		Comment dbComment = commentRepository.findById(comment.getCommentId());
		//읽을시 쿠키 읽기
		if(dbComment != null){
			dbComment=comment;
		}
	}

	@Override
	public void deleteUserById(Long id) {
		commentRepository.deleteCommentById(id);
	}
}
