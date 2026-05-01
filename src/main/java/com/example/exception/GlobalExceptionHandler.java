package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourseNotFoundException.class)
	public ResponseEntity<ErrorResponse> resonceNotfoundHandler(ResourseNotFoundException rnf, WebRequest web){
		
		ErrorResponse res = new ErrorResponse(rnf.getMessage(), web.getDescription(false),404);
		
		return new ResponseEntity<>( res ,HttpStatus.NOT_FOUND);
		
	}
	
	

}
