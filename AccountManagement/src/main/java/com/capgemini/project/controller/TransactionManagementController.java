package com.capgemini.project.controller;

import java.util.Date;
import java.util.Map;

import javax.security.auth.login.AccountException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.project.dao.ChequeEntityDao;
import com.capgemini.project.dao.TransactionEntityDao;
import com.capgemini.project.entity.Account;
import com.capgemini.project.entity.Cheque;
import com.capgemini.project.entity.Customer;
import com.capgemini.project.entity.Transaction;
import com.capgemini.project.exception.ChequeBounceException;
import com.capgemini.project.exception.InSufficientBalanceException;
import com.capgemini.project.service.AccountManagementService;
import com.capgemini.project.service.TransactionManagementService;

@RestController
public class TransactionManagementController {
	@Autowired
	private TransactionManagementService transactionService;
	
	@Autowired
	private AccountManagementService accountService;
	
	@Autowired
	private TransactionEntityDao transactionDao;
	
	@Autowired
	private ChequeEntityDao chequeDao;
	
	@PutMapping("/creditUsingSlip")
	public ResponseEntity<String> creditUsingSlip( @RequestBody Map<String ,Object> requestMap) {
		Map<String ,Object> map=requestMap;
		String msg="";
		String transAccountId = (String)map.get("transAccountId");
		double transAmount = (double)map.get("transAmount");
		try
		 {
			Account userAccount = transactionService.getAccountById(transAccountId);
			String customerId = userAccount.getAccountHolderId();
			Customer myCustomer = accountService.fetchCustomerByCustomerId(customerId);
			String customerName = myCustomer.getCustomerName();
			Transaction transaction = new Transaction();
			transaction.setTransAccountId(transAccountId);
			transaction.setTransAmount(transAmount);
			int transactionId = transactionService.creditUsingSlip(transaction);
			double userBalance = transactionService.getBalance(userAccount);
			double newBalance = userBalance+transAmount;
			transaction.setTransId(transactionId);
			transaction.setTransType("Credit");
			transaction.setTransDate(new Date());
			transaction.setTransClosingBalance(newBalance);
			transaction.setTransOption("Using Slip");
			transaction.setTransTo(customerName);
		    transactionDao.save(transaction);
	     	boolean isTrue = transactionService.updateBalance(userAccount, newBalance);
			if(isTrue)
			{
				msg = "Amount creditted successfully";
			}
			else
			{
				msg="No Such Account Found with Id "+transAccountId;
			}
		}
		catch (AccountException e) {
			e.printStackTrace();
		}
		ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.OK);
			return response;
		}
	
	
	@PutMapping("/debitUsingSlip")
	public ResponseEntity<String> debitUsingSlip( @RequestBody Map<String ,Object> requestMap) {
		Map<String ,Object> map=requestMap;
		String msg="";
		
		String transAccountId = (String)map.get("transAccountId");
		double transAmount = (double)map.get("transAmount");
		try
		{
			Account userAccount = transactionService.getAccountById(transAccountId);
			String customerId = userAccount.getAccountHolderId();
			Customer myCustomer = accountService.fetchCustomerByCustomerId(customerId);
			String customerName = myCustomer.getCustomerName();
			double userBalance = transactionService.getBalance(userAccount);
			if(transAmount>userBalance)
			{
				throw new InSufficientBalanceException("Low Balance In The Account");
			}
			double newBalance = userBalance-transAmount;
			Transaction transaction = new Transaction();
			transaction.setTransAccountId(transAccountId);
			transaction.setTransAmount(transAmount);
			int transId = transactionService.debitUsingSlip(transaction);
			transaction.setTransId(transId);
			transaction.setTransClosingBalance(newBalance);
			transaction.setTransDate(new Date());
			transaction.setTransFrom(customerName);
			transaction.setTransOption("Using Slip");
			transaction.setTransType("Debit");
			transactionDao.save(transaction);
			boolean isTrue = transactionService.updateBalance(userAccount, newBalance);
			if(isTrue)
			{
				msg = "Amount debitted successfully";
			}
			else
			{
				msg="No Such Account Found with Id "+transAccountId;
			}
		

		}
		catch(AccountException e)
		{
			e.printStackTrace();
		}
		ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.OK);
		return response;
	}
	
	
	@PutMapping("/debitUsingCheque")
	public ResponseEntity<String> debitUsingCheque( @RequestBody Map<String ,Object> requestMap) {
		Map<String ,Object> map=requestMap;
		String msg="";
		String transAccountId = (String)map.get("transAccountId");
		double transAmount = (double)map.get("transAmount");
		int chequeNum = (int)map.get("chequeNum");
		String chequeAccountNo = (String)map.get("chequeAccountNo");
		String chequeHolderName = (String)map.get("chequeHolderName");
		String chequeBankName = (String)map.get("chequeBankName");
		String chequeIFSC = (String)map.get("chequeIFSC");
		Date chequeIssueDate = (Date)map.get("chequeIssueDate");
		
		Transaction myTransaction = new Transaction();
		myTransaction.setTransAccountId(transAccountId);
		myTransaction.setTransAmount(transAmount);
		Cheque myCheque = new Cheque();
		myCheque.setChequeNum(chequeNum);
		myCheque.setChequeAccountNo(chequeAccountNo);
		myCheque.setChequeHolderName(chequeHolderName);
		myCheque.setChequeBankName(chequeBankName);
		myCheque.setChequeIFSC(chequeIFSC);
		myCheque.setChequeIssueDate(chequeIssueDate);
		
		try {
			Account userAccount = transactionService.getAccountById(transAccountId);
			Account payeeAccount = transactionService.getAccountById(chequeAccountNo);
			if(!userAccount.equals(payeeAccount))
			{
				myCheque.setChequeStatus("Cheque Bounced");
				throw new ChequeBounceException("Payee & Beneficiary Account Not Matched");
			}
			String customerId = userAccount.getAccountHolderId();
			Customer myCustomer = accountService.fetchCustomerByCustomerId(customerId);
			String customerName = myCustomer.getCustomerName();
			int transactionId = transactionService.debitUsingCheque(myTransaction, myCheque);
			int chequeId = transactionService.generateChequeId(myCheque);
			myCheque.setChequeId(Integer.toString(chequeId));
			double myBalance = transactionService.getBalance(userAccount);
			if(transAmount>myBalance)
			{
				myCheque.setChequeStatus("Cheque Bounced");
				throw new InSufficientBalanceException("Low Balance In The Account");
			}
			double newBalance = myBalance-transAmount;
			myCheque.setChequeStatus("Cheque cleared");
			chequeDao.save(myCheque);
			myTransaction.setChequeId(Integer.toString(chequeId));
			myTransaction.setTransClosingBalance(newBalance);
			myTransaction.setTransDate(new Date());
			myTransaction.setTransFrom(customerName);
			myTransaction.setTransId(transactionId);
			myTransaction.setTransOption("Using Cheque");
			myTransaction.setTransType("Debit");
			transactionDao.save(myTransaction);
			boolean isTrue = transactionService.updateBalance(userAccount, newBalance);
			if(isTrue)
			{
				msg = "Amount debitted successfully";
			}
			else
			{
				msg="No Such Account Found with Id "+transAccountId;
			}
	
		}
		catch(AccountException e)
		{
			e.printStackTrace();
		}
		ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.OK);
		return response;
	
	}
	
	@PutMapping("/creditUsingCheque")
	public ResponseEntity<String> creditUsingCheque( @RequestBody Map<String ,Object> requestMap) {
		Map<String ,Object> map=requestMap;
		String msg="";
		String transAccountId = (String)map.get("transAccountId");
		double transAmount = (double)map.get("transAmount");
		int chequeNum = (int)map.get("chequeNum");
		String chequeAccountNo = (String)map.get("chequeAccountNo");
		String chequeHolderName = (String)map.get("chequeHolderName");
		String chequeBankName = (String)map.get("chequeBankName");
		String chequeIFSC = (String)map.get("chequeIFSC");
		Date chequeIssueDate = (Date)map.get("chequeIssueDate");
		
		Transaction myTransaction = new Transaction();
		myTransaction.setTransAccountId(transAccountId);
		myTransaction.setTransAmount(transAmount);
		Cheque myCheque = new Cheque();
		myCheque.setChequeNum(chequeNum);
		myCheque.setChequeAccountNo(chequeAccountNo);
		myCheque.setChequeHolderName(chequeHolderName);
		myCheque.setChequeBankName(chequeBankName);
		myCheque.setChequeIFSC(chequeIFSC);
		myCheque.setChequeIssueDate(chequeIssueDate);
		
		try {
			Account beneficiaryAccount = transactionService.getAccountById(transAccountId);
			Account payeeAccount = transactionService.getAccountById(chequeAccountNo);
			String beneficiaryId = beneficiaryAccount.getAccountHolderId();
			Customer myBeneficiary = accountService.fetchCustomerByCustomerId(beneficiaryId);
			String beneficiaryName = myBeneficiary.getCustomerName();
			String payeeId = payeeAccount.getAccountHolderId();
			Customer myPayee = accountService.fetchCustomerByCustomerId(payeeId);
			String payeeName = myPayee.getCustomerName();
			int transactionId = transactionService.creditUsingCheque(myTransaction,myCheque);
			double beneficiaryBalance = transactionService.getBalance(beneficiaryAccount);
			double payeeBalance = transactionService.getBalance(payeeAccount);
			double newBeneficiaryBalance = beneficiaryBalance+transAmount;
			double newPayeeBalance = payeeBalance-transAmount;
			int chequeId = transactionService.generateChequeId(myCheque);
			myCheque.setChequeId(Integer.toString(chequeId));
			myCheque.setChequeStatus("Cheque cleared");
			myTransaction.setChequeId(Integer.toString(chequeId));
			myTransaction.setTransClosingBalance(newBeneficiaryBalance);
			myTransaction.setTransDate(new Date());
			myTransaction.setTransFrom(payeeName);
			myTransaction.setTransId(transactionId);
			myTransaction.setTransOption("Using Cheque");
			myTransaction.setTransTo(beneficiaryName);
			myTransaction.setTransType("Credit");
			transactionDao.save(myTransaction);
			chequeDao.save(myCheque);
			boolean beneficiaryisTrue = transactionService.updateBalance(beneficiaryAccount, newBeneficiaryBalance);
			boolean payeeisTrue = transactionService.updateBalance(payeeAccount, newPayeeBalance);
			if(beneficiaryisTrue==true && payeeisTrue==true)
			{
				msg = "Amount creditted successfully";
			}
			else
			{
				msg="No Such Account Found with Id "+transAccountId;
			}

		}
		catch(AccountException e)
		{
			e.printStackTrace();
		}
		ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.OK);
		return response;
	
	
	}
}

