package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.dao.AccountDAO;
import com.example.demo.dao.AccountLinkDAO;
import com.example.demo.entity.Account;
import com.example.demo.entity.AccountLink;

@RestController
@RequestMapping(value= {"/linkService"})
public class AccountLinkController {
	
	@Autowired
	AccountLinkDAO linkService;

	
	@PostMapping(value="/link",headers="Accept=application/json")
	public ResponseEntity<String> linkAccount(@RequestBody AccountLink link, UriComponentsBuilder ucBuilder) {
	//	System.out.println("Creating account " + account.getFirstName());
		//accountService.addAccount(account);
		linkService.linkAccount(link.getUser_id(), link.getAccount_id());
		//HttpHeaders headers = new HttpHeaders();
	//	headers.setLocation(ucBuilder.path("/account/{account_id}").buildAndExpand(account.getaccount_id()).toUri());
		//return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	@DeleteMapping(value="/deLink",headers="Accept=application/json")
	public ResponseEntity<String> deleteLink(@RequestBody AccountLink link, UriComponentsBuilder ucBuilder) {
	//	System.out.println("Creating account " + account.getFirstName());
		//accountService.addAccount(account);
		linkService.deLinkAccount(link.getUser_id(), link.getAccount_id());
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
