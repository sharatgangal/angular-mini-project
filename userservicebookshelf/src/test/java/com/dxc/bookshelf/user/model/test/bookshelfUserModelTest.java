package com.dxc.bookshelf.user.model.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.dxc.bookshelf.user.model.User;

/**
 * @author sgangal2
 *
 */
public class bookshelfUserModelTest {

	private static User user;

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		user=new User();
		user.setUserId("123");
		user.setUserName("dxc@gmail.com");
		user.setPassword("s123");
		user.setGender("Male");
		user.setCountry("India");
		
	}

	/**
	 * 
	 */
	@Test
	public void testUser() 
	{
		assertEquals("123",user.getUserId());
		assertEquals("dxc@gmail.com", user.getUserName());
		assertEquals("s123", user.getPassword());
		assertEquals("Male", user.getGender());
		assertEquals("India", user.getCountry());
		
	}

}
