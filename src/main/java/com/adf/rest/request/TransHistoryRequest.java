package com.adf.rest.request;

import java.time.LocalDateTime;

public class TransHistoryRequest {
       
	   private LocalDateTime fromDate;
	   private LocalDateTime toDate;
	   
		public LocalDateTime getFromDate() {
			return fromDate;
		}
		public void setFromDate(LocalDateTime fromDate) {
			this.fromDate = fromDate;
		}
		public LocalDateTime getToDate() {
			return toDate;
		}
		public void setToDate(LocalDateTime toDate) {
			this.toDate = toDate;
		}
	   
		
	   
		
	   
	 
	 
}
