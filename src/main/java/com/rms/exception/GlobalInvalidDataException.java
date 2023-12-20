package com.rms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GlobalInvalidDataException extends RuntimeException {
	private static final long serialVersionUID = 1L;


	public GlobalInvalidDataException(String errorMessage) {
		super(errorMessage);

	}
}
