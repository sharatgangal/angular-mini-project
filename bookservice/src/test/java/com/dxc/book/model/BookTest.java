package com.dxc.book.model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author sgangal2
 *
 */
public class BookTest {
	private static Book book;
	private static String[] arr= {"java"};
	
	 private String _id;
	 private String bookId;
	 private String userId;
	 private String bookTitle;
	 private String[] bookAuthor;
     private String bookImage;
     private int bookFrequency;
     
     //Constructor

	/**
	 * @return unique id
	 * 
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * 
	 */
	public BookTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param _id
	 */
	public void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * @return book id
	 */
	public String getBookId() {
		return bookId;
	}

	/**
	 * @param bookId
	 */
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return book title
	 */
	public String getBookTitle() {
		return bookTitle;
	}

	/**
	 * @param bookTitle
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	/**
	 * @return book author
	 */
	public String[] getBookAuthor() {
		return bookAuthor;
	}

	/**
	 * @param bookAuthor
	 */
	public void setBookAuthor(String[] bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	/**
	 * @return book image
	 */
	public String getBookImage() {
		return bookImage;
	}

	/**
	 * @param bookImage
	 */
	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}

	/**
	 * @return book frequency
	 */
	public int getBookFrequency() {
		return bookFrequency;
	}

	/**
	 * @param bookFrequency
	 */
	public void setBookFrequency(int bookFrequency) {
		this.bookFrequency = bookFrequency;
	}

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		book=new Book();
	       book.set_id("123");
	       book.setBookFrequency(200);
	       book.setBookId("143");
	       book.setBookTitle("java");
	       book.setBookImage("image.com");
	       book.setUserId("ram@gmail.com");
	       book.setBookAuthor(arr);
	}

	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 
	 */
	@Test
	public void bookTest() {
		assertEquals("123", book.get_id());
		assertEquals(200, book.getBookFrequency());
		assertEquals("143", book.getBookId());
		assertEquals("java", book.getBookTitle());
		assertEquals("image.com", book.getBookImage());
		assertEquals("ram@gmail.com", book.getUserId());
		assertEquals(arr, book.getBookAuthor());
	}

}
