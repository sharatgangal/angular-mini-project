package com.dxc.book.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.book.exception.BookNotAddedException;
import com.dxc.book.exception.BookNotFoundException;
import com.dxc.book.exception.BooksNotFoundException;
import com.dxc.book.model.Book;
import com.dxc.book.repository.BookRepository;

/**
 * @author sgangal2
 *
 */
@Service
//@Service annotation is used with classes that provide some business functionalities. 
public class BookServiceImpl implements BookService{

	@Autowired
	//we can use autowiring on properties, setters, and constructors
	private BookRepository bookRepository;
	
	
	@Override
	public Book addBook(Book bookToAdd) throws BookNotAddedException {
		Book addedBook=bookRepository.save(bookToAdd);
		if(addedBook==null)
		{
			throw new BookNotAddedException();
		}
		else
			return addedBook;
	}

	
	@Override
	public List<Book> getBooks() {
		List<Book> books=bookRepository.findAll();
		return books;
	}

	@Override
	public boolean deleteBook(String _id) throws BookNotFoundException{
		Optional<Book> book=bookRepository.findById(_id);
		
		if(book.isPresent())
		{
			bookRepository.deleteById(_id);
			return true;
		}
		else
			throw new BookNotFoundException();
	}

}
