package com.capgemini.project.service;

import java.util.List;

import javax.security.auth.login.AccountException;

import com.capgemini.project.entity.Account;
import com.capgemini.project.entity.Address;
import com.capgemini.project.entity.Customer;

public interface AccountManagementService {
	
	
	public String addAccount(Account account,Customer customer,Address address) throws AccountException;
	
	public boolean deleteAccount(Account account) throws AccountException;
	
	public boolean updateAccountAddress(Account account, Address address) throws AccountException;
	
	public boolean updateName(Account account , Customer customer) throws AccountException;
	
	public boolean updateContact(Account account , Customer customer) throws AccountException;
	
	public List<Account> showAllAccount() throws AccountException;
	
	public Account fetchAccountByAccountId(String accountId) throws AccountException;
	
	public Customer fetchCustomerByCustomerId(String customerId) throws AccountException;
	
	public Address fetchAddressByAddressId(String addressId) throws AccountException;
}
