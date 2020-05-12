package com.capgemini.project.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.AccountException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.project.entity.Account;
import com.capgemini.project.entity.Address;
import com.capgemini.project.entity.Customer;
import com.capgemini.project.service.AccountManagementService;
import com.capgemini.project.service.AccountManagementServiceImp;
import com.capgemini.project.service.SequenceGeneratorService;

@RestController
public class AccountManagementController {

	@Autowired
	private AccountManagementService accountServivce;
	
	@Autowired
	private SequenceGeneratorService numberGeneratorService;
	
	@PostMapping("/addAccount")
	public ResponseEntity<String> addAccount( @RequestBody Map<String ,Object> requestMap){
		Map<String ,Object> map=requestMap;
		
	
		
		// Code for generating accoutNumber,customerId,addressId
		String generatedAccountNumber=String.valueOf(numberGeneratorService.generateAccountNumber());
		String genereatedAccountHolderId=String.valueOf(numberGeneratorService.generateCustomerId());
		String generatedAddressId=String.valueOf(numberGeneratorService.generateAddressId());
		
		// Account class level member info
		String accountNumber=(String)map.get(generatedAccountNumber);
		String accountHolderId=(String)map.get("accountHolderId");
		double accountBalance=(double)map.get("accountBalance");
		String branchId=(String)map.get("branchId");
		String accountType=(String)map.get("accountType");
		String accountStatus=(String)map.get("accountStatus");
		double accountInterest=(double)map.get("accountInterest");
		Date lastUpdate=(Date)map.get("lastUpdate");
		Account account=new Account(accountNumber, accountHolderId, accountStatus, accountBalance, branchId, accountType, accountInterest, lastUpdate);
		
		// Customer class level member info
		String customerId=(String)map.get(genereatedAccountHolderId);
		String customerName=(String)map.get("customerName");
		String customerAddres=(String)map.get("customerAddres");
		Date customerDob=(Date)map.get("customerDob");
		String customerGender=(String)map.get("customerGender");
		String customerContactNumber=(String)map.get("customerContactNumber");
		String customerPanNumber=(String)map.get("customerPanNumber");
		String customerAadharNumber=(String)map.get("customerAadharNumber");
		Customer customer =new Customer(customerId, customerAddres, customerName, customerDob, customerGender, customerContactNumber, customerPanNumber, customerAadharNumber);
		
		// Address class level member info
		String addressId=(String)map.get(generatedAddressId);
		String addressLine=(String)map.get("addressLine");
		String city=(String)map.get("city");
		String state=(String)map.get("state");
		String country=(String)map.get("country");
		String zipcode=(String)map.get("zipcode");
		Address address= new Address(addressId, addressLine, city, state, country, zipcode);
		
		String msg="";
		try {
			msg = accountServivce.addAccount(account, customer, address);
		} catch (AccountException e) {
			System.out.println(e);
		}
		
		ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.OK);
		
		return response;
	}
	
	@PutMapping("/updateName")
	public ResponseEntity<String> updateName(@RequestBody Map<String ,Object> requestMap){
		Map<String ,Object> map=requestMap;
		String msg="";
		
		String accountNumber=(String)map.get("accountNumber");
		String newCustomerName=(String)map.get("newCustomerName");
		
		try {
			Account account=accountServivce.fetchAccountByAccountId(accountNumber);
			String customerId=account.getAccountHolderId();
			Customer customer=accountServivce.fetchCustomerByCustomerId(customerId);
			customer.setCustomerName(newCustomerName);
			boolean isTrue=accountServivce.updateName(account, customer);
			if(isTrue) {
				msg="Account Holder Name Updated Successfully ";
			}else {
				msg="No such Account found with Id"+accountNumber;
			}
		} catch (AccountException e) {
			
			e.printStackTrace();
		}
		
		ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.OK);
		
		return response;
	}
	
	@PutMapping("/updateContact")
	public ResponseEntity<String> updateContact(@RequestBody Map<String ,Object> requestMap){
		Map<String ,Object> map=requestMap;
		String msg="";
		
		String accountNumber=(String)map.get("accountNumber");
		String newCustomerContact=(String)map.get("newCustomerContact");
		
		try {
			Account account=accountServivce.fetchAccountByAccountId(accountNumber);
			String customerId=account.getAccountHolderId();
			Customer customer=accountServivce.fetchCustomerByCustomerId(customerId);
			customer.setContactNumber(newCustomerContact);
			boolean isTrue=accountServivce.updateContact(account, customer);
			if(isTrue) {
				msg="Account Holder Contact Number Updated Successfully ";
			}else {
				msg="No such Account found with Id"+accountNumber;
			}
		} catch (AccountException e) {
			
			e.printStackTrace();
		}
		
		ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.OK);
		
		return response;
	}
	
	@PutMapping("/updateAddress")
	public ResponseEntity<String> updateAddress(@RequestBody Map<String ,Object> requestMap){
		Map<String ,Object> map=requestMap;
		String msg="";
		
		String accountNumber=(String)map.get("accountNumber");
		String addressLine=(String)map.get("addressLine");
		String city=(String)map.get("city");
		String state=(String)map.get("state");
		String country=(String)map.get("country");
		String zipcode=(String)map.get("zipcode");
		
		
		try {
			Account account=accountServivce.fetchAccountByAccountId(accountNumber);
			String customerId=account.getAccountHolderId();
			Customer customer=accountServivce.fetchCustomerByCustomerId(customerId);
			String addressId=customer.getAddress();
			Address address= accountServivce.fetchAddressByAddressId(addressId);
			address.setAddressLine(addressLine);
			address.setCity(city);
			address.setState(state);
			address.setCountry(country);
			address.setZipCode(zipcode);
			boolean isTrue=accountServivce.updateAccountAddress(account, address);
			if(isTrue) {
				msg="Account Holder Address Updated Successfully ";
			}else {
				msg="No such Account found with Id"+accountNumber;
			}
		} catch (AccountException e) {
			
			e.printStackTrace();
		}
		
		ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.OK);
		
		return response;
	}
	
	@GetMapping("/accounts")
	public ResponseEntity<List<Account>> showAllAccounts(){
		
		List<Account> accounts=null;
		try {
			accounts = accountServivce.showAllAccount();
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResponseEntity<List<Account>> response = new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
		
		return response;
	}
	
	@PutMapping("/deleteAccount")
	public ResponseEntity<String> deleteAccount(@RequestBody Map<String ,Object> requestMap){
		Map<String ,Object> map=requestMap;
		String msg="";
		String accountNumber=(String)map.get("accountNumber");
		
		try {
			Account account=accountServivce.fetchAccountByAccountId(accountNumber);
			account.setAccountStatus("Closed");
			boolean isTrue=accountServivce.deleteAccount(account);
			if(isTrue) {
				msg="Account Closed Successfully ";
			}else {
				msg="No such Account found with Id"+accountNumber;
			}
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.OK);
		
		return response;
	}
}
