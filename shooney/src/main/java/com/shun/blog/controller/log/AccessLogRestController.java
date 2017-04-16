package com.shun.blog.controller.log;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shun.blog.model.log.AccessLog;
import com.shun.blog.service.log.AccessLogService;

@RestController
public class AccessLogRestController {
	private static final Logger LOG = LoggerFactory.getLogger(AccessLogRestController.class);
	
	private AccessLogService accessLogService;
	
	@Autowired	
	public AccessLogRestController(AccessLogService accessLogService){
		this.accessLogService=accessLogService;
	}
	
	@RequestMapping(path="/access/client/insert", method=RequestMethod.POST)
	public void insertClientFootPrint(@RequestBody AccessLog accessLog, HttpServletRequest request) throws Exception{ 
		AccessLog dbAccessLog=accessLogService.findByIp(accessLog);
		LOG.info("return : insertClientFootPrint : {}", dbAccessLog);
		if(dbAccessLog==null){
			accessLogService.insertAccessLog(accessLog);
		}
	}
	
	@RequestMapping(path="/access/get/all", method=RequestMethod.POST)
	public Map<String, Integer> getTodayAndYesterday(@RequestBody AccessLog accessLog, HttpServletRequest request) throws Exception{ 
		Map<String, Integer> resultMap=new HashMap<>();
		Date date=new Date();
		accessLog.setDate(date);
		Integer today=accessLogService.getCountByDate(accessLog);
		
		accessLog.setDate(date);
		Integer yester=accessLogService.getCountByDate(accessLog);
		resultMap.put("today", today);
		resultMap.put("yester", yester);
		return resultMap;
	}
}
