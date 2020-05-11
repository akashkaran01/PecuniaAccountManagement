package com.capgemini.project.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Accounts")
public class Account {

	@Id
	@Column(name="Account_number")
	private String accountNumber;
	 
	@Column(name="Account_Holder_Id")
	private String accountHolderId;
	
	@Column(name="Account_Status")
	private String accountStatus;
	
	@Column(name="Account_Balance")
	private double accountBalance;

	@Column(name="Branch_Id")
	private String branchId;
	
	@Column(name="Account_Type")
	private String accountType;
	
	@Column(name="Account_Interest")
	private double accountInterest;
	
	@Column(name="Last_Update")
	private Date lastUpdate;
	
	

	public String getAccountNumber() {
		return accountNumber;
	}



	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}



	public String getAccountHolderId() {
		return accountHolderId;
	}



	public void setAccountHolderId(String accountHolderId) {
		this.accountHolderId = accountHolderId;
	}



	public String getAccountStatus() {
		return accountStatus;
	}



	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}



	public double getAccountBalance() {
		return accountBalance;
	}



	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}



	public String getBranchId() {
		return branchId;
	}



	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}



	public double getAccountInterest() {
		return accountInterest;
	}



	public void setAccountInterest(double accountInterest) {
		this.accountInterest = accountInterest;
	}

	

	public String getAccountType() {
		return accountType;
	}



	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}



	public Date getLastUpdate() {
		return lastUpdate;
	}



	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountHolderId=" + accountHolderId
				+ ", accountStatus=" + accountStatus + ", accountBalance=" + accountBalance + ", branchId=" + branchId
				+ ", accountType=" + accountType + ", accountInterest=" + accountInterest + ", lastUpdate=" + lastUpdate
				+ "]";
	}

	

	public Account(String accountNumber, String accountHolderId, String accountStatus, double accountBalance,
			String branchId, String accountType, double accountInterest, Date lastUpdate) {
		super();
		this.accountNumber = accountNumber;
		this.accountHolderId = accountHolderId;
		this.accountStatus = accountStatus;
		this.accountBalance = accountBalance;
		this.branchId = branchId;
		this.accountType = accountType;
		this.accountInterest = accountInterest;
		this.lastUpdate = lastUpdate;
	}



	public Account() {
		super();
	}
	
	
}
