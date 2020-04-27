package com.example.demo.dao.impl;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.demo.dao.AccountLinkDAO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;

@Repository
@Transactional
public class AccountLinkDAOImpl implements AccountLinkDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void linkAccount(int user_id,int account_id) {
		Session session=sessionFactory.getCurrentSession();
		
		User user = session.get(User.class, user_id);
		Account account = session.get(Account.class, account_id);
		//user.getAccounts()
		Set<Account>accounts=user.getAccounts();
		accounts.add(account);
		user.setAccounts(accounts);
		session.save(user);
		
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deLinkAccount(int id, int account_id) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, id);
		Account account = session.get(Account.class, account_id);
		Set<Account>accounts=user.getAccounts();
		accounts.add(account);
		user.setAccounts(accounts);
		session.delete(account);
	}


	

}
