package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;

public interface AccountDAO {

	public void addAccount(Account account);

	public List<Account> getAccounts();

	public Account findById(int account_id);

	public Account update(Account account, int account_id);

	public void delete(int account_id);
}
