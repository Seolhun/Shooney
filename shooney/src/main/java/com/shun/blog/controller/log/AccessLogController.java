package com.shun.blog.controller.log;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shun.blog.model.log.AccessLog;
import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.log.AccessLogService;

@RestController
public class AccessLogController {
	private AccessLogService accessLogService;
	private CommonService commonService;
	
	@Autowired	
	public AccessLogController(CommonService commonService, AccessLogService accessLogService){
		this.commonService=commonService;
		this.accessLogService=accessLogService;
	}
	
	@RequestMapping(path="/login/info", method=RequestMethod.GET)
	public void checkToday(AccessLog log, HttpServletRequest request) throws Exception{ 
		log=accessLogService.findByIp(log);
		if(log==null){
			accessLogService.insertAccessLog(log);
		}
	}
}
