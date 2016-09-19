package com.leonlu.code.sample.webapp.ws.resource;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leonlu.code.sample.webapp.ws.dao.PeopleDao;
import com.leonlu.code.sample.webapp.ws.domain.People;
import com.leonlu.code.sample.webapp.ws.service.PeopleService;

@ContextConfiguration(locations = "classpath*:META-INF/spring/applicationContext*.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PeolpleServiceTest {

	@Autowired
	private PeopleService peopleService;
	@Autowired
	private PeopleDao peopleDao;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void insertTest(){
		People insertedPeople = new People("xx", 32);
		peopleService.insert(insertedPeople);
		
		People updatedPeople = peopleDao.findByName("xx");
		updatedPeople.setName("xyz");
		peopleService.update(updatedPeople);
		
		People checkedPeople = peopleDao.find(updatedPeople.getId());
		Assert.assertEquals("xyz", checkedPeople.getName());
	}
	
	@Test
	public void deleteTest(){
		List<People> peopleList = peopleService.getAll();
		for(People item : peopleList) {
			System.out.println(item);
		}
		peopleService.delete(peopleList.get(0).getId());
		long postSize = peopleDao.countByCondition(null, null);
		Assert.assertEquals(peopleList.size(), postSize + 1);
	}

}
