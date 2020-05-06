package com.example.demo.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.StudentDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import com.example.demo.service.StudentService;

@Repository
@Transactional
public class StudentDAOImpl implements StudentDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	//This method creates a student, but most of the work is done in
	//StudentService that only calls this method to save it to the DB
	@Override
	public void createStudent(Student student1) {
		Session session = sessionFactory.getCurrentSession();
		session.save(student1);
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
	public List<String> findByIdAll(int student_id) {
		Student student=findById(student_id);
		User user=student.getUser();
		Session session = sessionFactory.getCurrentSession();		
		/*CriteriaQuery<Student> criteria=session.getCriteriaBuilder().createQuery(Student.class);
		criteria.
		List<Student> students=criteria.list();
		*/
		//testing purposes;
		System.out.println(student.getName());
		System.out.println(user.getName());
		List<String> ans = new LinkedList<String>();
		ans.add(student.getName());
		ans.add(user.getName());
		return ans;
	}

	@Override
	public Student update(Student student, int student_id) {
		Session session = sessionFactory.getCurrentSession();
		Student studentToUpdate = findById(student_id);
		studentToUpdate.setName(student.getName());
	//	studentToUpdate.setPermission_lv(student.getPermission_lv());
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
