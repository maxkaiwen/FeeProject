package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class})
public class FeeApp{

	public static void main(String[] args) {
		SpringApplication.run(FeeApp.class, args);
	}
	
	/*
	 * Progam Explanation
	 * 
	 * Tables:
	 * 1.User : The user table has 3 columns, id, name and email. This is used to log users into the system. id is system generated, name and email are user entered.
	 * 2. AccountLink: This tables joins a User to one OR more accounts. It has a user_id and an account_id. 
	 * 3. Account: this table contains 3 columns. Id and Permission and Description. Id is used to associate the Account type with one or more users
	 *. Permission is used to show what items the Account has access to. Description is a String that says what type of account this is, such as "Admin" or "Accountant"
	 * 4. Student: This table  
	 * 
	 * 
	 * 
	 * 
	 */

}
