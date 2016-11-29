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

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	public User findById(int id) {
		User user = getByKey(id);
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public User findByEmail(String email) {
		logger.info("email : {}", email);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("email", email));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}
	
	public User findByNickname(String nickname) {
		logger.info("nickname : {}", nickname);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("nickname", nickname));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	//Criteria는 무엇인가?
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers(Paging paging) {
		int cPage = paging.getcPage();
		int sType = paging.getsType();
		String sText = paging.getsText();
		int limit = paging.getLimit();
		String entityName = paging.getEntityName();
		String pfName = paging.getPfName();
		
		// 검색 로직
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("id"));
		criteria.setFirstResult((cPage - 1) * limit);
		criteria.setMaxResults(limit);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		List<User> users = (List<User>) criteria.list();
		
		//클래스에 객체 명을 따라간다. 
		if (entityName!= null) {
			criteria.add(Restrictions.eq("entityName", entityName));
		}
		
		if (pfName != null) {
			criteria.add(Restrictions.eq("pfName", pfName));
		}
		
		if (paging.getsType() != 0 && sType == 1) {
			criteria.add(Restrictions.eq("email", sText));
		} else if (paging.getsType() != 0 && sType == 2) {
			criteria.add(Restrictions.eq("nickname", sText));
		} else if (paging.getsType() != 0 && sType == 3) {
			criteria.add(Restrictions.eq("name", sText));
		} 

		return users;
	}
	
	@Override
	public int getCount(Paging paging) {
		String condition = "";
		if (paging.getEntityName() != null) {
			condition = "WHERE state='" + paging.getEntityName()+ "'";
		}
		Query query = rawQuery("SELECT COUNT(*) FROM USER " + condition);
		return ((Number) query.uniqueResult()).intValue();
	}

	public void save(User user) {
		persist(user);
	}

	//Criteria는 무엇인가?
	public void deleteByEmail(String email) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("Email", email));
		User user = (User) crit.uniqueResult();
		delete(user);
	}


}
