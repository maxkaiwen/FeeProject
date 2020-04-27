package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.dao.StudentDAO;
import com.example.demo.entity.Student;

@RestController
@RequestMapping(value= {"/student"})
public class StudentController {
	@Autowired
	StudentDAO studentService;
	
	@GetMapping(value = "/{student_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> getStudentById(@PathVariable("student_id") int student_id) {
		System.out.println("Fetching Student with id " + student_id);
		Student student = studentService.findById(student_id);
		if (student == null) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	
	@PostMapping(value="/create",headers="Accept=application/json")
	public ResponseEntity<Void> createStudent(@RequestBody Student student, UriComponentsBuilder ucBuilder) {
	//	System.out.println("Creating student " + student.getFirstName());
		studentService.addStudent(student);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/student/{student_id}").buildAndExpand(student.getStudent_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<Student> getAllStudents() {
		List<Student> tasks = studentService.getStudents();
		return tasks;

	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateStudent(@RequestBody Student currentStudent) {
		System.out.println("sd");
		Student student = studentService.findById(currentStudent.getStudent_id());
		if (student == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		studentService.update(currentStudent, currentStudent.getStudent_id());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{student_id}", headers = "Accept=application/json")
	public ResponseEntity<Student> deleteStudent(@PathVariable("student_id") int student_id) {
		Student student = studentService.findById(student_id);
		if (student == null) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		studentService.delete(student_id);
		return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
	}
}
