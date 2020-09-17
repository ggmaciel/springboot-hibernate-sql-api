package com.gustavomaciel.springbintermediate.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gustavomaciel.springbintermediate.services.exceptions.DataIntegrityException;
import com.gustavomaciel.springbintermediate.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException err, HttpServletRequest request) {
		
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), err.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException err, HttpServletRequest request) {
		
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), err.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException err, HttpServletRequest request) {
		
		ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
		
		for(FieldError x : err.getBindingResult().getFieldErrors()) {
			error.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	
}
