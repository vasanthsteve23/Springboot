package com.adf.rest.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adf.rest.common.Error;
import com.adf.rest.dao.BankDao;
import com.adf.rest.models.BankAccount;


@Component
public class RangeTransValidator {
	
    @Autowired
	BankDao dao;
    
	public List<Error> accountValidation(Long AccountNumber) {
		List<Error> errors = new ArrayList<>();
        BankAccount ob = dao.findByAccountNumber(AccountNumber);
		
		if(ob == null) {
			Error error = new Error("AccountNumber","Invalid AccountNumber");
			errors.add(error);
		}
		return errors;
	}

}
