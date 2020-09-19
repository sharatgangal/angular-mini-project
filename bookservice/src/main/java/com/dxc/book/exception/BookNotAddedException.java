package com.dxc.book.exception;

/**
 * @author sgangal2
 *
 */
public class BookNotAddedException extends Exception{

	/**
	 * no args constructor
	 */
	public BookNotAddedException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public BookNotAddedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BookNotAddedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public BookNotAddedException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public BookNotAddedException(Throwable cause) {
		super(cause);
	}
	
	
}
