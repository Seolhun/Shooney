package com.shun.blog.service.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.model.log.AccessLog;
import com.shun.blog.repository.log.AccessLogRepository;

@Service
@Transactional(transactionManager="txManager")
public class AccessLogServiceImpl implements AccessLogService {

	@Autowired
	private AccessLogRepository accessLogRepository;

	@Override
	public AccessLog findById(Long id) throws Exception {
		return accessLogRepository.findById(id);
	}
	
	@Override
	public AccessLog findByIp(AccessLog accessLog) throws Exception {
		return accessLogRepository.findByIp(accessLog);
	}

	@Override
	public void insertAccessLog(AccessLog accessLog) throws Exception {
		accessLogRepository.insertAccessLog(accessLog);
	}

}
