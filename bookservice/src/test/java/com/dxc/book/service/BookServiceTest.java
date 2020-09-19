package com.dxc.book.service;

import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dxc.book.exception.BookNotAddedException;
import com.dxc.book.exception.BookNotFoundException;
import com.dxc.book.model.Book;

import com.dxc.book.repository.BookRepository;

/**
 * @author sgangal2
 *
 */
public class BookServiceTest {
	private Book book;
	@Mock
	private BookRepository bookRepo;
	 private  String[] arr= {"java"};
	
	 /*  Mockito @InjectMocks annotation allow us to inject
	 mocked dependencies object.   */
	
	@InjectMocks
	private static BookServiceImpl bookServiceImpl;

	private static List<Book> allBooks=null;
	Optional<Book>  options;
	
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {

		/*We can initiialize mock by calling initMocks method of
		  org.mockito.MockitoAnnotations
		  */
		
		  MockitoAnnotations.initMocks(this);
		   book=new Book();
		   book.set_id("125");
	       book.setBookFrequency(200);
	       book.setBookId("143");
	       book.setBookTitle("java");
	       book.setBookImage("image.com");
	       book.setUserId("ram@gmail.com");
	       book.setBookAuthor(arr);
	       
	       allBooks=new ArrayList<>();
	       allBooks.add(book);
			options=Optional.of(book);
	}

	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception 
	{
	   bookRepo.deleteAll();
	}

	/* Testcase for add book */
	/**
	 * @throws Exception
	 */
	@Test
	public void addBookTestSuccess() throws Exception{
		
		when(bookRepo.save((Book)any())).thenReturn(book);
		Book savedBook=bookServiceImpl.addBook(book);
		assertEquals(book,savedBook);
	}
	
	/**
	 * @throws Exception
	 */
	@Test(expected=BookNotAddedException.class)
	public void addBookFailure() throws Exception {
		when(bookRepo.save((Book)any())).thenReturn(null);
		Book savedBook=bookServiceImpl.addBook(book);
		assertEquals(book,savedBook);

	}
	
	/* Testcase for delete book */
	
	/**
	 * @throws Exception
	 */
	@Test
	public void deleteBookTest() throws Exception {
	when(bookRepo.findById(book.get_id())).thenReturn(options);	
	boolean flag=bookServiceImpl.deleteBook(book.get_id());
	assertTrue(flag);
	}
	
	/**
	 * @throws BookNotFoundException
	 */
	@Test(expected = BookNotFoundException.class)
	public void deleteBookTestFailure() throws BookNotFoundException {
	when(bookRepo.findById(book.get_id())).thenThrow(NoSuchElementException.class);	
	boolean flag=bookServiceImpl.deleteBook("226");
	assertFalse(flag);
	}
	
	/* Testcase for get all books */
	
	/**
	 * @throws Exception
	 */
	@Test
	public void getAllBooksTest() throws Exception {
	when(bookRepo.findAll()).thenReturn(allBooks);	
	List<Book> list= bookServiceImpl.getBooks();
	assertEquals(allBooks,list);
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void getAllBooksTestFailure() throws Exception {
	when(bookRepo.findAll()).thenReturn(null);	
	List<Book> list= bookServiceImpl.getBooks();
	assertNotEquals(allBooks,list);
	}

}
