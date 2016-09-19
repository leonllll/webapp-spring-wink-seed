package com.leonlu.code.sample.webapp.ws.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDao<T, ID extends Serializable> {
	public void insert(T t);

	public T update(T t);

	public void delete(T t);

	public T find(ID id);

	public List<T> findAll();

	public List<T> findByJpql(String jpql, Object... params);

	public List<T> findByProperties(Map<String, Object> params);

	public List<T> findByProperties(Map<String, Object> params, Map<String, String> orders, int start,
			int limit);
	
	public Long countByCondition(List<Map<String, Object>> paramsList, List<String> conditionList);
}
