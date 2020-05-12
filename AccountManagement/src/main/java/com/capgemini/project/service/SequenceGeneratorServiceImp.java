package com.capgemini.project.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.project.dao.SequenceGeneratorDao;
import com.capgemini.project.entity.SequenceGenerator;
import com.capgemini.project.entity.Address;

@Service
@Transactional
public class SequenceGeneratorServiceImp implements SequenceGeneratorService {
	
	@Autowired
	private SequenceGeneratorDao generateAccount;
	
	int id=1;
	
	@Override
	public Long generateAccountNumber() {
		Optional<SequenceGenerator> optional=generateAccount.findById(id);
		SequenceGenerator account=new SequenceGenerator();
		long accountNumber;
		 if(optional.isPresent()) {
			 SequenceGenerator acNumber=optional.get();
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
		Optional<SequenceGenerator> optional=generateAccount.findById(id);
		SequenceGenerator account=new SequenceGenerator();
		int customerId;
		 if(optional.isPresent()) {
			 SequenceGenerator acNumber=optional.get();
	           int number=acNumber.getCustomerId();
	           customerId=++number;
	           account.setCustomerId(customerId);
	           generateAccount.save(account);
	           return customerId;
	        }else {
	        	customerId=111111;
	        	account.setCustomerId(customerId);
		        generateAccount.save(account);
		        return customerId;
	        }
	}

	@Override
	public int generateAddressId() {
		Optional<SequenceGenerator> optional=generateAccount.findById(id);
		SequenceGenerator account=new SequenceGenerator();
		int addressId;
		 if(optional.isPresent()) {
			 SequenceGenerator acNumber=optional.get();
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

	@Override
	public long generateTransId() {
		Optional<SequenceGenerator> optional=generateAccount.findById(id);
		SequenceGenerator account=new SequenceGenerator();
		long transId;
		 if(optional.isPresent()) {
			 SequenceGenerator acNumber=optional.get();
	           long number=acNumber.getTransId();
	           transId=++number;
	           account.setTransId(transId);
	           generateAccount.save(account);
	           return transId;
	        }else {
	        	transId=111111l;
	        	account.setTransId(transId);
		        generateAccount.save(account);
		        return transId;
	        }
		
	}

	@Override
	public long generateChequeId() {
		Optional<SequenceGenerator> optional=generateAccount.findById(id);
		SequenceGenerator account=new SequenceGenerator();
		long chequeId;
		 if(optional.isPresent()) {
			 SequenceGenerator acNumber=optional.get();
	           long number=acNumber.getChequeId();
	           chequeId=++number;
	           account.setChequeId(chequeId);
	           generateAccount.save(account);
	           return chequeId;
	        }else {
	        	chequeId=222222l;
	        	account.setChequeId(chequeId);
		        generateAccount.save(account);
		        return chequeId;
	}

}
}
