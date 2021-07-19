package com.adf.rest.dao;

import com.adf.rest.models.BankAccount;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "api",path = "api")
public interface BankDao  extends JpaRepository<BankAccount,Long>
{
	   @Query("from BankAccount where account_number=?1")
	   BankAccount findByAccountNumber(Long accountNumber);
	   
	   //BankAccount findFirstByOrderByAccountNumberDesc();
	
}
