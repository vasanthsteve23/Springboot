package com.adf.rest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.adf.rest.models.Transaction;


@RepositoryRestResource(collectionResourceRel = "apit",path = "apit")
public interface TransactionDao extends JpaRepository<Transaction, Long>{

//	Transaction find();
//
//	Transaction findByTrasactionId(Long transactionId);

}
