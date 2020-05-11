package com.capgemini.project.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.project.dao.AccountNumberGeneratorDao;
import com.capgemini.project.entity.AccountNumberGenerator;
import com.capgemini.project.entity.Address;

@Service
@Transactional
public class AccountNumberGeneratorServiceImp implements AccountNumberGeneratorService {
	
	@Autowired
	private AccountNumberGeneratorDao generateAccount;
	
	int id=1;
	
	@Override
	public Long generateAccountNumber() {
		Optional<AccountNumberGenerator> optional=generateAccount.findById(id);
		AccountNumberGenerator account=new AccountNumberGenerator();
		long accountNumber;
		 if(optional.isPresent()) {
			 AccountNumberGenerator acNumber=optional.get();
	           long number=acNumber.getAccountNumber();
	           accountNumber=++number;
	           account.setAccountNumber(accountNumber);
	           generateAccount.save(account);
	           return accountNumber;
	        }else {
	        	accountNumber=111111111111l;
	        	account.setAccountNumber(accountNumber);
		        generateAccount.save(account);
		        return accountNumber;
	        }
		
	}

	@Override
	public int generateCustomerId() {
		Optional<AccountNumberGenerator> optional=generateAccount.findById(id);
		AccountNumberGenerator account=new AccountNumberGenerator();
		int customerId;
		 if(optional.isPresent()) {
			 AccountNumberGenerator acNumber=optional.get();
	           int number=acNumber.getCustomerId();
	           customerId=++number;
	           account.setAccountNumber(customerId);
	           generateAccount.save(account);
	           return customerId;
	        }else {
	        	customerId=111111;
	        	account.setAccountNumber(customerId);
		        generateAccount.save(account);
		        return customerId;
	        }
	}

	@Override
	public int generateAddressId() {
		Optional<AccountNumberGenerator> optional=generateAccount.findById(id);
		AccountNumberGenerator account=new AccountNumberGenerator();
		int addressId;
		 if(optional.isPresent()) {
			 AccountNumberGenerator acNumber=optional.get();
	           int number=acNumber.getAddressId();
	           addressId=++number;
	           account.setAddressId(addressId);
	           generateAccount.save(account);
	           return addressId;
	        }else {
	        	addressId=111111;
	        	account.setAddressId(addressId);
		        generateAccount.save(account);
		        return addressId;
	        }
	}
	
	


}
