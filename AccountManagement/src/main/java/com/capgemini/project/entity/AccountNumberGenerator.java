package com.capgemini.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.websocket.ClientEndpoint;

@Entity
@Table(name="Generator")
public class AccountNumberGenerator {
	
	

	@Id
	@Column(name = "Id")
	private int id;
	
	@Column(name = "Account_Number")
	private long AccountNumber;
	
	@Column(name = "Cutomer_Id")
	private int CustomerId;
	
	@Column(name = "Address_Id")
	private int AddressId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		AccountNumber = accountNumber;
	}

	public int getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}

	public int getAddressId() {
		return AddressId;
	}

	public void setAddressId(int addressId) {
		AddressId = addressId;
	}
	
	
	
}
