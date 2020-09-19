package com.dxc.book.controller;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.book.exception.BookNotAddedException;
import com.dxc.book.exception.BookNotFoundException;
import com.dxc.book.httpresponse.BookResponse;
import com.dxc.book.model.Book;
import com.dxc.book.service.BookService;

import io.swagger.annotations.ApiOperation;


/**
 * @author sgangal2
 *
 */
@RestController
//Spring RestController annotation is used to create RESTful web services using Spring MVC.

@RequestMapping("/api/v1/bookapp")
//This annotation maps HTTP requests to handler methods of MVC and REST controllers.

@CrossOrigin("http://localhost:4200")
//This @CrossOrigin annotation enables cross-origin resource sharing only for this specific method
public class BookController {

	@Autowired
	//@Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished
	//@Autowired annotation can be used to autowire bean on the setter method just like @Required annotation, constructor, 
	//a property or methods with arbitrary names and/or multiple arguments
	private BookService bookService;	
	
	/**
	 * @param newBook
	 * @return ResponseEntity
	 */
	@ApiOperation(value = "adding the new book to user repo",response = ResponseEntity.class)
	//we can use the @ApiOperation annotation to describe the endpoint and its response type, like this
	//This is for adding book
	
	@PostMapping("/addbook")
	//@PostMapping annotation maps HTTP POST requests onto specific handler methods.
		//@RequestBody annotation binds request body to method parameters.
	public ResponseEntity<?>addBook(@RequestBody Book newBook)
	{
		BookResponse bookResponse= new BookResponse();
		boolean isBookDuplicate=false;
		List<Book> books=bookService.getBooks();
		for (Book book : books) 
		{
			if(book.getBookId().equals(newBook.getBookId()) && newBook.getUserId().equals(book.getUserId()))
			{
				isBookDuplicate=true;
				System.out.println("book is duplicate");
			}
			}
			if(isBookDuplicate==true)
			{	
				bookResponse.setMessage("bookWas not added");
				return new ResponseEntity<>(bookResponse,HttpStatus.BAD_REQUEST);
			}
			else {
				try {
					Book addedbook=bookService.addBook(newBook);
					if(addedbook!=null)
					{
						bookResponse.setMessage("book added");
						bookResponse.setObj(addedbook);
						return new ResponseEntity<>(addedbook,HttpStatus.OK);
					}
					else
					{
						bookResponse.setMessage("bookWas not added");
					    return new ResponseEntity<>(bookResponse,HttpStatus.BAD_REQUEST);
					}
				} catch (BookNotAddedException e) 
				{
					bookResponse.setMessage("bookWas not added");
			    return new ResponseEntity<>(bookResponse,HttpStatus.BAD_REQUEST);
				}	
			}
	}
	//This is for deleting book
	/**
	 * @param _id
	 * @return ResponseEntity
	 */
	@ApiOperation(value = "Deleting a book",response = ResponseEntity.class)
	@DeleteMapping("/deleteBook/{_id}")
	//@PathVariable is a Spring annotation which indicates that a method parameter should be bound to a URI template variable

	public ResponseEntity<?> deleteBook(@PathVariable String _id)
	{
		BookResponse bookResponse= new BookResponse();
		bookResponse.setMessage("Unable to delete the book");
		try {
			boolean isBookDeleted=bookService.deleteBook(_id);
			if(isBookDeleted)
			{
				bookResponse.setMessage("book got deleted");
				return new ResponseEntity<>(bookResponse,HttpStatus.OK);
			}
			
		} catch (BookNotFoundException e) {
			return new ResponseEntity<>(bookResponse,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(bookResponse,HttpStatus.BAD_REQUEST);
	}
	
	
	/**
	 * @param userId
	 * @return ResponseEntity
	 */
	@ApiOperation(value = "getting the recommended books",response = ResponseEntity.class)
	@GetMapping(value="/getRecommendedBooks/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
	//This is used to getting all recommended books.
	public ResponseEntity<?> getRecommende(@PathVariable String userId)
	{	
		BookResponse bookResponse=new BookResponse();
		List<Book> allbooks=bookService.getBooks();
		Set<String> nameSet = new HashSet<>();
		List<Book> booksDistinctByName = allbooks.stream()
		            .filter(e -> nameSet.add(e.getBookId()))
		            .collect(Collectors.toList());
		if(booksDistinctByName.size()==0)
		{
			bookResponse.setMessage("can not find any books to recommend");
			return new ResponseEntity<>(bookResponse,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(booksDistinctByName,HttpStatus.OK);
	}
	
	/**
	 * @param userId
	 * @return ResponseEntity
	 */
	@ApiOperation(value = "getting the favorite books",response = ResponseEntity.class)
	@GetMapping("/getFavoriteBooks/{userId}")
	public ResponseEntity<?> getFavoriteBooks(@PathVariable String userId)
	{
		List<Book> allbooks=bookService.getBooks(); 
	    List<Book> favoriteBooks=null;
	    favoriteBooks=allbooks.stream().filter(book->{if(book.getUserId().equals(userId)) {return true;}
	    else return false;}).collect(Collectors.toList());
	    return new ResponseEntity<>(favoriteBooks,HttpStatus.OK);
	}
}
