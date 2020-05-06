package com.example.demo.dao.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.RoleDAO;
import com.example.demo.dto.RoleDto;
import com.example.demo.entity.Role;

@Repository
@Transactional
public class RoleDAOImpl implements RoleDAO{
	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * This method saves a role to the DB
	 */
	@Override
	public void addRole(Role role) {
		Session session = sessionFactory.getCurrentSession();
		session.save(role);
	}

	@Override
	public List<Role> getRoles() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Role> criteria = builder.createQuery(Role.class);
	    Root<Role> myObjectRoot = criteria.from(Role.class);
	    criteria.select(myObjectRoot);
		TypedQuery<Role> query = session.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public Role findById(int role_id) {
		Session session = sessionFactory.getCurrentSession();
		Role role = session.get(Role.class, role_id);
		return role;
	}

	@Override
	public Role update(Role role, int role_id) {
		Session session = sessionFactory.getCurrentSession();
		Role roleToUpdate = findById(role_id);
		roleToUpdate.setRole_name(role.getRole_name());
		roleToUpdate.setUsers(role.getUsers());
		session.update(roleToUpdate);
		return roleToUpdate;
	}
	
	

	@Override
	public void delete(int role_id) {
		Session session = sessionFactory.getCurrentSession();
		Role roleToDelete = findById(role_id);
		session.delete(roleToDelete);
	}

	
}
