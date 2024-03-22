package com.ojas.spring.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionEntity {
	private LocalDateTime timeStamp;
	private String message;
	private HttpStatusCode statusResponse;
	private int statusCode;
}
