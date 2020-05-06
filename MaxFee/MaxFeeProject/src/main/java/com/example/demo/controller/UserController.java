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

import com.example.demo.dao.UserDAO;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = { "/user" })
public class UserController {
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	UserService userService;
	/*
	 * User search by id. Accepts an integer in the address request. Returns a UserDto object to the response entity. Does return password or user's Roles
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> getUserDtoById(@PathVariable("id") int id) {
		System.out.println("Fetching UserDto with id " + id);
		UserDto userDto = userService.getUser(id);
		if (userDto == null) {
			return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	/*
	 * User creating method. Uses userDto to accept JSON and then 
	 * uses UserService class to format the data before persisting it with UserDAOImpl within the Service. 
	 * It can also set up the user with 1 role if this is entered into the JSON as role_id.
	 */
	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> createUser(@RequestBody UserDto userDto, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating User " + userDto.getName());
		userService.makeUser(userDto);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(userDto.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<User> getAllUser() {
		List<User> tasks = userDAO.getUsers();
		return tasks;

	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateUser(@RequestBody User currentUser) {
		System.out.println("sd");
		User user = userDAO.findById(currentUser.getId());
		if (user == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		userDAO.update(currentUser, currentUser.getId());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
		User user = userDAO.findById(id);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		userDAO.delete(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

}