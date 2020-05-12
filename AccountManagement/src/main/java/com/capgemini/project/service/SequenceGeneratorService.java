package com.capgemini.project.service;

public interface SequenceGeneratorService {
	
	Long generateAccountNumber();
	
	int generateCustomerId();
	
	int generateAddressId();
	
	long generateTransId();
	
	long generateChequeId();
	
}
