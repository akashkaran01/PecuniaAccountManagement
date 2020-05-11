package com.capgemini.project.service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.project.dao.AccountEntityDao;
import com.capgemini.project.dao.AddressEntityDao;
import com.capgemini.project.dao.CustomerEntityDao;
import com.capgemini.project.entity.Account;
import com.capgemini.project.entity.Address;
import com.capgemini.project.entity.Customer;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
@Transactional
public class AccountManagementServiceImp implements AccountManagementService {
	
	@Autowired
	private AccountEntityDao accountEntityDao;
	
	@Autowired
	private CustomerEntityDao customerEntityDao;
	
	@Autowired
	private AddressEntityDao addressEntityDao;

	@Override
	public String addAccount(Account account, Customer customer, Address address) throws AccountException{
		if(accountEntityDao.save(account).equals(account) && customerEntityDao.save(customer).equals(customer) &&
		addressEntityDao.save(address).equals(address)) {
			return "Account Created Successfully :)" ;
		}
		throw new AccountException("Something went gone wrong");
	}

	@Override
	public boolean deleteAccount(Account account) throws AccountException{
		if(accountEntityDao.save(account).equals(account)) {
			return true;
		}
		throw new AccountException("No Such Account Exists In Database");
	}

	@Override
	public boolean updateAccountAddress(Account account, Address address) throws AccountException{
		if(accountEntityDao.save(account).equals(account) && addressEntityDao.save(address).equals(address)) {
			return true;
		}
		throw new AccountException("No Such Account Exists In Database");
	}

	@Override
	public boolean updateName(Account account, Customer customer)throws AccountException {
		if(accountEntityDao.save(account).equals(account) && customerEntityDao.save(customer).equals(customer)) {
			return true;
		}
		throw new AccountException("No Such Account Exists In Database");

	}

	@Override
	public boolean updateContact(Account account, Customer customer)throws AccountException {
		if(accountEntityDao.save(account).equals(account) && customerEntityDao.save(customer).equals(customer)) {
			return true;
		}
		throw new AccountException("No Such Account Exists In Database");
	}

	@Override
	public List<Account> showAllAccount() throws AccountException{
		
		List<Account> accounts=accountEntityDao.findAll();
		
		return accounts;
	}

	@Override  
	public Account fetchAccountByAccountId(String accountId) throws AccountException {
		
		Optional<Account> optional=accountEntityDao.findById(accountId);
		 if(optional.isPresent()) {
	            Account account=optional.get();
	            return account;
	        }
	        throw new AccountException("Account not found for id="+accountId);
	}

	@Override
	public Customer fetchCustomerByCustomerId(String customerId) throws AccountException {
		
		Optional<Customer> optional=customerEntityDao.findById(customerId);
		 if(optional.isPresent()) {
			 Customer  customer=optional.get();
	            return customer;
	        }
	        throw new AccountException("customer not found for id="+customerId);
	}

	@Override
	public Address fetchAddressByAddressId(String addressId) throws AccountException {
		
		Optional<Address> optional=addressEntityDao.findById(addressId);
		 if(optional.isPresent()) {
			 Address address=optional.get();
	            return address;
	        }
	        throw new AccountException("customer not found for id="+addressId);
	}
	
	

}
