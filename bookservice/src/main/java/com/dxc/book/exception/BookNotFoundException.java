package com.dxc.book.exception;

/**
 * @author sgangal2
 *
 */
public class BookNotFoundException extends Exception {

	/**
	 * no argument constructor
	 */
	public BookNotFoundException() {
		super();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public BookNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public BookNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 */
	public BookNotFoundException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public BookNotFoundException(Throwable arg0) {
		super(arg0);
	}

	
}
