package com.leonlu.code.sample.webapp.ws.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.leonlu.code.sample.webapp.ws.dao.PeopleDao;
import com.leonlu.code.sample.webapp.ws.domain.People;

@Repository
public class PeopleDaoImpl extends GenericDaoImpl<People, Integer> implements PeopleDao {

	@Override
	public People findByName(String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		List<People> resultList = findByProperties(params);
		if(resultList.size() > 0) {
			return resultList.get(0);
		}
		return null;
	}

}
