package com.example.demo.controller;

import java.util.List;

import org.hibernate.Session;
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
import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping(value = { "/student" })
public class StudentController {
	@Autowired
	StudentDAO studentDAO;

	@Autowired
	StudentService studentService;
/*
 * Student search by id. Accepts an integer in the address request. Returns a StudentDto object to the response entity.
 */
	@GetMapping(value = "/{student_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentDto> getStudentById(@PathVariable("student_id") int student_id) {
		System.out.println("Fetching Student with id " + student_id);
		StudentDto studentDto=studentService.getStudent(student_id);
		if (studentDto == null) {
			return new ResponseEntity<StudentDto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<StudentDto>(studentDto, HttpStatus.OK);
	}
/*
 * Student creating method. Uses studentDto to accept JSON and then 
 * uses StudentService class to format the data before persisting it with StudentDAOImpl within the Service. 
 * It will also set the user who has created this student, as long as that user's user_id is inside the JSON.
 */
	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> createStudent(@RequestBody StudentDto studentDto, UriComponentsBuilder ucBuilder) {
		 System.out.println("Creating student " + studentDto.getName());
		studentService.makeStudent(studentDto);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/student/{student_id}").buildAndExpand(studentDto.getStudent_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<Student> getAllStudents() {
		List<Student> tasks = studentDAO.getStudents();
		return tasks;

	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateStudent(@RequestBody Student currentStudent) {
		System.out.println("sd");
		Student student = studentDAO.findById(currentStudent.getStudent_id());
		if (student == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		studentDAO.update(currentStudent, currentStudent.getStudent_id());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{student_id}", headers = "Accept=application/json")
	public ResponseEntity<Student> deleteStudent(@PathVariable("student_id") int student_id) {
		Student student = studentDAO.findById(student_id);
		if (student == null) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		studentDAO.delete(student_id);
		return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
	}
}
