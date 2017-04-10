package com.shun.blog.service.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.repository.comment.CommentRepository;
import com.shun.blog.model.comment.Comment;
import com.shun.blog.model.common.Paging;

@Service
@Transactional(transactionManager="txManager")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	
	public Comment findById(Long id) throws Exception{
		return commentRepository.findById(id);
	}

	public void saveComment(Comment Comment) {
		commentRepository.saveComment(Comment);
	}
	
	@Override
	public List<Comment> findAllComments(Paging paging) throws Exception{
		return commentRepository.findAllComments(paging);
	}

	@Override
	public int getCount(Paging paging) throws Exception{
		return commentRepository.getCount(paging);
	}
	
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
