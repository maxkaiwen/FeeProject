package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;

public interface AccountLinkDAO {
	

	

//	public void linkAccount(int id);

	public void linkAccount(int id, int account_id);
	public void deLinkAccount(int id,int account_id);
	
	
}
