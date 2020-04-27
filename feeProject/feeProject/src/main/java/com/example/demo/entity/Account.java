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
import javax.persistence.Table;
@Entity
@Table(name="account")
public class Account {
	
		@Id
		@GeneratedValue
		@Column(name="ID", unique=true,nullable =false)
		private Integer account_id;//Type of Account

		public Integer getAccount_id() {
			return account_id;
		}

		public void setAccount_id(Integer account_id) {
			this.account_id = account_id;
		}

		public Integer getPerm() {
			return perm;
		}

		public void setPerm(Integer perm) {
			this.perm = perm;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Set<Student> getAccountStudents() {
			return accountStudents;
		}

		public void setAccountStudents(Set<Student> accountStudents) {
			this.accountStudents = accountStudents;
		}

		private Integer perm;//Account Permission ID
		
		private String description;

		@ManyToMany(cascade=CascadeType.ALL)
	    @JoinTable(name="PERMISSION_STUDENT", joinColumns={@JoinColumn(referencedColumnName="PERM")}
	                                        , inverseJoinColumns={@JoinColumn(referencedColumnName="PERMMISSION_LV")})  
	    private Set<Student> accountStudents;
		public Integer getaccount_id() {
			return account_id;
		}

		public void setaccount_id(Integer account_id) {
			this.account_id = account_id;
		}

		public Integer getperm() {
			return perm;
		}

		public void setperm(Integer perm) {
			this.perm = perm;
		}

	


		
}
