package com.adf.rest.request;

public class TransactionRequest {
		
	    private double TransactionAmount;
		private String TransactionType;
		private String TransactionStatus = "Success";
		public double getTransactionAmount() {
			return TransactionAmount;
		}
		public void setTransactionAmount(double transactionAmount) {
			TransactionAmount = transactionAmount;
		}
		public String getTransactionType() {
			return TransactionType;
		}
		public void setTransactionType(String transactionType) {
			TransactionType = transactionType;
		}
		public String getTransactionStatus() {
			return TransactionStatus;
		}
		public void setTransactionStatus(String transactionStatus) {
			TransactionStatus = transactionStatus;
		}
		
		
		
		
}