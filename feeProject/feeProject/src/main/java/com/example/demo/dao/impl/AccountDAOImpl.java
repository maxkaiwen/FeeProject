package com.example.demo.dao.impl;

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

import com.example.demo.dao.AccountDAO;
import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.entity.Account;

@Repository
@Transactional
public class AccountDAOImpl implements AccountDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void addAccount(Account account) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(account);
	}
	@Override
	public List<Account> getAccounts() {
		
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Account> criteria = builder.createQuery(Account.class);
		 Root<Account> myObjectRoot = criteria.from(Account.class);
		    criteria.select(myObjectRoot);
			TypedQuery<Account> query = session.createQuery(criteria);
			return query.getResultList();
		// TODO Auto-generated method stub
		
	}
	@Override
	public Account findById(int account_id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Account account = session.get(Account.class, account_id);
		
		return account;
		
		
		
	}
	@Override
	public Account update(Account account, int account_id) {
		// TODO Auto-generated method stub
		
		
		Session session = sessionFactory.getCurrentSession();
		Account accountToUpdate = findById(account_id);
		accountToUpdate.setaccount_id(account.getaccount_id());
		
		session.update(accountToUpdate);
		return accountToUpdate;
		
	}
	@Override
	public void delete(int account_id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Account account = findById(account_id);
		session.delete(account);
	}

}
