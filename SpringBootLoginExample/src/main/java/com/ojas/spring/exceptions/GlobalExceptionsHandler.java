package com.ojas.spring.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionsHandler {

	@ExceptionHandler(CustomExceptions.class)
	public ResponseEntity<ExceptionEntity> checkRequestTimeOut(CustomExceptions exeTimeOut) {
		ExceptionEntity exception = new ExceptionEntity();
		exception.setTimeStamp(LocalDateTime.now());
		exception.setMessage(exeTimeOut.getMessage());
		exception.setStatusResponse(HttpStatusCode.valueOf(504));
		exception.setStatusCode(HttpStatus.GATEWAY_TIMEOUT.value());
		return new ResponseEntity<ExceptionEntity>(exception, HttpStatus.GATEWAY_TIMEOUT);
	}
}
