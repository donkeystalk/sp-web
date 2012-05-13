package com.sp.repos;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateGenericRepository<T> implements GenericRepository<T> {

	protected HibernateTemplate hibernateTemplate;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	public void save(T entity) {
		hibernateTemplate.save(entity);
	}

	public void delete(T entity) {
		hibernateTemplate.delete(entity);
	}

}
