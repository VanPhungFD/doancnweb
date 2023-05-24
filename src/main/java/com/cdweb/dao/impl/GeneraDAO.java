package com.cdweb.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cdweb.dao.IGeneralDAO;

@Transactional
public class GeneraDAO<L, K> implements IGeneralDAO<L, K> {

	@Autowired
	protected SessionFactory factory;

	@Override
	public L create(L entity) {
		Session session = factory.getCurrentSession();
		session.save(entity);
		return entity;
	}

	@Override
	public void update(L entity) {
		Session session = factory.getCurrentSession();
		session.update(entity);
	}

	@Override
	public void delete(@SuppressWarnings("unchecked") K... ids) {
		Session session = factory.getCurrentSession();
		for (K id : ids) {
			L entity = this.findById(id);
			session.delete(entity);
		}
	}


	@Override
	public L findById(K id) {
		Session session = factory.getCurrentSession();
		Class<L> entityClass = this.getEntityClass();
		L entity = session.find(entityClass, id);
		return entity;
	}


	@SuppressWarnings({ "unchecked" })
	private Class<L> getEntityClass() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		return  (Class<L>) type.getActualTypeArguments()[0];
	}

	@Override
	public List<L> findAll() {
		Session session = factory.getCurrentSession();
		String hql = "FROM " + this.getEntityClass().getSimpleName();
		TypedQuery<L> query = session.createQuery(hql, this.getEntityClass());
		List<L> entity = query.getResultList();
		return entity;
	}

	@Override
	public <E> List<E> getResultList(String hql, Object...mangParam) {			
		return this.getResultPageOrPagram(hql,0,0,mangParam);
	}
	
	@Override
	public <E> List<E> getResultListParam(String hql, Object...mangParam) {	
		return this.getResultPageOrPagram(hql, 0, 0, mangParam);
	}

	@Override
	public <E> List<E> getResultPageOrPagram(String hql, int page, int size, Object...mangParam) {
		Session session = factory.getCurrentSession();
		@SuppressWarnings("unchecked")
		TypedQuery<E> query =  session.createQuery(hql);
		// size > 0 mới phân trang
		if(size >0)
		{
			query.setFirstResult(page*size);
			query.setMaxResults(size);
		}
		for(int i=0;i<mangParam.length;i++)
		{
			query.setParameter(i, mangParam[i]);
		}
		List<E> list = query.getResultList();
		return list;
	}



}
