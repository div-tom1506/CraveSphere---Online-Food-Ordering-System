package com.cravesphere.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserGlobalException {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException (UserNotFoundException userNotFoundException) {
		
		ErrorResponse errorResponse = new ErrorResponse();
		
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setMessage(userNotFoundException.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException (EmailAlreadyExistsException emailAlreadyExistsException) {
		
		ErrorResponse errorResponse = new ErrorResponse();
		
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(emailAlreadyExistsException.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(RuntimeException exception) {
		
		ErrorResponse errorResponse = new ErrorResponse();
		
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	

}
