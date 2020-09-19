package com.dxc.book.httpresponse;


import java.util.List;

import org.springframework.stereotype.Component;

import com.dxc.book.model.Book;

/**
 * @author sgangal2
 *
 */
@Component
public class BookResponse {

	private String message;
	private Object obj;
	private List<Book> books;
	
	//Constructors are used
	/**
	 * 
	 */
	public BookResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param obj
	 * @param books
	 */
	public BookResponse(String message, Object obj, List<Book> books) {
		super();
		this.message = message;
		this.obj = obj;
		this.books = books;
	}
   //getters and setters are used
	/**
	 * @return String (message)
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return Object
	 */
	public Object getObj() {
		return obj;
	}

	/**
	 * @param obj
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}

	/**
	 * @return List<Books>
	 */
	public List<Book> getBooks() {
		return books;
	}

	/**
	 * @param books
	 */
	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
