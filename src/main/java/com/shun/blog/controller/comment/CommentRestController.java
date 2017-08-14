package com.shun.blog.controller.comment;

import com.shun.blog.model.comment.Comment;
import com.shun.blog.model.common.AjaxResult;
import com.shun.blog.service.comment.CommentService;
import com.shun.blog.service.common.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * param Comment comment
	 * return -
	 * throws Exception
	 */
	@RequestMapping(value = "/{entity}/insert", method = RequestMethod.POST, produces = "application/json")
	public Comment addBoardComment(@RequestBody Comment comment, @PathVariable String entity, Authentication auth) throws Exception {
		String nickName="";
		try {
			//유저가 로그인하지 않았을 경우. 페이지 이동시켜야함.
			nickName=auth.getName();
		} catch (NullPointerException e) {
			LOG.error("error : Fail to get login user information ");
			return comment;
		}
		
		//유효성체크.
		String content=comment.getContent();
		if(content==null || content.length()<1 || content.length()>300){
			return comment;
		} else if(comment.getBlogInComment().getId()<1){
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
	 * param Comment comment
	 * return List<Comment>
	 * throws Exception
	 */
	@RequestMapping(value = "/{entity}/more", method = RequestMethod.POST, produces = "application/json")
	public Map<String, Object> getCommentsList(@RequestBody Comment comment, Authentication auth) throws Exception {
		Map<String, Object> returnMap=new HashMap<>();
		String nickName="";
		try {
			//유저가 로그인하지 않았을 경우. 페이지 이동시켜야함.
			nickName=auth.getName();
			returnMap.put("nickName", nickName);
		} catch (NullPointerException e) {
			LOG.error("error : Fail to get login user information ");
		}
		
		//댓글위한 해당 블로그 값 넣기.
		comment.setPaging(commonService.beforePostPaging(comment.getPaging())); 
		
		// 전체 댓글 갯수 확인.
		int totalCount = commentService.getCount(comment);
		comment.getPaging().setTotalCount(totalCount);
		commonService.setAndValidationPaging(comment.getPaging());
		int maxCount=comment.getPaging().getMaxCount();
		
		List<Comment> comments;
		if(totalCount>maxCount){
			comments=commentService.findAllComments(comment);	
			returnMap.put("comments", comments);
		}
		return returnMap;
	}
	
	@RequestMapping(value = "/{entity}/modify/{commentId}", method = RequestMethod.POST, produces = "application/json")
	public Comment modifyBoardComment(@RequestBody Comment comment, AjaxResult ajaxResult, @PathVariable String entity, @PathVariable Long commentId, Authentication auth) throws Exception{
		String nickName="";
		try {
			//유저가 로그인하지 않았을 경우. 페이지 이동시켜야함.
			nickName=auth.getName();
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
	public AjaxResult deleteBoardComment(@RequestBody Comment comment, AjaxResult ajaxResult, @PathVariable Long commentId, Authentication auth) throws Exception{
		String nickName="";
		try {
			//유저가 로그인하지 않았을 경우. 페이지 이동시켜야함.
			nickName=auth.getName();
			comment.setCreatedBy(nickName);
		} catch (NullPointerException e) {
			LOG.error("error : Fail to get login user information ");
			ajaxResult.setResult("invalid");
		}
		
		comment.setDeletedFlag(true);
		comment.setCommentId(commentId);
		comment.setModifiedBy(nickName);
		commentService.updateComment(comment);
		
		ajaxResult.setResult("success");
		return ajaxResult;
	}
}
