package com.cravesphere.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PaymentGlobalException {
	
	@ExceptionHandler
	ResponseEntity<ErrorResponse> handleException(PaymentNotFoundException paymentNotFoundException) {
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setMessage(paymentNotFoundException.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
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
