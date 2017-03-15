package com.shun.blog.dao.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.shun.blog.dao.AbstractDao;
import com.shun.blog.model.user.UserProfile;

@Repository("userProfileDao")
public class UserProfileRepositoryImpl extends AbstractDao<Integer, UserProfile> implements UserProfileRepository {

	@Override
	public UserProfile selectById(int id) {
		return getByKey(id);
	}

	@Override
	public UserProfile selectByType(String type) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("type", type));
		return (UserProfile) crit.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserProfile> selectList() {
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.desc("id"));
		return (List<UserProfile>) crit.list();
	}

}
