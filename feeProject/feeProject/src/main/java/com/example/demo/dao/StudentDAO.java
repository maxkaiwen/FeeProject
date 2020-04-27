package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Student;

public interface StudentDAO {
	
	public void addStudent(Student student);

	public List<Student> getStudents();

	public Student findById(int student_id);

	public Student update(Student student, int student_id);

	public void delete(int student_id);

}
