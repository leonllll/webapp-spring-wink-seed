package com.leonlu.code.sample.webapp.ws.resource;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.leonlu.code.sample.webapp.ws.domain.People;

@ContextConfiguration(locations = "classpath*:META-INF/spring/applicationContext*.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaTest {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		EntityManager em = entityManagerFactory.createEntityManager();
		People people = new People();
		people.setName("tester");
		people.setAge(22);
		em.getTransaction().begin();
		em.persist(people); 
		Assert.assertNotNull(em.find(People.class, people.getId()));
		em.getTransaction().commit();  
		em.close(); 
	}

}
