package com.rms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class GlobalInvalidCredentialsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GlobalInvalidCredentialsException() {
		super();
	}

	public GlobalInvalidCredentialsException(String message) {
		super(message);
	}

	public GlobalInvalidCredentialsException(String message, Throwable cause) {
		super(message, cause);
	}
}
