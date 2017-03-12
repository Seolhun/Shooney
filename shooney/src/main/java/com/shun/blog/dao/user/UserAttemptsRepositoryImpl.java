package com.shun.blog.dao.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.shun.blog.dao.AbstractDao;
import com.shun.blog.model.user.UserAttempts;

@Repository("userAttemptsRepository")
public class UserAttemptsRepositoryImpl extends AbstractDao<Integer, UserAttempts> implements UserAttemptsRepository {
	static final Logger log = LoggerFactory.getLogger(UserAttemptsRepositoryImpl.class);
	
	@Override
	public void insert(UserAttempts userAttempts) {
		persist(userAttempts);
	}
	
	@Override
	public UserAttempts selectById(Long id) {
		return getByLong(id);
	}
	
	@Override
	public UserAttempts selectByEmail(String email) {
		log.info("Parameter : {}", email);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userAttemptsEmail", email))
			.addOrder(Order.desc("userAttemptsCreatedDate"))
			.setMaxResults(1);
		UserAttempts userAttempts = (UserAttempts)crit.uniqueResult();
		return userAttempts;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserAttempts> selectList(UserAttempts userAttempts) {
		log.info("param : "+userAttempts.toString());
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("userAttemptsId")).add(Restrictions.eq("userAttemptsDelCheck", 0)).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<UserAttempts> attempts = criteria.list();
		
		log.info("return : "+attempts.toString());
		return attempts;
	}
	
	@Override
	public boolean delete(Long id) {
		return delete(id);
	}

}
