package com.shun.blog.dao.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.shun.blog.dao.AbstractRepository;
import com.shun.blog.model.common.Paging;
import com.shun.blog.model.user.User;

@Repository
public class UserRepositoryImpl extends AbstractRepository<Long, User> implements UserRepository {
	static final Logger LOG = LoggerFactory.getLogger(UserRepositoryImpl.class);

	@Override
	public void insert(User user) {
		LOG.info("param : insert : {}",user.toString());
		persist(user);
	}
	
	@Override
	public User selectById(Long id) {
		LOG.info("param : selectById : {}", id);
		User user = getByLong(id);
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		LOG.info("return : selectById {}", user);
		return user;
	}
	
	@Override
	public User selectByEmail(String email) {
		LOG.info("param : selectByEmail : {}", email);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("email", email));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		LOG.info("return : selectByEmail {}", user);
		return user;
	}

	@Override
	public User selectByNickname(String nickname) {
		LOG.info("param : selectByNickname : {}", nickname);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("nickname", nickname));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		LOG.info("return : selectByNickname {}", user);
		return user;
	}

	// Criteria는 무엇인가?
	@Override
	@SuppressWarnings("unchecked")
	public List<User> selectList(Paging paging) {
		LOG.info("param : selectList : {}",paging.toString());
		int cPage = paging.getCurrentPage();
		int sType = paging.getSearchType();
		String sText = paging.getSearchText();
		int limit = paging.getLimit();

		Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"))
				.setFirstResult((cPage - 1) * limit)
				.setMaxResults(limit).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<User> users = (List<User>) criteria.list();

		// 검색 로직
		if (paging.getSearchType() != 0 && sType == 1) {
			criteria.add(Restrictions.like("email", "%" + sText + "%"));
		} else if (paging.getSearchType() != 0 && sType == 2) {
			criteria.add(Restrictions.like("nickname", "%" + sText + "%"));
		} 

		LOG.info("return : selectList {}", users);
		return users;
	}

	@Override
	public int getCount(Paging paging) {
		String condition = "";
		if (paging.getEntityName() != null) {
			condition = "WHERE state='" + paging.getEntityName() + "'";
		}
		Query query = rawQuery("SELECT COUNT(*) FROM TB_USER " + condition);
		return ((Number) query.uniqueResult()).intValue();
	}
	
	@Override
	public void deleteById(Long id) {
		User user = getByLong(id);
		//initialize에 대해서 이해하기.		
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		delete(user);
	}

	@Override
	public void deleteByEmail(String email) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("email", email));
		User user = (User) crit.uniqueResult();
		delete(user);
	}
}
