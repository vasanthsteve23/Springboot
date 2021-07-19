package com.adf.rest.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransDetails {
   
	private Long AccountNumber;
	private String AccountHolderName;
	private LocalDate DateOfBirth;
	private Double Balance;
	private String AccountType;
	private LocalDate FromDate;
	private LocalDate ToDate;
	private List<Transaction> listDetails = new ArrayList<>();
	
	
	public Long getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		AccountNumber = accountNumber;
	}
	
	
	public String getAccountHolderName() {
		return AccountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		AccountHolderName = accountHolderName;
	}
	
	
	public LocalDate getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	
	
	public Double getBalance() {
		return Balance;
	}
	public void setBalance(Double balance) {
		Balance = balance;
	}
	
	
	public String getAccountType() {
		return AccountType;
	}
	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	
	
	public LocalDate getFromDate() {
		return FromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		FromDate = fromDate;
	}
	
	
	public LocalDate getToDate() {
		return ToDate;
	}
	public void setToDate(LocalDate toDate) {
		ToDate = toDate;
	}
	
	
	public List<Transaction> getListDetails() {
		return listDetails;
	}
	public void setListDetails(List<Transaction> listDetails) {
		this.listDetails = listDetails;
	}
	
	
}

