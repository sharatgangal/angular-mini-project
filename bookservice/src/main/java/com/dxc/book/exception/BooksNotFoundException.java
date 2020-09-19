package com.dxc.book.exception;

/**
 * @author sgangal2
 *
 */
public class BooksNotFoundException extends Exception {

	/**
	 * no argument constructor
	 */
	public BooksNotFoundException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public BooksNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BooksNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public BooksNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public BooksNotFoundException(Throwable cause) {
		super(cause);
	}

}
