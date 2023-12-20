package com.rms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class GlobalResourceNotFoundException extends RuntimeException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public GlobalResourceNotFoundException() {
		super();
	}

	public GlobalResourceNotFoundException(String s, String s1, Integer visaTypeId, Integer typeOfEntryId, Integer durationId) {
		super();
	}

	public GlobalResourceNotFoundException(String message) {
		super(message);
	}

	public GlobalResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
