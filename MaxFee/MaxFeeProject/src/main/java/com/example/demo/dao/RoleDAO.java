package com.example.demo.dao;

import java.util.List;
import java.util.Set;

import com.example.demo.dto.RoleDto;
import com.example.demo.entity.Role;


public interface RoleDAO {
	

	public List<Role> getRoles();

	public Role findById(int role_id);

	public Role update(Role role, int role_id);

	public void delete(int role_id);

	void addRole(Role role);

	
}
