package com.dxc.book.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dxc.book.model.Book;
/**
 * @author sgangal2
 *
 */
@RunWith(SpringRunner.class)

/* We are testing for MongoDB */
@DataMongoTest
public class BookRepoTest {

	@Autowired
	private BookRepository bookRepo;
	private Book book;
	 private static String[] arr= {"java"};
	 List<Book> books;
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		 books= new ArrayList<>();
		book=new Book();
	       book.set_id("125");
	       book.setBookFrequency(200);
	       book.setBookId("143");
	       book.setBookTitle("java");
	       book.setBookImage("image.com");
	       book.setUserId("ram@gmail.com");
	       book.setBookAuthor(arr);
	       books.add(book);
	}

	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception 
	{
		bookRepo.deleteAll();
	}
	

	/**
	 * testing of add book
	 */
	@Test
	public void addBookTest() {
		
		bookRepo.insert(book);
		Book fetchedBook=bookRepo.findById(book.get_id()).get();
		assertEquals("125",fetchedBook.get_id());
	}
	
	/**
	 * testing for updatebook
	 */
	// Update testcase for CRUD operation
	   @Test
		 public void updateBookTest()
		 {
		   // 1) Inserting a record so that we can update the inserted record
			bookRepo.insert(book); 
			// fetching the record which we want to update
			Book fetchedBook=bookRepo.findById(book.get_id()).get();
			assertEquals("125",fetchedBook.get_id());
			// We are trying to update the title
			fetchedBook.setBookTitle("html");;
			// save the data with the change
			bookRepo.save(fetchedBook);
			// after saving fetch the book once again to check whether the title got updated!!!
			fetchedBook=bookRepo.findById(book.get_id()).get();
			assertEquals("html",fetchedBook.getBookTitle());
			
		 }
	   
	/**
	 * testing for delete
	 */
	// DELETE testcase for CRUD operation 
	   @Test(expected=NoSuchElementException.class)
		public void deleteBookTest() {
			// 1) Inserting one record
		     bookRepo.insert(book);
		     // 2) Fetch the record to be deleted!!!!
		     Book fetchedBook=bookRepo.findById(book.get_id()).get();
		     assertEquals("125",fetchedBook.get_id());
		     // 3) Delete the record!!!
		     bookRepo.delete(fetchedBook);
		     fetchedBook=bookRepo.findById(book.get_id()).get();
	   }

	   /**
	 * testing for getbooks
	 */
	@Test()
	   public void getBooksTest()
	   {
		   bookRepo.save(book);
		   List<Book>allBooks=bookRepo.findAll();
		   assertEquals(allBooks.get(0).get_id(), book.get_id());
		   
		   
	   }
}
