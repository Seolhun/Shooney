package com.shun.blog.service.log;

import com.shun.blog.model.log.AccessLog;

public interface AccessLogService {
	AccessLog findById(Long id) throws Exception;
	
	AccessLog findByIp(AccessLog accessLog) throws Exception;

	void insertAccessLog(AccessLog accessLog) throws Exception;
	
	Integer getCountByDate(AccessLog accessLog) throws Exception;
}