package com.dxc.bookshelf.user.controller.test;

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


import com.dxc.bookshelf.user.controller.UserController;
import com.dxc.bookshelf.user.model.User;
import com.dxc.bookshelf.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author sgangal2
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest()
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private User user;
	
	@MockBean
	private UserService userService;
	
	
	/* Mockito's @injectMocks annotations allow us to inject mocked dependencies  */
	@InjectMocks
	private UserController userController;
	
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception{
		
		MockitoAnnotations.initMocks(this);
		mockMvc=MockMvcBuilders.standaloneSetup(userController).build();
		
		user=new User();
		user.setCountry("India");
		user.setGender("Male");
		user.setPassword("sdgfsdsfsf");
		user.setUserId("sharat@12.com");
		user.setUserName("sharat");
		
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void addBookSuccess() throws Exception{
	   User newUser=user;
		when(userService.addUser(any())).thenReturn(newUser);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/bookapp/addbook")
		.contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	
	 /**
	 * @throws Exception
	 */
	@Test
	    public void updateUser() throws Exception {
		 	User newUser=new User();
		 	newUser.setUserId("shgdhsgd");
		 	newUser.setCountry("india");
		 	newUser.setGender("Male");
		 	newUser.setPassword("sdsdhg");
		 	newUser.setUserName("shfhsf");
	        when(userService.isUserExists(newUser.getUserId())).thenReturn(true);
	        when(userService.updateUser(newUser.getUserId(),any())).thenReturn(newUser);

	        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/updateUser/sharat@13gmail.com")
	                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
	                .andDo(MockMvcResultHandlers.print());
	    }
	  
	 
	private String asJsonString(User book) {
		// TODO Auto-generated method stub
		try {
			return new ObjectMapper().writeValueAsString(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	
	}
}
