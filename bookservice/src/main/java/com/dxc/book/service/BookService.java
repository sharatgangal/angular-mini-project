package com.dxc.book.service;



import java.util.List;

import com.dxc.book.exception.BookNotAddedException;
import com.dxc.book.exception.BookNotFoundException;
import com.dxc.book.exception.BooksNotFoundException;
import com.dxc.book.model.Book;

/**
 * @author sgangal2
 *
 */
public interface BookService {
     //This is fpr adding book
	 /**
	 * @param bookToAdd
	 * @return added book
	 * @throws BookNotAddedException
	 */
	Book addBook(Book bookToAdd) throws BookNotAddedException;
	/**
	 * @return List<Books> list of all books
	 */
	//This is for getting all books.
	 public List<Book> getBooks();
	 /**
	 * @param bookId
	 * @return boolean
	 * @throws BookNotFoundException
	 */
	//This is for deleting books
	 boolean deleteBook(String bookId) throws BookNotFoundException;
	 
}
