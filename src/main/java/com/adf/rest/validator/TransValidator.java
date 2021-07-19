package com.adf.rest.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adf.rest.common.Error;
import com.adf.rest.dao.BankDao;
import com.adf.rest.models.BankAccount;
import com.adf.rest.models.Transaction;
import com.adf.rest.request.TransactionRequest;

@Component
public class TransValidator {
	
    @Autowired
	BankDao dao;
    
	public List<Error> validateTransRequest(TransactionRequest obj,Long AccountNumber) {
		List<Error> errors = new ArrayList<>();

		BankAccount ob = dao.findByAccountNumber(AccountNumber);
		
		if(ob == null) {
			Error error = new Error("AccountNumber","Invalid AccountNumber");
			errors.add(error);
		}
		
		if(obj.getTransactionType() == null) {
			Error error = new Error("TransactionType","Please mention the TransactionType");
			errors.add(error);
		}
		
		if(obj.getTransactionAmount() <= 0.0) {
			Error error = new Error("TransactionAmount","Please enter the valid TransactionAmount");
			errors.add(error);
		}
		
		if(obj.getTransactionType() != null) {
			if((obj.getTransactionType().toLowerCase()).equals("withdraw")==false && (obj.getTransactionType().toLowerCase()).equals("deposit")==false) {
				Error error = new Error("TransactionType","Please enter the valid TransactionType");
				errors.add(error);
			}
		}
		
		
		return errors;
	}
	
	public List<Error> validateTransAmount(TransactionRequest obj,Long AccountNumber) {
		List<Error> errors = new ArrayList<>();
		Double newBal = obj.getTransactionAmount();
		BankAccount ob = dao.findByAccountNumber(AccountNumber);
		Double oldBal = ob.getBalance();
		
		if(newBal > oldBal) {
			Error error = new Error("TransactionAmount","Insufficient Balance");
			errors.add(error);
		}
		
		return errors;
	}
	
}
