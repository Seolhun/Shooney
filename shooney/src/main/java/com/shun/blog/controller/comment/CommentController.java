package com.shun.blog.controller.comment;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shun.blog.model.comment.Comment;
import com.shun.blog.service.comment.CommentService;
import com.shun.blog.service.common.CommonService;

@Controller
@RequestMapping(value = "/reply", produces = "application/json")
public class CommentController {
	private CommonService commonService;
	private CommentService commentService;;
	
	@Autowired
	public CommentController(CommentService commentService, CommonService commonService) {
		this.commonService=commonService;
		this.commentService=commentService;
	}

	private static final Logger LOG = LoggerFactory.getLogger(CommentController.class);
	
	@RequestMapping(value = "/{entity}/delete/{commentId}", method = {RequestMethod.GET})
	@ResponseBody
	public String deleteBoardComment(HttpServletRequest reqeust, Comment comment, @PathVariable String entity, @PathVariable Long commentId, Principal principal) throws Exception{
		String writer=reqeust.getParameter("writer");
		String accessUser=principal.getName();
		if(!accessUser.equals(writer)){
			return "false";
		}
		
//		comment.setDelCheck(1);
//		comment.setId(id);
		commentService.updateComment(comment);
		return "true";
	}
	
	@RequestMapping(value = "/board/modify/{id}", method = {RequestMethod.GET})
	public @ResponseBody String modifyBoardComment(HttpServletRequest reqeust, Comment comment, @PathVariable Long id, Principal principal) throws Exception{
		String writer=reqeust.getParameter("writer");
		String accessUser=principal.getName();
		if(!accessUser.equals(writer)){
			return "false";
		}
		comment.setContent(comment.getContent().replaceAll("\n", "<br/>"));
		commentService.updateComment(comment);
		return "true";
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
