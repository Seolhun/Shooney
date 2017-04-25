package com.shun.blog.repository.log;

import java.sql.Timestamp;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shun.blog.model.log.AccessLog;
import com.shun.blog.repository.AbstractRepository;
import com.shun.blog.service.common.CommonService;

@Repository
public class AccessLogRepositoryImpl extends AbstractRepository<Long, AccessLog> implements AccessLogRepository {
	static final Logger LOG = LoggerFactory.getLogger(AccessLogRepositoryImpl.class);
	
	@Autowired
	private CommonService commonService;
	
	@Override
	public void insertAccessLog(AccessLog accessLog) throws Exception {
		persist(accessLog);
	}
	
	@Override
	public AccessLog findById(Long id) throws Exception {
		AccessLog accessLog = getByLong(id);
		return accessLog;
	}
	
	@Override
	public AccessLog findByIp(AccessLog accessLog) throws Exception {
		LOG.info("param : findByIp : {}", accessLog);
		
		Timestamp fromTime=commonService.convertDateToday("yyyy-MM-dd");
		Timestamp toTime=commonService.convertDateFormat("yyyy-MM-dd", 1);
		
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("ip", accessLog.getIp()))
			.add(Restrictions.between("createdDate", fromTime, toTime));
		
		accessLog = (AccessLog)criteria.uniqueResult();
		return accessLog;
	}
	
	@Override
	public Integer getCountByDate(AccessLog accessLog) throws Exception {
		LOG.info("param : findAllByIp {}", accessLog.toString());
		Timestamp fromTime=commonService.convertDateToday("yyyy-MM-dd");
		Timestamp toTime=commonService.convertDateFormat("yyyy-MM-dd", accessLog.getCalculator());
		
		Criteria criteria = createEntityCriteria();
		
		Integer type=accessLog.getType();
		//YesterDay
		if(type==1){
			criteria.add(Restrictions.between("createdDate", toTime, fromTime));	
		} 
		//Today
		else if(type==2){
			criteria.add(Restrictions.between("createdDate", fromTime, toTime));
		}
		
		Integer count = ((Number)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		return count;
	}
}

