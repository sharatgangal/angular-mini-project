package com.dxc.bookshelf.user.exception;

/**
 * @author sgangal2
 *
 */
public class UserExistsExeception extends Exception {

	/**
	 * no argument constructor
	 */
	public UserExistsExeception() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public UserExistsExeception(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UserExistsExeception(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public UserExistsExeception(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public UserExistsExeception(Throwable cause) {
		super(cause);
	}
	
	

}
