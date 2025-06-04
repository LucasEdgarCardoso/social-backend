package com.nulo.social.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -3748667588941355774L;

	private HttpStatus errorCategory;
	
	public ServiceException(String message, HttpStatus errorCategory) {
		super(message);
		this.errorCategory = errorCategory;
	}
	
}
