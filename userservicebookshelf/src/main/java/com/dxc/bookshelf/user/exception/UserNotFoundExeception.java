package com.dxc.bookshelf.user.exception;

/**
 * @author sgangal2
 *
 */
public class UserNotFoundExeception extends Exception {

	/**
	 * 
	 */
	public UserNotFoundExeception() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public UserNotFoundExeception(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UserNotFoundExeception(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public UserNotFoundExeception(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public UserNotFoundExeception(Throwable cause) {
		super(cause);
	}

}
