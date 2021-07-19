package com.adf.rest.models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TransactionId;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "account_account_number", referencedColumnName = "AccountNumber", nullable = false)
    private BankAccount account;
    private Double TransactionAmount;
    private Double OldBalance;
    private Double NewBalance;
    private String TransactionType;
    @CreationTimestamp
    private LocalDateTime TransactionDate;
    private String TransactionStatus;
    @CreationTimestamp
    private LocalDateTime CreatedAt;
    @UpdateTimestamp
    private LocalDateTime UpdatedAt;
    
    @JsonBackReference
    public BankAccount getAccount() {
        return account;
    }
    public void setAccount(BankAccount account) {
        this.account = account;
    }
    public Long getTransactionId() { 
        return TransactionId;
    }
    public void setTransactionId(Long transactionId) {
        TransactionId = transactionId;
    }
    public Double getTransactionAmount() {
        return TransactionAmount;
    }
    public void setTransactionAmount(Double transactionAmount) {
        TransactionAmount = transactionAmount;
    }
    public String getTransactionType() {
        return TransactionType;
    }
    public void setTransactionType(String transactionType) {
        TransactionType = transactionType;
    }
    public LocalDateTime getTransactionDate() {
        return TransactionDate;
    }
    public void setTransactionDate(LocalDateTime transactionDate) {
        TransactionDate = transactionDate;
    }
    public String getTransactionStatus() {
        return TransactionStatus;
    }
    public void setTransactionStatus(String transactionStatus) {
        TransactionStatus = transactionStatus;
    }
    public LocalDateTime getCreatedAt() {
        return CreatedAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        CreatedAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return UpdatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        UpdatedAt = updatedAt;
    }
	public Double getOldBalance() {
		return OldBalance;
	}
	public void setOldBalance(Double oldBalance) {
		OldBalance = oldBalance;
	}
	public Double getNewBalance() {
		return NewBalance;
	}
	public void setNewBalance(Double newBalance) {
		NewBalance = newBalance;
	}
    
    

}