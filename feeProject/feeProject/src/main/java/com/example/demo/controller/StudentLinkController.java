package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import com.example.demo.dao.StudentLinkDAO;
import com.example.demo.entity.AccountLink;
import com.example.demo.entity.StudentLink;

@RestController
@RequestMapping(value= {"/linkStudent"})
public class StudentLinkController {
	

	@Autowired
	StudentLinkDAO linkService;
	
	@PostMapping(value="/link",headers="Accept=application/json")
	public ResponseEntity<String> linkAccount(@RequestBody StudentLink link, UriComponentsBuilder ucBuilder) {
	//	System.out.println("Creating account " + account.getFirstName());
		//accountService.addAccount(account);
		linkService.linkStudent(link.getPerm(), link.getPerm_lv());
		//HttpHeaders headers = new HttpHeaders();
	//	headers.setLocation(ucBuilder.path("/account/{account_id}").buildAndExpand(account.getaccount_id()).toUri());
		//return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	@DeleteMapping(value="/deLink",headers="Accept=application/json")
	public ResponseEntity<String> deLinkAccount(@RequestBody StudentLink link, UriComponentsBuilder ucBuilder) {
	//	System.out.println("Creating account " + account.getFirstName());
		//accountService.addAccount(account);
		linkService.deLinkStudent(link.getPerm(), link.getPerm_lv());
		//HttpHeaders headers = new HttpHeaders();
	//	headers.setLocation(ucBuilder.path("/account/{account_id}").buildAndExpand(account.getaccount_id()).toUri());
		//return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
