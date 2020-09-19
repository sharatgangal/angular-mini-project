package com.dxc.book.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.dxc.book.model.Book;
import com.dxc.book.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;


/*    
 * The following annontation 
 * @RunWith(SpringRunner.class) tells Junit to run using Spring's testing support

 * 
 */

/**
 * @author sgangal2
 *
 */
@RunWith(SpringRunner.class)

/*
 * The following annotation @WebMvcTest is to fire up an application context ( IOC )
 * that contains only the beans needed for testing a web controller
 * 
 */

@WebMvcTest

public class BookControllerTest {

	@Autowired
	
	/* the below is the main entry point for server-side Spring MVC test support  */
	private MockMvc mockMvc;
	private Book book;
	 private  String[] arr= {"java"};
	
	/* The below annotation is used to which object needs to be mocked */
	@MockBean
	private BookService bookService;
	
	
	/* Mockito's @injectMocks annotations allow us to inject mocked dependencies  */
	@InjectMocks
	private BookController bookController;
	
	private List<Book> allBooks=null;
	


	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception{
		
		MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(bookController).build();
		book=new Book();
		book.set_id("125");
	       book.setBookFrequency(200);
	       book.setBookId("143");
	       book.setBookTitle("java");
	       book.setBookImage("image.com");
	       book.setUserId("ram@gmailram");
	       book.setBookAuthor(arr);
		allBooks=new ArrayList<>();
		allBooks.add(book);
	}
	
	
	/**
	 * @throws Exception
	 */
	@Test
	public void addBookSuccess() throws Exception{
	
		when(bookService.addBook(any())).thenReturn(book);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/bookapp/addbook")
		.contentType(MediaType.APPLICATION_JSON).content(asJsonString(book)))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void addBookNotSuccess() throws Exception{
	
		when(bookService.addBook(any())).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/bookapp/addbook")
		.contentType(MediaType.APPLICATION_JSON).content(asJsonString(null)))
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andDo(MockMvcResultHandlers.print());
	}

    
	  /**
	 * @throws Exception
	 */
	@Test
	    public void deleteBookSuccess() throws Exception {

	        when(bookService.deleteBook("125")).thenReturn(true);
	        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/bookapp/deleteBook/125")
	                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
	                .andDo(MockMvcResultHandlers.print());
	    }
	  
	  
	  /**
	 * @throws Exception
	 */
	@Test
	    public void deleteBookNotSuccess() throws Exception {

	        when(bookService.deleteBook(book.get_id())).thenReturn(true);
	        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/bookapp/deleteBook/126")
	                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest())
	                .andDo(MockMvcResultHandlers.print());
	    }
          

	  	
	  /**
	 * @throws Exception
	 */
	@Test
	  public void getRecommendeBooksSuccess()  throws Exception
	  {
		  when(bookService.getBooks()).thenReturn(allBooks);
	        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/bookapp/getRecommendedBooks/ram@gmail")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andDo(MockMvcResultHandlers.print());

	  }
	
	  /**
	 * @throws Exception
	 */
	  
	private String asJsonString(Book book) {
		// TODO Auto-generated method stub
		try {
			return new ObjectMapper().writeValueAsString(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	
	}
}
