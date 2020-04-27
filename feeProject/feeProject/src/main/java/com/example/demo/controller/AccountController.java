package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.dao.AccountDAO;
import com.example.demo.entity.Account;


@RestController
@RequestMapping(value= {"/account"})
public class AccountController {
	@Autowired
	AccountDAO accountService;
	
	@GetMapping(value = "/{account_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> getAccountById(@PathVariable("account_id") int account_id) {
		System.out.println("Fetching Account with id " + account_id);
		Account account = accountService.findById(account_id);
		if (account == null) {
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}
	
	
	@PostMapping(value="/create",headers="Accept=application/json")
	public ResponseEntity<Void> createAccount(@RequestBody Account account, UriComponentsBuilder ucBuilder) {
	//	System.out.println("Creating account " + account.getFirstName());
		accountService.addAccount(account);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/account/{account_id}").buildAndExpand(account.getaccount_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<Account> getAllAccounts() {
		List<Account> tasks = accountService.getAccounts();
		return tasks;

	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateAccount(@RequestBody Account currentAccount) {
		System.out.println("sd");
		Account account = accountService.findById(currentAccount.getaccount_id());
		if (account == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		accountService.update(currentAccount, currentAccount.getaccount_id());
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{account_id}", headers = "Accept=application/json")
	public ResponseEntity<Account> deleteAccount(@PathVariable("account_id") int account_id) {
		Account account = accountService.findById(account_id);
		if (account == null) {
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
		accountService.delete(account_id);
		return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
	}

}
