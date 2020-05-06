package com.example.demo.service;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.StudentDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.dao.impl.StudentDAOImpl;
import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;

@Service
public class StudentService {
	
	@Autowired
	private StudentDAOImpl studentImpl;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserService userService;
	/*
	 * This creates a student after the StudentController gets the studentDto from JSON. 
	 * This method takes studentDto and sends each of its fields to a Student Object called student1
	 * Once all these are copied over, the method uses UserDAO findById and a User Object to find an already existing user in the db
	 * and then sets it as student1's user. Then studentDAOImpl's createStudent method is used to persist the student1 into the db.
	 *
	 */
	public void makeStudent(StudentDto studentDto) {
		Student student1=new Student();
		student1.setName(studentDto.getName());
		student1.setFee(studentDto.getFee());
		student1.setFeePaid(studentDto.getFeePaid());
		System.out.println(studentDto.getUser_id());
		User user=userDAO.findById(studentDto.getUser_id());
		student1.setUser(user);
			
		studentImpl.createStudent(student1);
		
	}
	/*
	 * This method reverses the makeStudent method. It uses findById in the StudentDAOImpl to get a Student object from the db, then it makes a
	 * StudentDto and copies over each field. Finally it grabs the user associated with this student and adds it to the studentDto then returns the Dto
	 * which will be sent back to StudentController. This will display as JSON both the Student and the user who created the student.
	 */
	public StudentDto getStudent(int student_id) {
		Student student1=studentImpl.findById(student_id);
		StudentDto studentDto=new StudentDto();
		studentDto.setStudent_id(student_id);
		studentDto.setName(student1.getName());
		studentDto.setFee(student1.getFee());
		studentDto.setFeePaid(student1.getFeePaid());
		studentDto.setUserDto(userService.getUser(student1.getUser().getId()));
		
		return studentDto;
		
	}
}
