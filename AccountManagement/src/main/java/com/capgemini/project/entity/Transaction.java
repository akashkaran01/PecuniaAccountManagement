package com.capgemini.project.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="transactions")
public class Transaction {
	@Id
	@Column(name="TRANSACTION_ID")
	private int transId;
	
	@Column(name="TRANSACTION_ACCOUNT_ID")
	private String transAccountId;
	
	@Column(name="TRANSACTION TYPE")
	private String transType;
	
	@Column(name="TRANSACTION AMOUNT")
	private double transAmount;
	
	@Column(name="TRANSACTION MODE")
	private String transOption;
	
	@Column(name="DATE")
	private Date transDate;
	
	@Column(name="CHEQUE_ID")
	private String chequeId;
	
	@Column(name="INITIATED_FROM")
	private String transFrom;
	
	@Column(name="INITIATED_TO")
	private String transTo;
	
	@Column(name="CLOSING_BALANCE")
	private double transClosingBalance;

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}
	
	

	public String getTransAccountId() {
		return transAccountId;
	}

	public void setTransAccountId(String transAccountId) {
		this.transAccountId = transAccountId;
	}
	
	

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}
	
	

	public double getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(double transAmount) {
		this.transAmount = transAmount;
	}
	
	

	public String getTransOption() {
		return transOption;
	}

	public void setTransOption(String transOption) {
		this.transOption = transOption;
	}
	
	

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	
	

	public String getChequeId() {
		return chequeId;
	}

	public void setChequeId(String chequeId) {
		this.chequeId = chequeId;
	}
	
	

	public String getTransFrom() {
		return transFrom;
	}

	public void setTransFrom(String transFrom) {
		this.transFrom = transFrom;
	}
	
	

	public String getTransTo() {
		return transTo;
	}

	public void setTransTo(String transTo) {
		this.transTo = transTo;
	}
	
	

	public double getTransClosingBalance() {
		return transClosingBalance;
	}

	public void setTransClosingBalance(double transClosingBalance) {
		this.transClosingBalance = transClosingBalance;
	}

	public Transaction(int transId, String transAccountId, String transType, double transAmount, String transOption,
			Date transDate, String chequeId, String transFrom, String transTo, double transClosingBalance) {
		super();
		this.transId = transId;
		this.transAccountId = transAccountId;
		this.transType = transType;
		this.transAmount = transAmount;
		this.transOption = transOption;
		this.transDate = transDate;
		this.chequeId = chequeId;
		this.transFrom = transFrom;
		this.transTo = transTo;
		this.transClosingBalance = transClosingBalance;
	}

	@Override
	public String toString() {
		return "Transaction [transId=" + transId + ", transAccountId=" + transAccountId + ", transType=" + transType
				+ ", transAmount=" + transAmount + ", transOption=" + transOption + ", transDate=" + transDate
				+ ", chequeId=" + chequeId + ", transFrom=" + transFrom + ", transTo=" + transTo
				+ ", transClosingBalance=" + transClosingBalance + "]";
	}

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
