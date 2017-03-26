package com.shun.blog.service.common;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shun.blog.model.common.Paging;
import com.shun.blog.model.user.User;
import com.shun.blog.service.user.UserService;

@Service
@Transactional(transactionManager="txManager")
public class CommonServiceImpl implements CommonService {
	static final Logger LOG = LoggerFactory.getLogger(CommonServiceImpl.class);
	
	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CommonService commonService;
	
	final private String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,3})$";
	final private String idPattern = "^[A-Za-z0-9].{1,20}";
	final private String passwordPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+=-~`]).{8,20})";
//	final private String passwordPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}";
	/*
		(?=.*[0-9]) a digit must occur at least once
		(?=.*[a-z]) a lower case letter must occur at least once
		(?=.*[A-Z]) an upper case letter must occur at least once
		(?=.*[@#$%^&+=]) a special character must occur at least once
		(?=\\S+$) no whitespace allowed in the entire string
		.{8,} at least 8 characters
	*/
	final private String namePattern = ".[가-힣]{1,14}";
	final private String phonePattern = "\\d{10,11}";
	final private String telPattern = "\\d{9,10}";
	
	@Override
	//Pattern에 틀리면 True || 맞으면 False
	public boolean validPattern(String parameter, String patternName) {
		boolean validation=false;
		switch (patternName) {
		case "password":
			validation=parameter.matches(passwordPattern);
		case "email":
			validation=parameter.matches(emailPattern);
		case "id":
			validation=parameter.matches(idPattern);
		case "name":
			validation=parameter.matches(namePattern);
		case "phone":
			validation=parameter.matches(phonePattern);
		case "tel":
			validation=parameter.matches(telPattern);
		}
		LOG.info("return : {}", validation);
		return validation;
	}
	
	@Override
	public Long checkVDLong(String parameter, int default_value) {
		Long longValue;
		try {
			longValue = Long.parseLong(parameter);
		} catch (Exception e) {
			longValue = Integer.toUnsignedLong(default_value);
		}
		return longValue;
	}

	@Override
	public int checkVDInt(String parameter, int default_value) {
		int int_value;
		try {
			int_value = Integer.parseInt(parameter);
		} catch (Exception e) {
			int_value = default_value;
		}
		return int_value;
	}

	@Override
	public float checkVDFloat(String parameter, int default_value) {
		float int_value;
		try {
			int_value = Float.parseFloat(parameter);
		} catch (Exception e) {
			int_value = default_value;
		}
		return int_value;
	}
	

	@Override
	public String checkVDQuestion(String question) {
		String question_text = "";
		try {
			if (question.length() > 0) {
				question_text = question.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "")
						.replaceAll("[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]", "%").trim();
			} else {
				question_text = null;
			}
		} catch (Exception e) {
			question_text = null;
		}
		return question_text;
	}

	@Override
	public ObjectMapper setJSONMapper() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper(); // create once, reuse. Thank
		// Mapping에 실패했을 때도 그냥 실행할 수 있게 하기.
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper;
	}

	@Override
	public String getJSONData(Object rawData) throws JsonProcessingException {
		// 담을 JSON 매핑
		ObjectMapper mapper = new ObjectMapper();
		// Null일 때, Null값을 가져오지 마세요.
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		// 추출한 자료를 JSON으로 매핑하기
		String dtoAsString = mapper.writeValueAsString(rawData);
		return dtoAsString;
	}

	@Override
	public String getUserIP() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = request.getHeader("X-FORWARDED-FOR");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	@Override
	public String buildSHA256(String str) {
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			str = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			str = null;
		}
		return str;
	}
	
	@Override
	public boolean getLoginAuthValidation(Authentication auth, String authNameYouWant) {
		boolean valid=false;
		try {
			for (GrantedAuthority authority: auth.getAuthorities()) {
				String authName=authority.getAuthority();
				if(authName.equals(authNameYouWant)){
					valid=true;
				}
			}	
		} catch (NullPointerException e) {
			LOG.error("ERROR NullPointException - getLoginAuthValidation = "+valid);
		}
		LOG.info("TEST : getLoginAuthValidation = "+valid);
		return valid;
	}
	
	@Override
	public void sendEmailLockingUser(String toEmail, String userName, String authentication, String httpPath, String password) throws IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA);
		Date date = new Date();
		String currentTime = formatter.format(date);
		String from = "imedisyndev@gmail.com";
		String mailSubject = "안녕하세요. (주)Hi-Cord입니다. "+userName+"의 계정이 5회 로그인 실패로 잠금설정 되었습니다.";
		String mailContent = 
				userName+"님 안녕하세요."+" (주)Hi-Cord입니다."
				+ "<br>회원님의 아이디에 누군가가 "+currentTime+"에 로그인 시도를 하여, 5회 이상 실패로 계정이 잠금처리 되었습니다."
				+ "<br>먼저, 비밀번호를 바꾸시길 요청드립니다. 밑의 비밀번호 변경 버튼을 눌러 1회용 Password를 입력하여 비밀번호 변경해주시기 바랍니다."
				+ "<h4>Password : "+password+"</h4>"
				+ "<br><a href="+httpPath+"?key="+authentication+"><button>비밀번호 변경</button></a>"
				+ "<br>추가로 로그인 시도된 정보를 제공해드리오니, 확인하시고 궁금하신 것이 있으시면 문의부탁드립니다.";
		mainSendMail(toEmail, from, mailSubject, mailContent);
	}
	
	public void mainSendMail(String toEmail, String from, String mailSubject, String mailContent) {
		new Thread(){
			public void run(){
				String emailregex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
				if (toEmail.matches(emailregex)) {
					try {
						MimeMessage message = mailSender.createMimeMessage();
						// true로서 멀티파트 메세지라는 의미
						MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
						messageHelper.setFrom(from);
						messageHelper.setTo(toEmail);
						messageHelper.setSubject(mailSubject);
						messageHelper.setText(
							"<html>"
								+ "<body>"
									+ "<div style='text-align : left; font-color:black; font-size : 14px;'>"
										+ mailContent
									+ "</div>"
								+ "</body>"
							+ "</html>", true);
						// 파일첨부하기 하지만, Url위치가 틀려서 파일을 찾을 수 없다고 에러가 발생...수정 요망
						// FileSystemResource fileImage=new
						// FileSystemResource("/resources/img/google.png");
						// messageHelper.addAttachment("Google Png", fileImage);
		
						// 로고 넣기
						// ClassPathResource image=new
						// ClassPathResource("/resources/img/google.png");
						// messageHelper.addInline("Google_Logo", image);
						mailSender.send(message);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	@Override
	public void validCheckAndSendError(MessageSource messageSource, BindingResult bindingResult, HttpServletRequest request, String inputValue, String objectName, String fieldName, String messagePropertyName) {
		try {
			FieldError error = new FieldError(objectName, fieldName, messageSource.getMessage(messagePropertyName, new String[] { inputValue }, request.getLocale()));	
			bindingResult.addError(error);	
		} catch (Exception e) {
			FieldError error = new FieldError(objectName, fieldName, messageSource.getMessage(messagePropertyName, new String[] { inputValue }, Locale.getDefault()));	
			bindingResult.addError(error);
		}
		
	}

	@Override
	public String getPrincipal() throws Exception{
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	// 파라미터 호출 및 유효성 검사
	@Override
	public Paging beforePagingGetData(HttpServletRequest request) {
		int currentPage = commonService.checkVDInt(request.getParameter("cPage"), 1);
		int searchType = commonService.checkVDInt(request.getParameter("sType"), 0);
		String searchText = commonService.checkVDQuestion(request.getParameter("sText"));
		int searchDate = commonService.checkVDInt(request.getParameter("sDate"), 0);
		int limit = commonService.checkVDInt(request.getParameter("limit"), 20);
		Paging paging = new Paging(currentPage, searchType, searchText, searchDate, limit);
		return paging;
	}
	
	@Override
	public void setAndValidationPaging(Paging paging) {
		int blockLimit = 10; // 페이지 당 보여줄 블록 번호 limit
		// [1],[2],[3],[4],[5],[6],[7],[8],[9],[10]
		int totalCount = paging.getTotalCount();
		int limit = paging.getLimit();
		int cPage = paging.getCurrentPage();
		int sType = paging.getSearchType();
		String sText = paging.getSearchText();
		int sDate = paging.getSearchDate();

		int totalPage=(int)Math.ceil(totalCount/limit);
		if(totalPage==0){
			totalPage=1;
		}
		
		int totalBlock = totalCount / limit + (totalCount % limit > 0 ? 1 : 0); // 전체
		int currentBlock = cPage / blockLimit + (cPage % blockLimit > 0 ? 1 : 0);// 현재
		int blockEndNum = currentBlock * blockLimit;
		int blockStartNum = blockEndNum - (blockLimit - 1);

		if (blockEndNum > totalBlock) {
			blockEndNum = totalBlock;
		}

		int previousPage = blockStartNum - blockLimit; // << *[이전]*
		int nextPage = blockStartNum + blockLimit; // *[다음]* >>

		if (previousPage < 1) {
			previousPage = 1;
		}

		if (nextPage > totalBlock) {
			nextPage = totalBlock / blockLimit * blockLimit + 1;
		}
		paging.setTotalPage(totalPage);
		paging.setSearchType(sType);
		paging.setSearchDate(sDate);
		paging.setSearchText(sText);
		paging.setCurrentPage(cPage);
		paging.setBlockLimit(blockLimit);
		paging.setCurrentBlock(currentBlock);
		paging.setTotalBlock(totalBlock);
		paging.setBlockEndNum(blockEndNum);
		paging.setBlockStartNum(blockStartNum);
		paging.setNextPage(nextPage);
		paging.setPreviousPage(previousPage);
		LOG.info("return : setAndValidationPaging {}", paging.toString());
	}

	
	@Override
	public User getAccessUserToModel() throws Exception {
		String userEmail=commonService.getPrincipal();
		LOG.info("return : getAccessUserToModel : {}", userEmail);
		return userService.selectByEmail(userEmail);
	}
}
