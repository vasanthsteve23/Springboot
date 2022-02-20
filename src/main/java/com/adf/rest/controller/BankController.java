package com.adf.rest.controller;

import java.util.List;
import com.adf.rest.dao.BankDao;
import com.adf.rest.dao.TransactionDao;
import com.adf.rest.models.BankAccount;
import com.adf.rest.models.Transaction;
import com.adf.rest.request.CreateRequest;
import com.adf.rest.validator.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adf.rest.common.BadRequestException;
import com.adf.rest.common.Error;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class BankController {
    @Autowired
    BankDao dao;
    
    @Autowired
    TransactionDao da;
    
    @Autowired
    private Validator validator;
    
    static final Logger logger = LogManager.getLogger(BankController.class.getName());
    
    
    @PostMapping(path="bank/account",consumes={"application/json"})
    public BankAccount home(@RequestBody CreateRequest obj)
    {
    	Transaction obt=new Transaction();
        BankAccount objAcc = new BankAccount();
        objAcc.setAccountHolderName(obj.getAccountHolderName());
        logger.info("Account created for "+obj.getAccountHolderName());
        objAcc.setAccountType(obj.getAccountType());
        objAcc.setDateofBirth(obj.getDateofBirth());
        if ((obj.getInitialDeposit() != 0.0)){
             objAcc.setBalance(obj.getInitialDeposit());
        }
        else {
        	objAcc.setBalance(0.0);
        }
        
        if ((obj.getAccountType().toLowerCase()).equals("current")){
            objAcc.setTransactionFee(5.0);
        }
        
        List<Error> errors = validator.validateCreateRequest(objAcc);
        // if not success
        
        if(errors.size()>0) {
        	throw new BadRequestException("Bad Request",errors);
        }
        // if success
        dao.save(objAcc);
        
        obt.setTransactionAmount(objAcc.getBalance());
        obt.setTransactionType("Deposit");
        obt.setTransactionStatus("Success");
        obt.setAccount(objAcc);
        obt.setOldBalance(0.0);
        obt.setNewBalance(objAcc.getBalance());
        if(obt.getNewBalance()!=0.0 && obt.getNewBalance()!=null )
           objAcc.getTransaction().add(obt);
        da.save(obt);
        
        return objAcc;
    }
    
   

}