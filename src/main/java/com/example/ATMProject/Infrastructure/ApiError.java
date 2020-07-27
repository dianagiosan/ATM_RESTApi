package com.example.ATMProject.Infrastructure;

import java.time.LocalDateTime;

public class ApiError {
	private LocalDateTime timestamp;
	private String errorMessage;
	public ApiError(LocalDateTime timestamp, String errorMessage) {
		this.timestamp = timestamp;
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
