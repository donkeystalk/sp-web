package com.sp.repos;

public interface GenericRepository<T> {
	public void save(T entity);
	public void delete(T entity);
}
