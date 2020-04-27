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

import com.example.demo.dao.StudentDAO;
import com.example.demo.entity.Student;

@Repository
@Transactional
public class StudentDAOImpl implements StudentDAO {

	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.save(student);
	}

	@Override
	public List<Student> getStudents() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Student> criteria = builder.createQuery(Student.class);
	    Root<Student> myObjectRoot = criteria.from(Student.class);
	    criteria.select(myObjectRoot);
		TypedQuery<Student> query = session.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public Student findById(int student_id) {
		Session session = sessionFactory.getCurrentSession();
		Student student = session.get(Student.class, student_id);
		return student;
	}

	@Override
	public Student update(Student student, int student_id) {
		Session session = sessionFactory.getCurrentSession();
		Student studentToUpdate = findById(student_id);
		studentToUpdate.setName(student.getName());
		studentToUpdate.setPermission_lv(student.getPermission_lv());
		studentToUpdate.setFeePaid(student.getFeePaid());
		studentToUpdate.setFee(student.getFee());
		//studentToUpdate.set
		session.update(studentToUpdate);
		return studentToUpdate;
	}

	@Override
	public void delete(int student_id) {
		Session session = sessionFactory.getCurrentSession();
		Student studentToDelete = findById(student_id);
		session.delete(studentToDelete);
	}

}
