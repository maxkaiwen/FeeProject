package com.example.demo.dto;

import javax.persistence.GeneratedValue;

import com.example.demo.entity.User;

import lombok.Data;


public class StudentDto {
	
	
	private Integer student_id;
	private String name;
	private Integer fee;
	private Integer feePaid;	
	private Integer user_id;//This is used to tell the program what user created this student and add that user into the student's db row	
	private UserDto userDto;//when sending information about a student out of the db this also gets the user who made this student's info.
	
	
	
	//Getters and setters
	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public Integer getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public Integer getFeePaid() {
		return feePaid;
	}

	public void setFeePaid(Integer feePaid) {
		this.feePaid = feePaid;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	
}
