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

import com.example.demo.dao.RoleDAO;
import com.example.demo.dto.RoleDto;
import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;

@RestController
@RequestMapping(value= {"/role"})
public class RoleController {
	/*
	 * Role Controller, unfinished
	 * 
	 */
	
	@Autowired
	RoleDAO roleDAO;
	
	@Autowired
	RoleService roleService;
	
	@GetMapping(value = "/{role_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Role> getRoleById(@PathVariable("role_id") int role_id) {
		System.out.println("Fetching Role with id " + role_id);
		Role role = roleDAO.findById(role_id);
		if (role == null) {
			return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}
	
	
	@PostMapping(value="/create",headers="Accept=application/json")
	public ResponseEntity<Void> createRole(@RequestBody RoleDto roleDto, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating role " + roleDto.getRole_name());
		
		roleService.addRole(roleDto);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/role/{role_id}").buildAndExpand(roleDto.getRole_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<Role> getAllRoles() {
		List<Role> tasks = roleDAO.getRoles();
		return tasks;

	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateRole(@RequestBody Role currentRole) {
		System.out.println("sd");
		Role role = roleDAO.findById(currentRole.getRole_id());
		if (role == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		roleDAO.update(currentRole, currentRole.getRole_id());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{role_id}", headers = "Accept=application/json")
	public ResponseEntity<Role> deleteRole(@PathVariable("role_id") int role_id) {
		Role role = roleDAO.findById(role_id);
		if (role == null) {
			return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
		}
		roleDAO.delete(role_id);
		return new ResponseEntity<Role>(HttpStatus.NO_CONTENT);
	}
}
