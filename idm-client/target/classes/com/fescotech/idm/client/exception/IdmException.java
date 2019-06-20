package com.fescotech.idm.client.exception;

public class IdmException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -837523669543219296L;

	public IdmException() {
		super();
	}

	public IdmException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IdmException(String message, Throwable cause) {
		super(message, cause);
	}

	public IdmException(String message) {
		super(message);
	}

	public IdmException(Throwable cause) {
		super(cause);
	}

}
