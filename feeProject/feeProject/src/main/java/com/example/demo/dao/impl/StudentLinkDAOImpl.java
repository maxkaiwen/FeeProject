package com.example.demo.dao.impl;

import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.StudentLinkDAO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Student;
@Repository
@Transactional
public class StudentLinkDAOImpl implements StudentLinkDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void linkStudent(int perm, int perm_lv) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		
		
		Account account = session.get(Account.class, perm);
		Student student=session.get(Student.class, perm_lv);
		//user.getAccounts()
		Set<Student>students=account.getAccountStudents();
		Set<Account>accounts=student.getAccounts();
		students.add(student);
		accounts.add(account);
		student.setAccounts(accounts);
		account.setAccountStudents(students);
		
		session.save(student);
		session.save(account);
			}


	@Override
	public void deLinkStudent(int perm, int perm_lv) {
		// TODO Auto-generated method stub
Session session=sessionFactory.getCurrentSession();
		
		
		Account account = session.get(Account.class, perm);
		Student student=session.get(Student.class, perm_lv);
		//user.getAccounts()
		Set<Student>students=account.getAccountStudents();
		Set<Account>accounts=student.getAccounts();
		students.add(student);
		accounts.add(account);
		student.setAccounts(accounts);
		account.setAccountStudents(students);
		
		session.delete(student);
		session.delete(account);
	}
	
}
