package com.shun.blog.service.common;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shun.blog.model.common.Paging;
import com.shun.blog.model.menu.Menu;
import com.shun.blog.model.user.User;

public interface CommonService {
	boolean validPattern(String parameter, String patternName);
	
	//Long 유효성 검사하기.
	Long checkVDLong(String parameter, int defaultValue);
	
	//int 유효성 검사하기.
	int checkVDInt(String parameter, int defaultValue);
	
	//Float 유효성 검사하기.
	float checkVDFloat(String parameter, int defaultValue);
	
	//검색어 유효성 검사하기.
	String checkVDQuestion(String question);
	
	//JsonData를 VO에 매핑하기.
	ObjectMapper setJSONMapper() throws JsonProcessingException;
	
	//VO에 있는 값들 JSON으로 변환하기,
	String getJSONData(Object rawData) throws JsonProcessingException;
	
	//User IP를 가져오기.	
	String getUserIP(HttpServletRequest request);

	//Encode SHA256
	String buildSHA256(String str);
	
	//get Validation about logiun User 	
	boolean getLoginAuthValidation(Authentication auth, String authNameYouWant);
	
	//Post Email Locked user.
	void sendEmailLockingUser(String toEmail, String userName, String authentication, String httpPath, String password) throws IOException;
	
	//@Valid로 검사시 중복값 리다이렉트해주기.
	void validCheckAndSendError(MessageSource messageSource, BindingResult bindingResult, HttpServletRequest request, String inputValue, String objectName, String fieldName, String messagePropertyName);
	
	//로그인된 유저 이메일로 유저 가져오기.(null처리 가능)
	String getPrincipal() throws Exception;
	
	//리스트 페이징하기.
	void setAndValidationPaging(Paging paging);
	
	//페이징 전에 페이징 데이터 가져오
	Paging beforeGetPaging(HttpServletRequest request);
	
	//현재 접속한 유저로 유저정보 가져오기.
	User getAccessUserToModel() throws Exception;
	
	//Menu 역할 확인하여 설정잡기.	
	Menu setMenuConfig(HttpServletRequest request) throws Exception;
}
