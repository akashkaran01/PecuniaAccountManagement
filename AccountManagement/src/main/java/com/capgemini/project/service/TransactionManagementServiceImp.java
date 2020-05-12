package com.capgemini.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.project.dao.AccountEntityDao;
import com.capgemini.project.dao.TransactionEntityDao;
import com.capgemini.project.entity.Account;
import com.capgemini.project.entity.Cheque;
import com.capgemini.project.entity.Transaction;
import com.capgemini.project.exception.AccountException;
import com.capgemini.project.exception.ChequeBounceException;
import com.capgemini.project.exception.InSufficientBalanceException;

@Service
@Transactional
public class TransactionManagementServiceImp implements TransactionManagementService {

	@Autowired
	private AccountEntityDao accountEntityDao;
	
	@Autowired
	private SequenceGeneratorService mySequence; 

	@Override
	public double getBalance(Account account) {
		double myBalance = account.getAccountBalance();
		return myBalance;
		
	}

	@Override
	public boolean updateBalance(Account account,double balance)  {
			account.setAccountBalance(balance);
			return true;
		
	}

	@Override
	public int generateTransactionId(Transaction transaction) {
		long myTransactionId = mySequence.generateTransId();
		String transId = String.valueOf(myTransactionId);
		int id = Integer.parseInt(transId);
		return id;
		
		
	}

	@Override
	public int generateChequeId(Cheque cheque) {
		long myChequeId = mySequence.generateChequeId();
		String chequeId = String.valueOf(myChequeId);
		int id = Integer.parseInt(chequeId);
		return id;
	}

	@Override
	public Account getAccountById(String accountNumber) throws AccountException {
		Optional<Account> optional=accountEntityDao.findById(accountNumber);
		 if(optional.isPresent()) {
	            Account account=optional.get();
	            return account;
	        }
	        throw new AccountException("Account not found for id="+accountNumber);
	}

	@Override
	public int creditUsingSlip(Transaction transaction) {
		
			int transactionId = generateTransactionId(transaction);
			return transactionId;
	}
		@Override
	public int creditUsingCheque(Transaction transaction, Cheque cheque) {
			int transactionId = generateTransactionId(transaction);
			return transactionId;
	}

	@Override
	public int debitUsingSlip(Transaction transaction)  {
		
		int transactionId = generateTransactionId(transaction);
		return transactionId;
	}

	@Override
	public int debitUsingCheque(Transaction transaction, Cheque cheque) {
		int transId = generateTransactionId(transaction);
		return transId;
	}
	
	
}
