package com.shun.blog.repository.log;

import com.shun.blog.model.log.AccessLog;

public interface AccessLogRepository {
	AccessLog findById(Long id) throws Exception;
	
	AccessLog findByIp(AccessLog accessLog) throws Exception;

	void insertAccessLog(AccessLog accessLog) throws Exception;
}
