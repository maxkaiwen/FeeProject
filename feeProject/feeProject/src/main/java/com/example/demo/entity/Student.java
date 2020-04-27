package com.example.demo.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	@Id
	@GeneratedValue
	private Integer student_id;
	
	@Column(name="PERMISSION_LV")
	private Integer permission_lv;
	private String name;

	private Integer fee;
	
	private Integer feePaid;
	@ManyToMany(mappedBy="accountStudents")
	
	private Set<Account> accounts;
	
	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Integer getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}

	public Integer getPermission_lv() {
		return permission_lv;
	}

	public void setPermission_lv(Integer permission_lv) {
		this.permission_lv = permission_lv;
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
	
	
}
