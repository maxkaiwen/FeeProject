
/**
 * 
 */
package com.example.demo.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	private String name;
	private String email;
	private String password;
	//This gives the user one or more roles.
	 @ManyToMany(fetch =FetchType.EAGER, cascade=CascadeType.ALL)
	    @JoinTable(name="USER_ROLE", joinColumns={@JoinColumn(referencedColumnName="ID")}
	                                        , inverseJoinColumns={@JoinColumn(referencedColumnName="ID")})  
	   private Set<Role> roles;
	//This gives the user one or more students that the user created inthe db.
	 @OneToMany(fetch=FetchType.EAGER , cascade=CascadeType.ALL)
	    @JoinColumn(name="USER_ID")
	    private Set<Student> students;

	 
	//Getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
