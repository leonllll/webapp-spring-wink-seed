package com.leonlu.code.sample.webapp.ws.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.lang.reflect.ParameterizedType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.leonlu.code.sample.webapp.ws.dao.GenericDao;

@SuppressWarnings("unchecked")
public class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	protected Class<T> entityClass;
	
	public final Logger logger = LoggerFactory.getLogger(getClass());
	
	public GenericDaoImpl() {
		entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	@Override
	public void insert(T t) {
		entityManager.persist(t);
	}

	@Override
	public T update(T t) {
		return entityManager.merge(t); 
	}

	@Override
	public void delete(T t) {
		entityManager.remove(t);
	}

	@Override
	public T find(ID id) {
		return entityManager.find(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		return entityManager.createQuery("select e from " + entityClass.getSimpleName() + " e").getResultList();
	}

	@Override
	public List<T> findByJpql(String jpql, Object... params) {
		logger.debug("jpql: " + jpql);
		Query query = entityManager.createQuery(jpql, entityClass);
		int i = 1;
		for (Object param : params) {
			query.setParameter(i, param);
			i++;
		}
		return query.getResultList();
	}

	@Override
	public List<T> findByProperties(Map<String, Object> params) {
		StringBuffer stringBuffer = new StringBuffer(" select o from " + entityClass.getSimpleName() + " o where 1=1 ");
		if (params != null) {
			for (String property : params.keySet()) {
				stringBuffer.append(" and o." + property + "=:" + property);
			}
		}

		String jpql = stringBuffer.toString();
		logger.debug("jpql:" + jpql);
		Query query = entityManager.createQuery(jpql, entityClass);
		if (params != null) {
			for (Map.Entry<String, Object> param : params.entrySet()) {
				query.setParameter(param.getKey(), param.getValue());
			}
		}
		return query.getResultList();
	}

	@Override
	public List<T> findByProperties(Map<String, Object> params, Map<String, String> orders, int start, int limit) {
		StringBuilder jpql = new StringBuilder(" select o from " + entityClass.getSimpleName() + " o where 1=1 ");
		if (params != null) {
			for (String property : params.keySet()) {
				jpql.append(" and o." + property + "=:" + property);
			}
		}
		if (orders != null && orders.size() > 0) {
			jpql.append(" order by ");
			for (Map.Entry<String, String> entry : orders.entrySet()) {
				jpql.append("o." + entry.getKey() + " " + entry.getValue() + ",");
			}
			jpql.deleteCharAt(jpql.length() - 1);
		}

		logger.debug("jpql:" + jpql.toString());
		Query query = entityManager.createQuery(jpql.toString(), entityClass);
		if (params != null) {
			for (Map.Entry<String, Object> param : params.entrySet()) {
				query.setParameter(param.getKey(), param.getValue());
			}
		}
		query.setFirstResult(start);
		query.setMaxResults(limit);
		return query.getResultList();
	}

	@Override
	public Long countByCondition(List<Map<String, Object>> paramsList, List<String> conditionList) {
		StringBuffer stringBuffer = new StringBuffer(" select count(o) from " + entityClass.getSimpleName() + " o where 1=1 ");
		if(paramsList != null && conditionList != null) {
			for (int i = 0; i < conditionList.size(); i++) {
				Map<String, Object> params = paramsList.get(i);
				for (String property : params.keySet()) {
					stringBuffer.append(" and o." + property + conditionList.get(i) + ":" + property);
				}
			}
		}
		String jpql = stringBuffer.toString();
		logger.debug("jpql:" + jpql);
		Query query = entityManager.createQuery(jpql, entityClass);
		if(paramsList !=null && conditionList != null) {
			for (int i = 0; i < paramsList.size(); i++) {
				Map<String, Object> params = paramsList.get(i);
				for (Map.Entry<String, Object> param : params.entrySet()) {
					query.setParameter(param.getKey(), param.getValue());
				}
			}
		}
		return (Long) query.getSingleResult();

	}
}
