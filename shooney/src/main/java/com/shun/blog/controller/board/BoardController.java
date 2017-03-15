package com.shun.blog.controller.board;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.shun.blog.model.board.Board;
import com.shun.blog.model.board.EntityName;
import com.shun.blog.model.common.Paging;
import com.shun.blog.model.portfolio.PortfolioName;
import com.shun.blog.service.board.BoardService;
import com.shun.blog.service.comment.CommentService;
import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.file.FileService;
import com.shun.blog.service.user.UserService;

@Controller
@RequestMapping("/board")
public class BoardController {
	private BoardService boardService;
	private UserService userService;
	private CommentService commentService;
	private CommonService commonService;
	private MessageSource messageSource;
	private FileService fileService;
	
	private static final String FILE_PATH="/Users/hunseol/Desktop/project/shooney/file/";
	
	@Autowired
	public BoardController(UserService userService, CommentService commentService, BoardService boardService,
			CommonService commonService, MessageSource messageSource, FileService fileService) {
		this.userService = userService;
		this.commentService = commentService;
		this.boardService = boardService;
		this.commonService=commonService;
		this.messageSource=messageSource;
		this.fileService=fileService;
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(BoardController.class);

	/**
	 * 게시판 리스트
	 * 
	 * @param String portfolioType
	 * @return String  -view
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String allBoardList(ModelMap model, HttpServletRequest request, @RequestParam(required=false, name="pf") String portfolioType) {
		//페이징 세팅 및 파라미터 가져오기.
		Paging paging=commonService.beforePagingGetData(request);
		paging.setPortfolioType(portfolioType);
		
		// 전체 게시판 갯수 확인
		int totalCount = boardService.getCount(paging);
		paging.setTotalCount(totalCount);
		commonService.setPaging(paging);
		List<Board> boards =new ArrayList<>();
		try {
			boards = boardService.selectList(paging);
		} catch (NullPointerException e) {
			
		}
		
		model.addAttribute("boards", boards);
		model.addAttribute("paging", paging);
		model.addAttribute("pfNames", PortfolioName.values());
		return "board/board-list";
	}

	/**
	 * 게시판 등록 페이지 이동
	 * 
	 * @param -
	 * @return String  -view
	 * @throws Exception
	 */
	@GetMapping(value={"/insert"})
	public String addBoard(ModelMap model) {
		model.addAttribute("board", new Board());
		model.addAttribute("edit", false);
		model.addAttribute("enNames", EntityName.values());
		model.addAttribute("pfNames", PortfolioName.values());
		return "board/board-insert";
	}

	/**
	 * 게시판 등록하기 - 파일업로드
	 * 
	 * @param Board board, FileData fileData
	 * @return String  -view
	 * @throws Exception
	 */
	@RequestMapping(value = "/insert/file", method = RequestMethod.POST)
	public String addBoardDo(Board board, BindingResult bindingResult,  ModelMap model, HttpServletRequest request, @RequestParam("files") MultipartFile[] files) throws Exception {
		//Board 부분
		model.addAttribute("board", board);
		model.addAttribute("edit", false);
		model.addAttribute("enNames", EntityName.values());
		model.addAttribute("pfNames", PortfolioName.values());
		
		//유효성 검사.
		String mapping="board/board-insert";
		if(board.getTitle().length()<5){
			commonService.validCheckAndSendError(messageSource, bindingResult, request, board.getTitle(), "board", "title", "INVALID-TITLE");
		} else if(board.getContent().length()<5){
			commonService.validCheckAndSendError(messageSource, bindingResult, request, board.getContent(), "board", "content", "INVALID-CONTENT");
		} else if (bindingResult.hasErrors()) {
			return mapping;
		}
		
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			try {
				byte[] bytes = file.getBytes();
				// Creating the directory to store file
				String rootPath = FILE_PATH;
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists()){
					dir.mkdirs();
				}

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()+ File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				LOG.info("Server File Location={}", serverFile.getAbsolutePath());
			} catch (Exception e) {
				
			}
		}
		
		board.setCreatedBy(commonService.getAccessUserToModel().getNickname());
		boardService.insert(board);
		return "result/success";
	}

	@RequestMapping(value = { "/{kind}/{id}" }, method = RequestMethod.GET)
	public String detailBoard(@PathVariable Long id, ModelMap model, @PathVariable String kind, HttpServletRequest request, HttpServletResponse response, Principal principal) throws Exception {
		String strId=String.valueOf(id);
		
		if(checkHitCookie(request, response, strId)){
			Board board=new Board();
			board.setId(id);;
			board.setHits(1);
			boardService.update(board);
		}
		
		Board board = boardService.selectById(id);
		
		model.addAttribute("board", board);
		model.addAttribute("edit", false);
		model.addAttribute("kind", kind);
		commonService.getAccessUserToModel();
		model.addAttribute("enNames", board.getEntityName());
		model.addAttribute("pfNames", board.getPortfolioType());
		
		return "board/board-detail";
	}

	@RequestMapping(value = { "/{kind}/modify" }, method = RequestMethod.GET)
	public String editBoard(@RequestParam(required=true) Long id, ModelMap model, @PathVariable String kind) {
		Board board = boardService.selectById(id);

//		if (!(board.getWriter().equals(initializeUser().getNickname()))) {
//			return "redirect:/" + kind + "/r" + id;
//		}

		model.addAttribute("board", board);
		model.addAttribute("edit", true);
		model.addAttribute("kind", kind);
		model.addAttribute("enNames", EntityName.values());
		model.addAttribute("pfNames", PortfolioName.values());
		return "board/board-insert";
	}

	@RequestMapping(value = { "/{kind}/modify" }, method = RequestMethod.POST)
	public String editBoardDo(@Valid Board board, BindingResult result, ModelMap model, @PathVariable String kind) {
		if (result.hasErrors()) {
			model.addAttribute("edit", true);
			model.addAttribute("kind", kind);
			return "board/board-insert";
		}

//		if (!(board.getWriter().equals(initializeUser().getNickname()))) {
//			return "redirect:/" + kind + "/r" + id;
//		}

		boardService.update(board);

//		model.addAttribute("success", "Board " + board.getWriter() + "의 " + board.getTitle() + "성공적으로 수정되었습니다.");
		model.addAttribute("entity", kind);
		return "result/success";
	}

	@RequestMapping(value = { "/{kind}/delete" }, method = RequestMethod.GET)
	public String deleteBoard(@PathVariable String kind, @RequestParam(required=true) Long id) {
		Board board = boardService.selectById(id);
//		if (!(board.getWriter().equals(initializeUser().getNickname()))) {
//			return "redirect:/" + kind + "/r" + id;
//		}
//		board.setDelCheck(1);
		boardService.update(board);
		return "redirect:/" + kind + "/list";
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

	private boolean checkHitCookie(HttpServletRequest request, HttpServletResponse response, String id) {
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
	
	//파일 존재 여부 확인 Method
	private void validFileExist(MultipartHttpServletRequest multipartRequst, Map<String, MultipartFile> fileMap) throws Exception {
		Iterator<String> iterator = multipartRequst.getFileNames();
		// 첨부된 파일이 있으면 파일시퀀스 증가하고 가져오기.
		while (iterator.hasNext()) {
			MultipartFile multipartFile = multipartRequst.getFile(iterator.next());
			LOG.info("param : file : {}",multipartFile);
			if (multipartFile.isEmpty() == false) {
				// FILE_ID 넣을 key 값.
//				FileData dbFileData = fileService.selectById(fileData.getFileDataId());
//				fileData.setFileDataId(dbFileData.getFileDataId());
			}
		}
	}
}