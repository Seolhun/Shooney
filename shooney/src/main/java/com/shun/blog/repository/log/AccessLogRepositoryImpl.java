package com.shun.blog.repository.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.shun.blog.model.log.AccessLog;
import com.shun.blog.repository.AbstractRepository;

@Repository
public class AccessLogRepositoryImpl extends AbstractRepository<Long, AccessLog> implements AccessLogRepository {
	static final Logger LOG = LoggerFactory.getLogger(AccessLogRepositoryImpl.class);
	
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
		Criteria criteria = createEntityCriteria();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date toDate=new Date();
		String toDateStr=format.format(toDate);
		Date fromDate=new Date();
		String fromDateStr=format.format(fromDate);
        
		criteria.add(Restrictions.eq("ip", accessLog.getIp()))
			.add(Restrictions.between("createdDate", toDateStr, fromDateStr));
		accessLog = (AccessLog)criteria.uniqueResult();
		return accessLog;
	}
	
	@Override
	public Integer getCountByDate(AccessLog accessLog) throws Exception {
		LOG.info("param : findAllByIp {}", accessLog.toString());
		String ip=accessLog.getIp(); 
		
		Criteria criteria = createEntityCriteria();
		criteria
			.add(Restrictions.eq("ip", ip))
			.add(Restrictions.like("createdDate",accessLog.getDate()))
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);		
		
		Integer totalCount = ((Number)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		return totalCount;
	}
}

