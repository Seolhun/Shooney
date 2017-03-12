package com.shun.blog.controller.comment;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shun.blog.common.model.Paging;
import com.shun.blog.common.service.CommonService;
import com.shun.blog.model.comment.Comment;
import com.shun.blog.model.user.User;
import com.shun.blog.service.comment.CommentService;
import com.shun.blog.service.user.UserService;

@Controller
public class CommentController {
	@Autowired
	CommentService cService;

	@Autowired
	UserService uService;

	@Autowired
	CommonService commonService;

	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@RequestMapping(value = "/reply/board/add", method = {RequestMethod.POST}, produces = "application/json; charset=utf8")
	public @ResponseBody String addBoardComment(@RequestBody Comment comment) {
		try {
//			comment.setWriter(initializeUser().getNickname());
			String content=comment.getContent();
			if(content==null || content.length()<1){
				return "false";
			}
		} catch (NullPointerException e) {
			logger.error("Fail to get login user information ");
		}
		comment.setContent(comment.getContent().replaceAll("\n", "<br/>"));
		cService.saveComment(comment);
		return "true";
	}
	
	@RequestMapping(value = "/reply/board/delete/{id}", method = {RequestMethod.GET})
	public @ResponseBody String deleteBoardComment(HttpServletRequest reqeust, Comment comment, @PathVariable Long id, Principal principal) {
		String writer=reqeust.getParameter("writer");
		String accessUser=initializeUser(principal).getEmail();
		if(!accessUser.equals(writer)){
			return "false";
		}
		
//		comment.setDelCheck(1);
//		comment.setId(id);
		cService.updateComment(comment);
		return "true";
	}
	
	@RequestMapping(value = "/reply/board/modify/{id}", method = {RequestMethod.GET})
	public @ResponseBody String modifyBoardComment(HttpServletRequest reqeust, Comment comment, @PathVariable Long id, Principal principal) {
		String writer=reqeust.getParameter("writer");
		String accessUser=initializeUser(principal).getEmail();
		if(!accessUser.equals(writer)){
			return "false";
		}
		comment.setContent(comment.getContent().replaceAll("\n", "<br/>"));
//		comment.setId(id);
		cService.updateComment(comment);
		return "true";
	}
	
	@RequestMapping(value = "/reply/board/list", method = {RequestMethod.GET}, produces = "application/json; charset=utf8")
	public @ResponseBody String getCommentsList(@RequestParam Long board_id, HttpServletRequest request, ModelMap model) throws JsonProcessingException {
		//댓글 가져오기
		int cPage = commonService.checkVDInt(request.getParameter("cp"), 1);
		int sType = commonService.checkVDInt(request.getParameter("sty"), 0);
		String question = commonService.checkVDQuestion(request.getParameter("sty"));
		int sDate = commonService.checkVDInt(request.getParameter("sda"), 0);
		int limit = commonService.checkVDInt(request.getParameter("li"), 5);
		String pfName = commonService.checkVDQuestion(request.getParameter("pf"));
		Paging paging = new Paging(cPage, sType, question, sDate, limit, pfName);
		paging.setId(board_id);

		// 전체 게시판 갯수 확인
		int totalCount = cService.getCount(paging);
		
		paging.setTotalCount(totalCount);
		commonService.setPaging(paging);
		
		List<Comment> comments=cService.findAllComments(paging);
		
		Map<String, Object> jsonObject = new HashMap<String, Object>();
		
		jsonObject.put("paging", paging);
		jsonObject.put("comments", comments);
		
		String result=commonService.getJSONData(jsonObject);
		return result;
	}

	// 선언하면 모델값으로 쉽게 넘길 수 있음
	@ModelAttribute("accessUser")
	public User initializeUser(Principal principal) {
		User user= commonService.getPrincipal(principal);
		return user;
	}
	
//	@RequestMapping(value = "/board/{hospitalAlias}/{boardType}/{path}/commentListJSON", method = RequestMethod.GET, produces = "application/json; charset=utf8")
//	public @ResponseBody String setCommentListJSON(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable("path") int path, @PathVariable("hospitalAlias") String hospitalAlias,
//			@PathVariable("boardType") String boardType) throws JsonProcessingException, UnsupportedEncodingException {
//		Map<String, Object> jsonObject = new HashMap<String, Object>();
//
//		String userName = UserCFn.getPrincipal();
//
//		// 게시판 주소 유효성 체크
//		MemberInfo MemberInfo = MemberInfoDAO.getUserbyEmailJoinHospital(userName);
//		String boardTypeFirst = boardType.substring(0, 1);
//
//		System.out.println("MyLog:value:" + boardTypeFirst);
//		int boardNumber = getCookieBoardNumber(request, response);
//		int validCheck = boardFn.setValidCheck_Board(boardNumber, hospitalAlias, MemberInfo, boardTypeFirst);
//
//		if (validCheck == -1)
//			return "error";
//
//		// 기본 parameters setting
//		int cPage = CommonFn.checkVDInt(request.getParameter("cPage"), 1);
//		int sType = CommonFn.checkVDInt(request.getParameter("stype"), -1);
//		String rawQuestion = request.getParameter("stext");
//		int limit = 15;// 페이지 레코드 수 (게시물 limit)
//
//		// 종속 parametes setting
//		Params boardInfo = boardFn.setBoardInfo(cPage, limit, sType, rawQuestion, boardNumber);
//		boardInfo.setBoardId(path);
//
//		// 페이징 설정
//		Params boardInfoTotalCount = new Params(path, boardNumber);
//
//		BoardPaging boardPaging = boardFn.setBoardPaging(cPage, limit, boardInfoTotalCount, 3);
//		// result.addObject("boardPaging", boardPaging);
//
//		// 본문 프로세스
//		ArrayList<BoardComments> commentList = boardCommentDAO.getList(boardInfo);
//
//		for (int i = 0; i < commentList.size(); i++) {
//			int memberId = commentList.get(i).getCommentCreatedBy();
//			MemberInfo commentMemberInfo = MemberInfoDAO.getUserById(memberId);
//			commentList.get(i).setCommentMemberInfo(commentMemberInfo);
//		}
//		
//		jsonObject.put("boardPaging", boardPaging);
//		jsonObject.put("commentList", commentList);
//
//		String jsonString = getJSONData(jsonObject);
//		
//		return jsonString;
//	}
}
