/**
 * 
 */
package com.example.demo.dao;

import java.util.List;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;


public interface UserDAO {


	public List<User> getUsers();

//	public  User findById(int id) ;
		// TODO Auto-generated method stub
		
	

	public User update(User user, int id);

	public void delete(int id);

	

	//User findById(Class<User> entityType, Serializable id);

	User findById(int id);

	public void addUser(User user);


	
}
