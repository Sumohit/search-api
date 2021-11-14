package com.learn.searchApi.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class SearchExceptionHandler extends ResponseEntityExceptionHandler {
	
	private ExceptionResponse exceptionResponse;
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex){
		exceptionResponse = new ExceptionResponse();
		exceptionResponse.setErrorMessage(ex.getMessage());
		exceptionResponse.setErrorCode("404");
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(RestClientException.class)
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public ResponseEntity<ExceptionResponse> unprocessableEntity(RestClientException ex){
		exceptionResponse = new ExceptionResponse();
		exceptionResponse.setErrorMessage(ex.getMessage());
		exceptionResponse.setErrorCode("422");
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		
	}
	
}
