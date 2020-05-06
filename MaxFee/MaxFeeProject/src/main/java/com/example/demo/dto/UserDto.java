package com.example.demo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;



public class UserDto {
		
		private Integer id;
		private String name;
		private String email;
		private String password;
		private Integer role_id;//This sets the initial Role of the user.
		
		
		
		
		//Getters and setters
		public Integer getRole_id() {
			return role_id;
		}

		public void setRole_id(Integer role_id) {
			this.role_id = role_id;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
}
