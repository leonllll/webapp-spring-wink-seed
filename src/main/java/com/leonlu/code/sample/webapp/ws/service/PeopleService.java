package com.leonlu.code.sample.webapp.ws.service;

import java.util.List;

import com.leonlu.code.sample.webapp.ws.domain.People;

public interface PeopleService {
	public void insert(People people);
	public People update(People people);
	public void delete(Integer id);
	public People get(Integer id);
	public List<People> getAll();
}
