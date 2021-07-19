package com.adf.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adf.rest.common.BadRequestException;
import com.adf.rest.common.Error;
import com.adf.rest.dao.BankDao;
import com.adf.rest.dao.TransactionDao;
import com.adf.rest.models.BankAccount;
//import com.adf.rest.models.TransResponse;
import com.adf.rest.models.Transaction;
import com.adf.rest.request.TransactionRequest;
import com.adf.rest.validator.TransValidator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class TransactionController {
	 @Autowired
	 TransactionDao da;
	 
	 @Autowired
	 BankDao dao;
	 
	 @Autowired
	 private TransValidator validator;
	 
	 static final Logger logger = LogManager.getLogger(BankController.class.getName());
	 
	    @PostMapping(path="bank/account/{acc}",consumes={"application/json"})
	    public Transaction ho(@PathVariable("acc") Long AccountNumber, @RequestBody TransactionRequest obj){
	    	
	        Transaction objAcc = new Transaction();
	        
	            List<Error> errors = validator.validateTransRequest(obj,AccountNumber);
	        
		       // if not success
		        
		        if(errors.size()>0) {
		        	throw new BadRequestException("Bad Request",errors);
		        }
		        
		        
	        BankAccount ob = dao.findByAccountNumber(AccountNumber);
	        
	        objAcc.setTransactionType(obj.getTransactionType());
	        double getbal=obj.getTransactionAmount();
	        double oldbal=ob.getBalance();
	        objAcc.setOldBalance(oldbal);
	        if(obj.getTransactionType().toLowerCase().equals("deposit"))
	        {
	        	double newbal=getbal+oldbal;
	        	objAcc.setTransactionAmount(getbal);
	        	objAcc.setTransactionStatus("Success");
	        	ob.setBalance(newbal);
	        	objAcc.setNewBalance(newbal);
	        }
	        
	        if(obj.getTransactionType().toLowerCase().equals("withdraw"))
	        {
	        	if(oldbal>getbal)
	        	{
	        		double newbal=oldbal-getbal;
	        		objAcc.setTransactionAmount(getbal);
	        		objAcc.setTransactionStatus("Success");
	        		objAcc.setAccount(ob);
	        		ob.setBalance(newbal);
	        		objAcc.setNewBalance(newbal);
	        	}
	        	else
	        	{
	        		objAcc.setTransactionAmount(getbal);
	        		objAcc.setNewBalance(oldbal);
	        		objAcc.setTransactionStatus("Failure");
	        	}
	        }
	       
	        objAcc.setAccount(ob);
	        ob.getTransaction().add(objAcc);
	       
	        
	        da.save(objAcc);
	        
	        List<Error> errors1 = validator.validateTransAmount(obj,AccountNumber);
	        
	        if(errors1.size()>0) {
	        	throw new BadRequestException("Bad Request",errors1);
	        }
	        
	        dao.save(ob);
	        logger.info("Transaction done for the user "+ob.getAccountHolderName()+" -> "+objAcc.getTransactionType()+" with the amount of "+objAcc.getTransactionAmount());
	        
	        return objAcc;
	          
	    }  
}