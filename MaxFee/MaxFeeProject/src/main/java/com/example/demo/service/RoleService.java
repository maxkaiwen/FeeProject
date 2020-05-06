package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RoleDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.dao.impl.RoleDAOImpl;
import com.example.demo.dao.impl.UserDAOImpl;
import com.example.demo.dto.RoleDto;
import com.example.demo.entity.Role;

@Service
public class RoleService {
	@Autowired
	private RoleDAO roleDAO;

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private RoleDAOImpl roleImpl;
	
	
	//This method adds a role into the db after acquiring the information via RoleDto. 
	//From RoleDto the information is transferred below into a Role object(role1). role1 is then 
	//saved to the db via RoleDAOImpl's addRole method.
	public void addRole(RoleDto roleDto) {
		
		Role role1=new Role();
		role1.setRole_id(roleDto.getRole_id());
		role1.setRole_name(roleDto.getRole_name());
		
		roleImpl.addRole(role1);
	}
	public void linkUser() {
		
	}
}
