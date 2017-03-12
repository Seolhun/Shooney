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

import com.shun.blog.dao.AbstractDao;
import com.shun.blog.model.common.Paging;
import com.shun.blog.model.user.User;

@Repository("userRepository")
public class UserRepositoryImpl extends AbstractDao<Integer, User> implements UserRepository {
	static final Logger LOG = LoggerFactory.getLogger(UserRepositoryImpl.class);

	public User findById(int id) {
		User user = getByKey(id);
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		LOG.info("return : findById {}", user);
		return user;
	}

	public User findByEmail(String email) {
		LOG.info("email : {}", email);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("email", email));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		LOG.info("return : findByEmail {}", user);
		return user;
	}

	public User findByNickname(String nickname) {
		LOG.info("nickname : {}", nickname);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("nickname", nickname));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		LOG.info("return : findByNickname {}", user);
		return user;
	}

	// Criteria는 무엇인가?
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers(Paging paging) {
		Integer cPage = paging.getCPage();
		Integer sType = paging.getSType();
		String sText = paging.getSText();
		Integer limit = paging.getLimit();
		String entityName = paging.getEntityName();
		String pfName = paging.getPfName();

		// 검색 로직
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"))
				.setFirstResult((cPage - 1) * limit)
				.setMaxResults(limit).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<User> users = (List<User>) criteria.list();

		// 클래스에 객체 명을 따라간다.
		if (entityName != null) {
			criteria.add(Restrictions.eq("entityName", entityName));
		}

		if (pfName != null) {
			criteria.add(Restrictions.eq("pfName", pfName));
		}

		if (paging.getSType() != 0 && sType == 1) {
			criteria.add(Restrictions.like("email", "%" + sText + "%"));
		} else if (paging.getSType() != 0 && sType == 2) {
			criteria.add(Restrictions.like("nickname", "%" + sText + "%"));
		} 

		LOG.info("return : findAllUsers {}", users);
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

	public void save(User user) {
		persist(user);
	}

	// Criteria는 무엇인가?
	public void deleteByEmail(String email) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("email", email));
		User user = (User) crit.uniqueResult();
		delete(user);
	}

}
