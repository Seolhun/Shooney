package com.shun.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shun.blog.controller.common.CommonFn;
import com.shun.blog.model.board.Board;
import com.shun.blog.model.board.EntityName;
import com.shun.blog.model.board.PortfolioName;
import com.shun.blog.model.common.Language;
import com.shun.blog.model.common.Paging;
import com.shun.blog.service.board.BoardService;

@Controller
@RequestMapping("/")
public class BoardController {

	@Autowired
	BoardService boardService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	CommonFn commonFn;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@RequestMapping(value = "/bo/{kind}/list", method = RequestMethod.GET)
	public String allBoardList(ModelMap model, @PathVariable String kind, HttpServletRequest request) {
		// 파라미터 호출 및 유효성 검사
		int cPage = commonFn.checkVDInt(request.getParameter("cp"), 1);
		int sType = commonFn.checkVDInt(request.getParameter("sty"), 0);
		String question = commonFn.checkVDQuestion(request.getParameter("ste"));
		int limit = commonFn.checkVDInt(request.getParameter("li"), 20);
		String pfName = commonFn.checkVDQuestion(request.getParameter("pf"));
		Paging paging = new Paging(cPage, sType, question, limit, kind, pfName);
		
		// 전체 게시판 갯수 확인
		int totalCount = boardService.getCount(paging);
		paging.setTotalCount(totalCount);
		commonFn.setPaging(paging);
		List<Board> boards = boardService.findAllBoards(paging);
		
		model.addAttribute("boards", boards);
		model.addAttribute("paging", paging);
		model.addAttribute("kind", kind);
		return "board/list";
	}

	@RequestMapping(value = { "/bo/{kind}/add" }, method = RequestMethod.GET)
	public String newUser(ModelMap model, @PathVariable String kind) {
		Board board = new Board();
		model.addAttribute("board", board);
		model.addAttribute("edit", false);
		model.addAttribute("enNames", EntityName.values());
		model.addAttribute("pfNames", PortfolioName.values());
		model.addAttribute("kind", kind);
		return "board/add";
	}

	@RequestMapping(value = { "/bo/{kind}/add" }, method = RequestMethod.POST)
	public String saveUser(@Valid Board board, BindingResult result, ModelMap model, @PathVariable String kind) {
		if (result.hasErrors()) {
			model.addAttribute("board", board);
			model.addAttribute("edit", false);
			model.addAttribute("enNames", EntityName.values());
			model.addAttribute("pfNames", PortfolioName.values());
			model.addAttribute("kind", kind);
			return "board/add";
		}
		
		board.setWriter(commonFn.getPrincipal());
		boardService.saveBoard(board);

		model.addAttribute("success", "Board " + board.getWriter() + "의 " + board.getTitle() + "성공적으로 등록되었습니다.");
		model.addAttribute("kind", kind);
		return "success";
	}
	
	@RequestMapping(value = { "/bo/{kind}/detail-{id}" }, method = RequestMethod.GET)
	public String detailBoard(@PathVariable int id, ModelMap model, @PathVariable String kind) {
		Board board = boardService.findById(id);
		model.addAttribute("board", board);
		model.addAttribute("edit", false);
		model.addAttribute("kind", kind);
		model.addAttribute("enNames", board.getEntityName());
		model.addAttribute("pfNames", board.getPfName());
		return "board/detail";
	}

	@RequestMapping(value = { "/bo/{kind}/edit-{id}" }, method = RequestMethod.GET)
	public String editBoard(@PathVariable int id, ModelMap model, @PathVariable String kind) {
		Board board = boardService.findById(id);
		model.addAttribute("board", board);
		model.addAttribute("edit", true);
		model.addAttribute("kind", kind);
		model.addAttribute("enNames", board.getEntityName());
		model.addAttribute("pfNames", board.getPfName());
		return "board/add";
	}

	@RequestMapping(value = { "/bo/{kind}/edit-{id}" }, method = RequestMethod.POST)
	public String editBoardDo(@Valid Board board, BindingResult result, ModelMap model, @PathVariable int id, @PathVariable String kind) {
		if (result.hasErrors()) {
			model.addAttribute("edit", true);
			model.addAttribute("kind", kind);
			return "board/add";
		}
		boardService.updateBoard(board);

		model.addAttribute("success", "Board " + board.getWriter() + "의 " + board.getTitle() + "성공적으로 수정되었습니다.");
		model.addAttribute("entity", kind);
		return "success";
	}

