package com.example.demo.dao;

import java.util.List;

import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;

public interface StudentDAO {
	
	//public void addStudent(Student student);

	public List<Student> getStudents();

	public Student findById(int student_id);

	public Student update(Student student, int student_id);

	public void delete(int student_id);

	

	//public void createStudent(StudentDto studentDto);

	public void createStudent(Student student1);

	public List<String> findByIdAll(int student_id);

	//public void createStudent();

}
