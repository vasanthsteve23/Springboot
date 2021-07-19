package com.adf.rest.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.adf.rest.dao.BankDao;
import com.adf.rest.dao.TransactionDao;
import com.adf.rest.models.BankAccount;
import com.adf.rest.models.TransDetails;
import com.adf.rest.request.TransHistoryRequest;
import com.adf.rest.validator.RangeTransValidator;
import com.adf.rest.common.BadRequestException;
import com.adf.rest.common.Error;

@RestController
public class TransHistoryController {
     
		@Autowired
	    BankDao dao;
		
		 @Autowired
		 TransactionDao da;
		 
		 @Autowired
		 private RangeTransValidator validator;
	 
	    @GetMapping(path="bank/account/{acc}",consumes={"application/json"})
	    public TransDetails home(@PathVariable("acc") Long AccountNumber,
	    		@RequestParam(value = "fromdate")
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromdate,
                @RequestParam(value = "todate")
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate todate
	    		)
	    {
	    	
	       
		       List<Error> errors = validator.accountValidation(AccountNumber);
		       
		       if(errors.size()>0) {
		        	throw new BadRequestException("Bad Request",errors);
		        }
		    	
		       TransDetails td = new TransDetails();
		       
		       BankAccount ob = dao.findByAccountNumber(AccountNumber);
		       
		       List<com.adf.rest.models.Transaction> lis = ob.getTransaction();
		       
		       List<com.adf.rest.models.Transaction> lis1 =new ArrayList<>();
		       
		       com.adf.rest.models.Transaction now = null;
		       
		       for(int i=0;i<lis.size();i++) {
		    	   LocalDateTime transdate = lis.get(i).getTransactionDate();
		    	   LocalDate transdat = transdate.toLocalDate();
		    	   if((transdat.compareTo(fromdate)>=1) && (todate.compareTo(transdat)>=1)) {
		                 now = lis.get(i);
		    		   
		    	   }
		    	   if(now != null && lis1.contains(now)==false) {
		    	    lis1.add(now);
		    	   }
	           }
		       
		       td.setAccountNumber(AccountNumber);
		       td.setAccountHolderName(ob.getAccountHolderName());
		       td.setDateOfBirth(ob.getDateofBirth());
		       td.setBalance(ob.getBalance());
		       td.setAccountType(ob.getAccountType());
		       td.setFromDate(fromdate);
		       td.setToDate(todate);
		       td.setListDetails(lis1);
		       
	           return td;
	    }
	    
	
}