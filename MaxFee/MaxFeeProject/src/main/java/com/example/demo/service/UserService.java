package com.example.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RoleDAO;
import com.example.demo.dao.StudentDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.dao.impl.RoleDAOImpl;
import com.example.demo.dao.impl.UserDAOImpl;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;

@Service
public class UserService {

	
	@Autowired
	private RoleDAO roleDAO;

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserDAOImpl userImpl;
	
	@Autowired
	private RoleDAOImpl roleImpl;
	/*
	 * This creates a user after the UserController gets the userDto from JSON. 
	 * This method takes userDto and sends each of its fields to a User Object called user1
	 * Once all these are copied over, the method uses roleDAO findById to get a role from the db and then adds it to a newly
	 * created Set of Role called roles. This is then placed inside user1 as User entities need this Set filled to make 
	 * the join table between User and Role work.
	 * Then userDAOImpl's addUser method is used to persist the user1 into the db.
	 * 
	 * Next another Set is created called users from role1.getUsers, this contains a list of users that are in that particular role.
	 * the new user is added to this Set which is added to role1 and then via roleImpl role1 is used to Update an already existing role in its table
	 *  
	 *
	 */
	public void makeUser(UserDto userDto) {
		User user1=new User();
		user1.setName(userDto.getName());
		user1.setEmail(userDto.getEmail());
		user1.setPassword(userDto.getPassword());
		
		Role role1=roleDAO.findById(userDto.getRole_id());	
		Set<Role> roles=new HashSet<Role>();
		roles.add(role1);	
		user1.setRoles(roles);
			
		userImpl.addUser(user1);
		
		Set<User> users=role1.getUsers();
		users.add(user1);
		role1.setUsers(users);
		roleImpl.update(role1, userDto.getRole_id());
	}
	/*
	 * This method reverses the makeUser method. It uses findById in the UserDAOImpl to get a User object from the db, then it makes a
	 * UserDto and copies over each field. Finally it returns the Dto which will be sent back to UserController.
	 *  This will display as JSON both the User and the user who created the user.
	 *  Note that it does not send back the password for security.
	 */
	public UserDto getUser(int user_id) {
		User user=userImpl.findById(user_id);
		UserDto userDto=new UserDto();	
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setId(user_id);
		
		return userDto;
		
		
	}
	
	
}
