package com.rms.exception;

public class RequestExaustedException
     extends RuntimeException{

        /** The Constant serialVersionUID. */
        private static final long serialVersionUID = 1L;

        /**
         * Instantiates a new OTP service exception.
         *
         * @param message the message
         * @param cause the cause
         */
	public RequestExaustedException(String message, Throwable cause) {
            super(message, cause);
            // TODO Auto-generated constructor stub
        }

        /**
         * Instantiates a new OTP service exception.
         *
         * @param message the message
         */
	public RequestExaustedException(String message) {
            super(message);
            // TODO Auto-generated constructor stub
        }

    }
