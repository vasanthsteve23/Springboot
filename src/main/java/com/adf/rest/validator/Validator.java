package com.adf.rest.validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.adf.rest.models.BankAccount;

import com.adf.rest.common.Error;

@Component
public class Validator {

	public List<Error> validateCreateRequest(BankAccount objAcc) {
		
		List<Error> errors = new ArrayList<>();
		
		// Account Holder Name
		if(objAcc.getAccountHolderName()==null) {
			Error error = new Error("name","AccountHolderName is null");
			errors.add(error);
			
		}
		
		// AccountType
		
		if(objAcc.getAccountType()==null) {
			Error error = new Error("AccountType","AccountType is null");
			errors.add(error);
		}
		else if(objAcc.getAccountType().equals("Savings")==false && objAcc.getAccountType().equals("Current")==false) {
			Error error = new Error("AccountType","AccountType is not valid");
			errors.add(error);
		}
		
		// dob's
		LocalDate today = LocalDate.now();
		LocalDate dob = objAcc.getDateofBirth();
		if(objAcc.getDateofBirth() == null) {
			Error error = new Error("dob","DateOfBirth is null");
			errors.add(error);
		}
		else if(dob.compareTo(today)>=1) {   
			Error error = new Error("dob","DateOfBirth is invalid");
			errors.add(error);
		}
		return errors;
	}

}
