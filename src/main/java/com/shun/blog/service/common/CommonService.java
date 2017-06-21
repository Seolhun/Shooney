package com.shun.blog.service.common;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.shun.blog.model.common.Paging;
import com.shun.blog.model.menu.Menu;
import com.shun.blog.model.user.User;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

public interface CommonService {
	boolean validPattern(String parameter, String patternName);
	
	//Long 유효성 검사하기.
	Long checkVDLong(String parameter, int defaultValue);
	
	//int 유효성 검사하기.
	int checkVDInt(String parameter, int defaultValue);
	
	//Integer로 Null값 체크하기.
	int checkVDInteger(Integer parameter, int default_value);
	
	//Float 유효성 검사하기.
	float checkVDFloat(String parameter, int defaultValue);
	
	//검색어 유효성 검사하기.
	String checkVDQuestion(String question);
	
	//데이트 포맷바꾸기.
	Timestamp convertDateToday(String formatYouWnat) throws Exception;
	Timestamp convertDateFormat(String formatYouWnat, int day) throws Exception;
	
	//JsonData를 VO에 매핑하기.
	ObjectMapper setJSONMapper() throws JsonProcessingException;
	//VO에 있는 값들 JSON으로 변환하기,
	String getJSONData(Object rawData) throws JsonProcessingException;
	//Convert Object -> Map : Map -> Object
	String convertMapToVo(ObjectMapper mapper, Object object) throws Exception;
	Map<?, ?> convertVoToMap(Object object) throws Exception;
	
	//User IP를 가져오기.	
	String getUserIP(HttpServletRequest request);

	//Encode SHA256
	String buildSHA256(String str);

    //Spring Security Auth check
	boolean getLoginAuthValidation(Authentication auth, String authNameYouWant);

    //Post Email Locked user.
    void sendEmailLockingUser(String toEmail, String userName, String authentication, String httpPath, String password) throws IOException;

    //Post Email
    String mainSendMail(String toEmail, String from, String mailSubject, String subTitle, String mailContent) throws Exception;
	
	//@Valid로 검사시 중복값 리다이렉트해주기.
	void validCheckAndSendError(MessageSource messageSource, BindingResult bindingResult, HttpServletRequest request, String inputValue, String objectName, String fieldName, String messagePropertyName);
	
	//리스트 페이징하기.
	void setAndValidationPaging(Paging paging);
	
	//페이징 전에 페이징 데이터 가져오
	Paging beforeGetPaging(HttpServletRequest request);
	Paging beforePostPaging(Paging paging);
	
	//현재 접속한 유저로 유저정보 가져오기.
	User getAccessUserToModel() throws Exception;
	String getPrincipal() throws Exception;
	
	//Menu 역할 확인하여 설정잡기.	
	Menu setMenuConfig(HttpServletRequest request) throws Exception;

    String getImgUsingJsoup(String imgSrc, String savedDirectoryName) throws IOException, StringIndexOutOfBoundsException;

    JsonObject getResponseAPI(String apiUrl) throws IOException;

    Paging lastestSetPaging(int totalCount, int currentPage, int blockLimit) throws NullPointerException;
}
