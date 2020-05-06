/**
 * 
 */
package com.example.demo.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.UserDAO;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import com.example.demo.service.StudentService;
import com.example.demo.service.UserService;


@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		
	
		session.save(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.dao.UserDAO#getUsers()
	 */
	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
	    Root<User> myObjectRoot = criteria.from(User.class);
	    criteria.select(myObjectRoot);
		TypedQuery<User> query = session.createQuery(criteria);
		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.dao.UserDAO#findById(int)
	 */
	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		System.out.println("Start");
		Session session = sessionFactory.getCurrentSession();
		 User user = session.get(User.class, id);
		return user;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.dao.UserDAO#update(com.example.demo.entity.User, int)
	 */
	@Override
	public User update(User user, int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		User userToUpdate = findById(id);
		userToUpdate.setEmail(user.getEmail());
		userToUpdate.setName(user.getName());
		session.update(userToUpdate);
		return userToUpdate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.dao.UserDAO#delete(int)
	 */
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		User userToDelete = findById(id);
		session.delete(userToDelete);
	}

}
