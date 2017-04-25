package com.shun.blog.service.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.model.blog.Blog;
import com.shun.blog.model.comment.Comment;
import com.shun.blog.repository.blog.BlogRepository;
import com.shun.blog.repository.comment.CommentRepository;

@Service
@Transactional(transactionManager="txManager")
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;
	private BlogRepository blogRepository;
	
	@Autowired
	public CommentServiceImpl(CommentRepository commentRepository, BlogRepository blogRepository) {
		this.commentRepository=commentRepository;
		this.blogRepository=blogRepository;
	}
	
	@Override
	public Comment findById(Long id) throws Exception{
		return commentRepository.findById(id);
	}
	
	@Override
	public Comment getLatest(Comment Comment) throws Exception{
		return commentRepository.getLatest(Comment);
	}
	
	@Override
	public List<Comment> selectListByBlog(Blog blog) throws Exception {
		return commentRepository.selectListByBlog(blog);
	}
	
	@Override
	public void saveComment(Comment comment) throws Exception {
		commentRepository.saveComment(comment);
		
		Blog dbBlog= blogRepository.selectById(comment.getBlogInComment().getBlogId());
		//읽을시 쿠키 읽기
		if(dbBlog != null){
			int depth=dbBlog.getDepth();
			dbBlog.setDepth(depth+1);
		}
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
	public Comment updateComment(Comment comment) throws Exception{
		Comment dbComment = commentRepository.findById(comment.getCommentId());
		String createdBy=dbComment.getCreatedBy();
		String modifyBy=comment.getModifiedBy();
		if(createdBy.equals(modifyBy)){
			if(comment.getDelFlag().equals("Y")){
				dbComment.setDelFlag(comment.getDelFlag());
			} else if(comment.getContent()!=null){
				dbComment.setContent(comment.getContent());
			}
			dbComment.setModifiedBy(comment.getModifiedBy());
		}
		return dbComment;
	}

	@Override
	public void deleteUserById(Long id) {
		commentRepository.deleteCommentById(id);
	}
}
