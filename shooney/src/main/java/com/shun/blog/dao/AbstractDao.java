package com.shun.blog.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//세션에 있는 값으로 셀렉트
	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}
	
	//세션에 있는 값으로 셀렉트
	public T getByLong(Long id) {
		return (T) getSession().get(persistentClass, id);
	}

	//세션에서 Persistent값으로 만드는 것.
	public void persist(T entity) {
		getSession().persist(entity);
	}
	
	//세션에 있는 값으로 업데이트
	public void update(T entity) {
		getSession().update(entity);
	}
	
	//세션에 있는 값으로 지우기.
	public void delete(T entity) {
		getSession().delete(entity);
	}
	
	//세션 클리어
	public void clear() {
		getSession().clear();
	}
	
	//세션 갱신
	public void refresh(T entity) {
		getSession().refresh(entity);
	}
	
	//세션에 저장하는 것.
	public void save(T entity) {
		getSession().save(entity);
	}
	
	//세션에서 Detached상태로 만드는 것.
	public void flush() {
		getSession().flush();
	}
	
	public Query rawQuery(String raw) {
		Query query=getSession().createSQLQuery(raw);
		return query;
	}	

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}
}
