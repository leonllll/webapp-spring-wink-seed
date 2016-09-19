package com.leonlu.code.sample.webapp.ws.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leonlu.code.sample.webapp.ws.common.WebAppException;
import com.leonlu.code.sample.webapp.ws.dao.PeopleDao;
import com.leonlu.code.sample.webapp.ws.domain.People;
import com.leonlu.code.sample.webapp.ws.service.PeopleService;

@Service
@Transactional
public class PeopleServiceImpl implements PeopleService {
	
	@Autowired
	private PeopleDao peopleDao;
	public static final Logger logger = LoggerFactory.getLogger(PeopleServiceImpl.class);

	@Override
	public void insert(People people) {
		try {
			peopleDao.insert(people);
		} catch(Exception e) {
			logger.error("failed-to-insert, [" + people + "]", e);
			throw new WebAppException("failed-to-insert", 500);
		}
	}

	@Override
	public People update(People people) {
		People targetPeople = this.get(people.getId());
		try {
			if(people.getName() != null) {
				targetPeople.setName(people.getName());
			}
			
			if(people.getAge() != null) {
				targetPeople.setAge(people.getAge());
			}
			
			return peopleDao.update(people);
		} catch(Exception e) {
			logger.error("failed-to-update, [" + people + "]", e);
			throw new WebAppException("failed-to-update", 500);
		}
	}

	@Override
	public People get(Integer id) {	
		People people;
		try {
			people = peopleDao.find(id);
		} catch(Exception e) {
			logger.error("failed-to-find, [" + id + "]", e);
			throw new WebAppException("failed-to-find", 500);
		}
		
		if(people == null) {
			throw new WebAppException("people-not-found", 404);
		} 
		return people;
	}

	@Override
	public void delete(Integer id) {
		People people = peopleDao.find(id);
		if(people == null) {
			throw new WebAppException("people-not-found", 404);
		} 
		try {
			peopleDao.delete(people);
		} catch(Exception e) {
			logger.error("failed-to-delete, [" + id + "]", e);
			throw new WebAppException("failed-to-delete", 500);
		}
		
	}

	@Override
	public List<People> getAll() {
		try {
			return peopleDao.findAll();
		} catch(Exception e) {
			logger.error("failed-to-find", e);
			throw new WebAppException("failed-to-find", 500);
		}
	}

}
