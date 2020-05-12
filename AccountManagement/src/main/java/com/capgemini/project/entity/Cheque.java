package com.capgemini.project.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="cheque")
public class Cheque {
	@Id
	@Column(name="CHEQUE_ID")
	private String ChequeId;
	
	@Column(name="CHEQUE_NUM")
	private int chequeNum;        
	
	@Column(name="ACCOUNT_NUMBER")
	private String chequeAccountNo;
	
	@Column(name="CHEQUE_HOLDERNAME")      
	private String chequeHolderName;
	
	@Column(name="CHEQUE_OF_BANK")
	private String chequeBankName;
	
	@Column(name="BRANCH_IFSC")
	private String chequeIFSC;
	
	@Column(name="ISSUE_DATE")
	private Date chequeIssueDate;
	
	@Column(name="CHEQUE_STATUS")
	private String chequeStatus;
	
	public String getChequeId() {
		return ChequeId;
	}
	public void setChequeId(String chequeId) {
		ChequeId = chequeId;
	}
	
	
	public int getChequeNum() {
		return chequeNum;
	}
	public void setChequeNum(int chequeNum) {
		this.chequeNum = chequeNum;
	}
	
	
	public String getChequeAccountNo() {
		return chequeAccountNo;
	}
	public void setChequeAccountNo(String chequeAccountNo) {
		this.chequeAccountNo = chequeAccountNo;
	}
	
	
	public String getChequeHolderName() {
		return chequeHolderName;
	}
	public void setChequeHolderName(String chequeHolderName) {
		this.chequeHolderName = chequeHolderName;
	}
	
	
	
	public String getChequeBankName() {
		return chequeBankName;
	}
	public void setChequeBankName(String chequeBankName) {
		this.chequeBankName = chequeBankName;
	}
	
	
	public String getChequeIFSC() {
		return chequeIFSC;
	}
	public void setChequeIFSC(String chequeIFSC) {
		this.chequeIFSC = chequeIFSC;
	}
	
	
	public Date getChequeIssueDate() {
		return chequeIssueDate;
	}
	public void setChequeIssueDate(Date chequeIssueDate) {
		this.chequeIssueDate = chequeIssueDate;
	}
	
	
	public String getChequeStatus() {
		return chequeStatus;
	}
	public void setChequeStatus(String chequeStatus) {
		this.chequeStatus = chequeStatus;
	}
	
	@Override
	public String toString() {
		return "Cheque [ChequeId=" + ChequeId + ", chequeNum=" + chequeNum + ", chequeAccountNo=" + chequeAccountNo
				+ ", chequeHolderName=" + chequeHolderName + ", chequeBankName=" + chequeBankName + ", chequeIFSC="
				+ chequeIFSC + ", chequeIssueDate=" + chequeIssueDate + ", chequeStatus=" + chequeStatus + "]";
	}
	public Cheque(String chequeId, int chequeNum, String chequeAccountNo, String chequeHolderName,
			String chequeBankName, String chequeIFSC, Date chequeIssueDate, String chequeStatus) {
		super();
		ChequeId = chequeId;
		this.chequeNum = chequeNum;
		this.chequeAccountNo = chequeAccountNo;
		this.chequeHolderName = chequeHolderName;
		this.chequeBankName = chequeBankName;
		this.chequeIFSC = chequeIFSC;
		this.chequeIssueDate = chequeIssueDate;
		this.chequeStatus = chequeStatus;
	}
	public Cheque() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
