package com.shun.blog.controller.board;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shun.blog.model.board.Board;
import com.shun.blog.model.board.EntityName;
import com.shun.blog.model.board.PortfolioName;
import com.shun.blog.model.common.Paging;
import com.shun.blog.model.file.FileData;
import com.shun.blog.model.user.User;
import com.shun.blog.service.board.BoardService;
import com.shun.blog.service.comment.CommentService;
import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.user.UserService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService bService;

	@Autowired
	UserService uService;
	
	@Autowired
	CommentService cService;

	@Autowired
	CommonService commonService;

	@Autowired
	MessageSource messageSource;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	private static final String UPLOAD_LOCATION = "/Users/HunSeol/Desktop/shooney/file/";

	@RequestMapping(value = "/{kind}/list", method = RequestMethod.GET)
	public String allBoardList(ModelMap model, @PathVariable String kind, HttpServletRequest request) {
		logger.info("TEST : Get Board List of " + kind);

		// 파라미터 호출 및 유효성 검사
		int cPage = commonService.checkVDInt(request.getParameter("cp"), 1);
		int sType = commonService.checkVDInt(request.getParameter("sty"), 0);
		String sText = commonService.checkVDQuestion(request.getParameter("sty"));
		int sDate = commonService.checkVDInt(request.getParameter("sda"), 0);
		int limit = commonService.checkVDInt(request.getParameter("li"), 20);
		String pfName = commonService.checkVDQuestion(request.getParameter("pf"));
		Paging paging = new Paging(cPage, sType, sText, sDate, limit, kind, pfName);
		
		// 전체 게시판 갯수 확인
		int totalCount = bService.getCount(paging);
		paging.setTotalCount(totalCount);
		commonService.setPaging(paging);
		List<Board> boards = bService.findAllBoards(paging);
		
		model.addAttribute("boards", boards);
		model.addAttribute("paging", paging);
		model.addAttribute("kind", kind);
		model.addAttribute("pfNames", PortfolioName.values());
		return "board/list";
	}

	@RequestMapping(value = { "/{kind}/add" }, method = RequestMethod.GET)
	public String addBoard(ModelMap model, @PathVariable String kind) {
		Board board = new Board();
		model.addAttribute("board", board);

		model.addAttribute("edit", false);
		model.addAttribute("enNames", EntityName.values());
		model.addAttribute("pfNames", PortfolioName.values());
		model.addAttribute("kind", kind);
		return "board/add";
	}

	@RequestMapping(value = { "/{kind}/add" }, method = RequestMethod.POST)
	public String addBoardDo(@Valid Board board, @Valid FileData fileData, BindingResult result,
			ModelMap model, @PathVariable String kind, HttpServletRequest req) throws IOException {
		// Board 부분
		if (result.hasErrors()) {
			model.addAttribute("board", board);
			model.addAttribute("edit", false);
			model.addAttribute("enNames", EntityName.values());
			model.addAttribute("pfNames", PortfolioName.values());
			model.addAttribute("kind", kind);
			return "board/add";
		}

//		board.setWriter(initializeUser().getNickname());
		bService.saveBoard(board);

//		model.addAttribute("success", "Board " + board.getWriter() + "의 " + board.getTitle() + "성공적으로 등록되었습니다.");
		model.addAttribute("kind", kind);

		// File Upload 부분
		logger.info("Fetching files");
		List<String> fileNames = new ArrayList<String>();
		// Now do something with file...
//		for (FileData bucket : fileData.getFiles()) {
//			FileCopyUtils.copy(bucket.getFile().getBytes(),
//					new File(UPLOAD_LOCATION + bucket.getFile().getOriginalFilename()));
//			fileNames.add(bucket.getFile().getOriginalFilename());
//		}

		model.addAttribute("fileNames", fileNames);
		return "result/success";
	}

	@RequestMapping(value = { "/{kind}/{id}" }, method = RequestMethod.GET)
	public String detailBoard(@PathVariable Long id, ModelMap model, @PathVariable String kind, HttpServletRequest request, HttpServletResponse response, Principal principal) {
		String strId=String.valueOf(id);
		
		if(checkHitCookie(request, response, strId)){
			Board board=new Board();
			board.setId(id);;
			board.setHits(1);
			bService.updateBoard(board);
		}
		
		Board board = bService.findById(id);
		
		model.addAttribute("board", board);
		model.addAttribute("edit", false);
		model.addAttribute("kind", kind);
		model.addAttribute("accessUser", initializeUser(principal));
		model.addAttribute("enNames", board.getEntityName());
		model.addAttribute("pfNames", board.getPfName());
		
		return "board/detail";
	}

	@RequestMapping(value = { "/{kind}/modify" }, method = RequestMethod.GET)
	public String editBoard(@RequestParam(required=true) Long id, ModelMap model, @PathVariable String kind) {
		Board board = bService.findById(id);

//		if (!(board.getWriter().equals(initializeUser().getNickname()))) {
//			return "redirect:/" + kind + "/r" + id;
//		}

		model.addAttribute("board", board);
		model.addAttribute("edit", true);
		model.addAttribute("kind", kind);
		model.addAttribute("enNames", EntityName.values());
		model.addAttribute("pfNames", PortfolioName.values());
		return "board/add";
	}

	@RequestMapping(value = { "/{kind}/modify" }, method = RequestMethod.POST)
	public String editBoardDo(@Valid Board board, BindingResult result, ModelMap model, @PathVariable String kind) {
		if (result.hasErrors()) {
			model.addAttribute("edit", true);
			model.addAttribute("kind", kind);
			return "board/add";
		}

//		if (!(board.getWriter().equals(initializeUser().getNickname()))) {
//			return "redirect:/" + kind + "/r" + id;
//		}

		bService.updateBoard(board);

//		model.addAttribute("success", "Board " + board.getWriter() + "의 " + board.getTitle() + "성공적으로 수정되었습니다.");
		model.addAttribute("entity", kind);
		return "result/success";
	}

	@RequestMapping(value = { "/{kind}/delete" }, method = RequestMethod.GET)
	public String deleteBoard(@PathVariable String kind, @RequestParam(required=true) Long id) {
		Board board = bService.findById(id);
//		if (!(board.getWriter().equals(initializeUser().getNickname()))) {
//			return "redirect:/" + kind + "/r" + id;
//		}
//		board.setDelCheck(1);
		bService.updateBoard(board);
		return "redirect:/" + kind + "/list";
	}

	// 선언하면 모델값으로 쉽게 넘길 수 있음
	@ModelAttribute("accessUser")
	public User initializeUser(Principal principal) {
		String accessEmail = principal.getName();
		return uService.findByEmail(accessEmail);
	}

	// @RequestMapping(value = { "/{tableName}/detail" }, method =
	// RequestMethod.GET)
	// public String getBoardDetail(ModelMap model, HttpServletRequest request,
	// HttpServletResponse response, @PathVariable String tableName,
	// @RequestParam int id) throws Exception {
	// // RequestMethod.GET일 때의 기본언어 설정
	// Language language = commonFn.setLanguageData(text_ko, text_en, request);
	// String target = "Board";
	// String targetName = "common.main.information";
	// commonFn.setDefaultSetting(model, language, target, targetName);
	//
	// Board board = new Board();
	// board.setId(id);
	// // 조회수 증가
	// if (checkHitCookie(board, request, response)) {
	// board.setTableName(tableName);
	// bDao.updateData(board, 2);
	// }
	//
	// // 불러올 게시물 설정
	// board.setTableName(tableName);
	// board = bDao.getDetailbyKey(board);
	//
	// // 불러올 댓글 페이징 설정
	// Paging paging = new Paging();
	// paging.setTableName(tableName);
	// paging.setLimit(commonFn.checkVDInt(request.getParameter("limit"), 5));
	// paging.setcPage(commonFn.checkVDInt(request.getParameter("cPage"), 1));
	// String rawQuestion = request.getParameter("sText");
	// paging.setQuestion(rawQuestion);
	//
	// // 총 페이지 가져오기.
	// int totalCount = bDao.getTotalReplyCount(board, paging);
	// paging.setTotalPage(totalCount);
	// setPaging(paging);
	// board.setReplyList(bDao.getAllReply(board, paging));
	//
	// int reno = commonFn.checkVDInt(request.getParameter("reno"), 0);
	// if (reno > 0) {
	// Reply reply=new Reply();
	// reply.setId(reno);
	// reply=bDao.getReply(reply);
	// model.addAttribute("modifyReply", reply);
	// }
	//
	// // 리다이렉트를 통해 온 에러메시지를 확인하는 곳.
	// try {
	// String error = request.getParameter("error");
	// if (error != null ) {
	// model.addAttribute("error", error);
	// }
	//
	// String replyError = request.getParameter("replyError");
	// if (replyError != null) {
	// model.addAttribute("replyError", replyError);
	// }
	// } catch (NullPointerException e) {
	// //e.printStackTrace();
	// }
	// String logUser=commonFn.getPrincipal();
	// Reply reply=new Reply();
	// model.addAttribute("reply", reply);
	// model.addAttribute("logUser", logUser);
	// model.addAttribute("paging", paging);
	// model.addAttribute("tableName", tableName);
	// model.addAttribute("board", board);
	// return "board/detail";
	// }

	private boolean checkHitCookie(HttpServletRequest request, HttpServletResponse response,
			String id) {
		// 쿠키에 담을 값을 가져오기 위함.(uri는 테이블 값을 가져오기 위함 - 3번)
		boolean validHit = false;
		String tableName="bh";
		String split="A4G9";

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
			String[] upHitByNo = originalNo.split(split);
			for (int i = 0; i < upHitByNo.length; i++) {
				if (upHitByNo[i].equals(id)) {
					return validHit;
				}
			}
			originalNo = originalNo + split + id;
			cookie = new Cookie(tableName, originalNo);
		}

		cookie.setMaxAge(24 * 60 * 60); // 365*24*60*60 365일
		response.addCookie(cookie);
		validHit = true;
		return validHit;
	}
}