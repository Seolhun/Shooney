package com.shun.blog.controller.comment;

import java.security.Principal;
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
import com.shun.blog.model.common.AjaxResult;
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
		String nickName="";
		try {
			//유저가 로그인하지 않았을 경우. 페이지 이동시켜야함.
			nickName=commonService.getAccessUserToModel().getNickname();
		} catch (NullPointerException e) {
			LOG.error("error : Fail to get login user information ");
			return comment;
		}
		
		//유효성체크.
		String content=comment.getContent();
		if(content==null || content.length()<1 || content.length()>300){
			return comment;
		} else if(comment.getBlogInComment().getBlogId()<1){
			return comment;
		}
		
		comment.setCreatedBy(nickName);
		comment.setEntityName(entity);
		comment.setContent(comment.getContent().replaceAll("\n", "<br/>"));
		commentService.saveComment(comment);
		
		comment=commentService.getLatest(comment);
		return comment;
	}
	
	/**
	 * 댓글 리스트 가져오기.
	 * 
	 * @param Comment comment
	 * @return List<Comment>
	 * @throws Exception
	 */
	@RequestMapping(value = "/{entity}/list/more", method = RequestMethod.POST, produces = "application/json")
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
	
	@RequestMapping(value = "/{entity}/modify/{commentId}", method = RequestMethod.POST, produces = "application/json")
	public Comment modifyBoardComment(@RequestBody Comment comment, AjaxResult ajaxResult, @PathVariable String entity, @PathVariable Long commentId, HttpServletRequest reqeust, Principal principal) throws Exception{
		String nickName="";
		try {
			//유저가 로그인하지 않았을 경우. 페이지 이동시켜야함.
			nickName=commonService.getAccessUserToModel().getNickname();
		} catch (NullPointerException e) {
			LOG.error("error : Fail to get login user information ");
			ajaxResult.setResult("invalid");
		}
		
		comment.setCommentId(commentId);
		comment.setEntityName(entity);
		comment.setModifiedBy(nickName);
		comment.setContent(comment.getContent().replaceAll("\n", "<br/>"));
		comment=commentService.updateComment(comment);
		
		return comment;
	}
	
	@RequestMapping(value = "/{entity}/delete/{commentId}", method = RequestMethod.POST, produces = "application/json")
	public AjaxResult deleteBoardComment(@RequestBody Comment comment, AjaxResult ajaxResult, @PathVariable String entity, @PathVariable Long commentId, HttpServletRequest reqeust, Principal principal) throws Exception{
		String nickName="";
		try {
			//유저가 로그인하지 않았을 경우. 페이지 이동시켜야함.
			nickName=commonService.getAccessUserToModel().getNickname();
			comment.setCreatedBy(nickName);
		} catch (NullPointerException e) {
			LOG.error("error : Fail to get login user information ");
			ajaxResult.setResult("invalid");
		}
		
		comment.setDelFlag("Y");
		comment.setCommentId(commentId);
		comment.setModifiedBy(nickName);
		commentService.updateComment(comment);
		
		ajaxResult.setResult("success");
		return ajaxResult;
	}
}