	@RequestMapping(value = { "/bo/{kind}/delete-{id}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable int id, @PathVariable String kind) {
		boardService.deleteUserById(id);
		return "redirect:/bo_{kind}list";
	}
	
//	@RequestMapping(value = { "/board/{tableName}/detail" }, method = RequestMethod.GET)
//	public String getBoardDetail(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable String tableName, @RequestParam int id) throws Exception {
//		// RequestMethod.GET일 때의 기본언어 설정
//		Language language = commonFn.setLanguageData(text_ko, text_en, request);
//		String target = "Board";
//		String targetName = "common.main.information";
//		commonFn.setDefaultSetting(model, language, target, targetName);
//		
//		Board board = new Board();
//		board.setId(id);
//		// 조회수 증가
//		if (checkHitCookie(board, request, response)) {
//			board.setTableName(tableName);
//			bDao.updateData(board, 2);
//		}
//
//		// 불러올 게시물 설정
//		board.setTableName(tableName);
//		board = bDao.getDetailbyKey(board);
//
//		// 불러올 댓글 페이징 설정
//		Paging paging = new Paging();
//		paging.setTableName(tableName);
//		paging.setLimit(commonFn.checkVDInt(request.getParameter("limit"), 5));
//		paging.setcPage(commonFn.checkVDInt(request.getParameter("cPage"), 1));
//		String rawQuestion = request.getParameter("sText");
//		paging.setQuestion(rawQuestion);
//
//		// 총 페이지 가져오기.
//		int totalCount = bDao.getTotalReplyCount(board, paging);
//		paging.setTotalPage(totalCount);
//		setPaging(paging);
//		board.setReplyList(bDao.getAllReply(board, paging));
//
//		int reno = commonFn.checkVDInt(request.getParameter("reno"), 0);
//		if (reno > 0) {
//			Reply reply=new Reply();
//			reply.setId(reno);
//			reply=bDao.getReply(reply);
//			model.addAttribute("modifyReply", reply);
//		}
//		
//		// 리다이렉트를 통해 온 에러메시지를 확인하는 곳.
//		try {
//			String error = request.getParameter("error");
//			if (error != null ) {
//				model.addAttribute("error", error);
//			}
//			
//			String replyError = request.getParameter("replyError");
//			if (replyError != null) {
//				model.addAttribute("replyError", replyError);
//			}
//		} catch (NullPointerException e) {
//			//e.printStackTrace();
//		}
//		String logUser=commonFn.getPrincipal();
//		Reply reply=new Reply();
//		model.addAttribute("reply", reply);
//		model.addAttribute("logUser", logUser);
//		model.addAttribute("paging", paging);
//		model.addAttribute("tableName", tableName);
//		model.addAttribute("board", board);
//		return "board/detail";
//	}
	
	private boolean checkHitCookie(Board board, HttpServletRequest request, HttpServletResponse response) {
		// 쿠키에 담을 값을 가져오기 위함.(uri는 테이블 값을 가져오기 위함 - 3번)
		boolean validHit = false;
		String id = request.getParameter("id");
		String[] tableNames = request.getRequestURI().split("/");
		String tableName = tableNames[3];

		// 쿠키 이름값 보안처리하기
		tableName = commonFn.buildSHA256(tableName).substring(0, 5);

		// 쿠키 가져오기(체크 하는것)
		Map<String, String> cookieMap = new HashMap<>();
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookieMap.put(cookies[i].getName(), cookies[i].getValue());
			}
		}

		// 저장된 쿠키 중 읽었었던 해당 게시판의 번호 불러오기
		String originalNo = (String) cookieMap.get(tableName);
		Cookie cookie = null;
		if (originalNo == null) {
			cookie = new Cookie(tableName, id);
		} else {
			String[] upHitByNo = originalNo.split("T3Y1");
			for (int i = 0; i < upHitByNo.length; i++) {
				if (upHitByNo[i].equals(id)) {
					return validHit;
				}
			}
			originalNo = originalNo + "T3Y1" + id;
			cookie = new Cookie(tableName, originalNo);
		}

		cookie.setPath("/iMediSynHome");
		cookie.setMaxAge(24 * 60 * 60); // 365*24*60*60 365일
		response.addCookie(cookie);
		validHit = true;
		return validHit;
	}
}