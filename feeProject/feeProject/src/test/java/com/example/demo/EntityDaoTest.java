package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.configuration.HibernateConfiguration;
import com.example.demo.dao.UserDAO;
import com.example.demo.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = HibernateConfiguration.class)
public class EntityDaoTest {

	@Autowired
	private UserDAO userDAO;

	@Test
	public void testCreateUser() {
		User user = new User();
		user.setName("Ariv");
		user.setEmail("A");
		
		userDAO.addUser(user);
	}
}