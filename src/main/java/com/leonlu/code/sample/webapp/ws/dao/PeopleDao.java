package com.leonlu.code.sample.webapp.ws.dao;

import com.leonlu.code.sample.webapp.ws.domain.People;

public interface PeopleDao extends GenericDao<People, Integer>{
	public People findByName(String name);
}
