package com.pm.patientService.PatientService.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		
		return new ResponseEntity<Map<String,String>>(errors,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<Map<String,String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex){
		Map<String, String> errors = new HashMap<>();
		log.warn("Email already exists in the system {}",ex.getMessage());
		errors.put("Message", "Email Already Exists in the Database");
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

}
