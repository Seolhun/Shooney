package com.shun.blog.controller.comment;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shun.blog.model.comment.Comment;
import com.shun.blog.model.common.Paging;
import com.shun.blog.service.comment.CommentService;
import com.shun.blog.service.common.CommonService;

@RestController
@RequestMapping(value = "/reply")
public class CommentRestController {
	private static final Logger LOG = LoggerFactory.getLogger(CommentRestController.class);
	
	private CommonService commonService;
	private CommentService commentService;;
	
	@Autowired
	public CommentRestController(CommentService commentService, CommonService commonService) {
		this.commonService=commonService;
		this.commentService=commentService;
	}
	
	/**
	 * 댓글 입력하기..
	 * 
	 * @param Comment comment
	 * @return -
	 * @throws Exception
	 */
	@RequestMapping(value = "/{entity}/insert", method = RequestMethod.POST, produces = "application/json")
	public Comment addBoardComment(@RequestBody Comment comment, @PathVariable String entity) throws Exception {
		Comment returnComment=new Comment();
		
		try {
			//유저가 로그인하지 않았을 경우. 페이지 이동시켜야함.
			comment.setCreatedBy(commonService.getAccessUserToModel().getNickname());
		} catch (NullPointerException e) {
			LOG.error("error : Fail to get login user information ");
			return returnComment;
		}
		
		//유효성체크.
		String content=comment.getContent();
		if(content==null || content.length()<1 || content.length()>300){
			return returnComment;
		} else if(comment.getBlogInComment().getBlogId()<1){
			return returnComment;
		}
		
		comment.setEntityName(entity);
		comment.setContent(comment.getContent().replaceAll("\n", "<br/>"));
		commentService.saveComment(comment);
		
		returnComment=commentService.getLatest(comment);
		return returnComment;
	}
	
	/**
	 * 댓글 리스트 가져오기.
	 * 
	 * @param Comment comment
	 * @return List<Comment>
	 * @throws Exception
	 */
	@RequestMapping(value = "/{entity}/list/more", method = RequestMethod.POST)
	public List<Comment> getCommentsList(@RequestBody Comment comment, @PathVariable String entity, HttpServletRequest request, ModelMap model) throws Exception {
		//댓글위한 해당 블로그 값 넣기.
		Paging paging = commonService.beforeGetPaging(request);
		comment.setPaging(paging);
		
		// 전체 댓글 갯수 확인.
		int totalCount = commentService.getCount(comment);
		comment.getPaging().setTotalCount(totalCount);
		commonService.setAndValidationPaging(paging);
		
		List<Comment> comments=commentService.findAllComments(comment);
		LOG.info("return : {}",comments.toString());
		return comments;
	}
}
