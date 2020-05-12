package com.capgemini.project.service;

import com.capgemini.project.entity.Account;
import com.capgemini.project.entity.Cheque;
import com.capgemini.project.entity.Transaction;
import com.capgemini.project.exception.AccountException;
import com.capgemini.project.exception.ChequeBounceException;
import com.capgemini.project.exception.InSufficientBalanceException;

public interface TransactionManagementService {
	
	public double getBalance(Account account);
	
	public boolean updateBalance(Account account,double balance);
	
	public int generateTransactionId(Transaction transaction);
	
	public int generateChequeId(Cheque cheque);
	
	public Account getAccountById(String accountNumber) throws AccountException;
	
	public int creditUsingSlip(Transaction transaction);
	
	public int creditUsingCheque(Transaction transaction,Cheque cheque);
	
	public int debitUsingSlip(Transaction transaction) ;
	
	public int debitUsingCheque(Transaction transaction,Cheque cheque) ;
	

}
