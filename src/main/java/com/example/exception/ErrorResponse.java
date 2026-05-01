package com.example.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorResponse {
    
	LocalDateTime  timestamp;
	String errorMessage;
	String errordescription;
	int status;
	
	
	
	public ErrorResponse(String errorMessage,String errordescription,int status) {
		this.timestamp = LocalDateTime.now();
		this.errorMessage=errorMessage;
		this.errordescription= errordescription;
		this.status=status;
	
	}
}
