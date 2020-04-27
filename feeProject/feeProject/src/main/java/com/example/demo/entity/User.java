
/**
 * 
 */
package com.example.demo.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private String email;
	@JoinColumn
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="EMPLOYEE_ACCOUNT", joinColumns= { @JoinColumn (name="EMPLOYEE_ID",
	referencedColumnName="ID")}, inverseJoinColumns= { @JoinColumn (name="ACCOUNT_ID", referencedColumnName="ID")})
	private Set<Account> accounts;
	
	
	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
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

	public void setName(String Name) {
		this.name = Name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
