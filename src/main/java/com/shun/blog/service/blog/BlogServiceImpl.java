package com.shun.blog.service.blog;

import com.shun.blog.model.blog.Blog;
import com.shun.blog.model.blog.BlogType;
import com.shun.blog.model.comment.Comment;
import com.shun.blog.model.common.Paging;
import com.shun.blog.model.file.FileData;
import com.shun.blog.repository.blog.BlogRepository;
import com.shun.blog.repository.comment.CommentRepository;
import com.shun.blog.repository.file.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager="txManager")
public class BlogServiceImpl implements BlogService {
	static final Logger LOG = LoggerFactory.getLogger(BlogServiceImpl.class);

	private BlogRepository blogRepository;
	private BlogTypeService blogTypeService;
	private FileRepository fileRepository;
	private CommentRepository commentRepository;
	
	@Autowired
	public BlogServiceImpl(BlogRepository blogRepository, BlogTypeService blogTypeService, FileRepository fileRepository, CommentRepository commentRepository) {
		this.blogRepository=blogRepository;
		this.blogTypeService=blogTypeService;
		this.fileRepository=fileRepository;
		this.commentRepository=commentRepository;
	}
	
	@Override
	public void insert(Blog blog) throws Exception {
		LOG.info("param : insert {}", blog.toString());
		blogRepository.insert(blog);

		BlogType blogType=new BlogType(blog.getBlogType());
		blogTypeService.update(blogType, 1);
	}
	
	@Override
	public Blog selectById(Long id) throws Exception{
		LOG.info("param : selectById {}", id);
		Blog blog=blogRepository.selectById(id);
		List<FileData> fileList=fileRepository.selectListByBlog(blog);
		List<Comment> commentList=commentRepository.selectListByBlog(blog);
		if(fileList!=null){
			blog.setFileDataList(fileList);	
		} 
		if(commentList!=null){
			blog.setCommentList(commentList);	
		}
		return blog;
	}
	
	@Override
	public int getCount(Paging paging) throws Exception{
		LOG.info("param : selectList {}", paging.toString());
		return blogRepository.getCount(paging);
	}
	
	@Override
	public List<Blog> selectList(Paging paging) throws Exception{
		LOG.info("param : selectList {}", paging.toString());
		return blogRepository.selectList(paging);
	}

	@Override
	public void update(Blog blog) throws Exception{
		LOG.info("param : update {}", blog.toString());
		Blog dbBlog = blogRepository.selectById(blog.getId());
		//읽을시 쿠키 읽기
		if(blog.getHits()==1){
			dbBlog.setHits(dbBlog.getHits()+1);
		} else if(dbBlog != null){
			dbBlog.setTitle(blog.getTitle());
			dbBlog.setContent(blog.getContent());
			dbBlog.setCreatedBy(blog.getCreatedBy());
			dbBlog.setModifiedBy(blog.getModifiedBy());
			dbBlog.setDepth(blog.getDepth());
			dbBlog.setBlogType(blog.getBlogType());
		}

		if(blog.getDelFlag() != null && blog.getDelFlag().equals("Y")){
			dbBlog.setDelFlag("Y");
		}
	}

	@Override
	public void deleteById(Long id) throws Exception {
		LOG.info("param : deleteById {}", id);
		Blog blog=null;
		blog=blogRepository.selectById(id);
		blogRepository.deleteById(id);

        BlogType blogType=null;
        blogType.setName(blog.getBlogType());
        blogTypeService.update(blogType, -1);
	}
}
