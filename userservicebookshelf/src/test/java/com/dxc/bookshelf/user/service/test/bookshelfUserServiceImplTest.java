package com.dxc.bookshelf.user.service.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dxc.bookshelf.user.model.User;
import com.dxc.bookshelf.user.repository.UserRepository;
import com.dxc.bookshelf.user.service.UserServiceImpl;

import com.dxc.bookshelf.user.exception.UserNotFoundExeception;
import com.dxc.bookshelf.user.exception.UserExistsExeception;

/**
 * @author sgangal2
 *
 */
public class bookshelfUserServiceImplTest {

	private User user;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	Optional<User>  options;
	

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		MockitoAnnotations.initMocks(this);
		user=new User();
		user.setUserId("123");
		user.setUserName("vishal");
		user.setPassword("vis#al");
		user.setGender("male");
		user.setCountry("India");
		options=Optional.of(user);	
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testAddUserSuccess() throws Exception
	{
		when(userRepository.save((User)any())).thenReturn(user);
		User savedUser=userServiceImpl.addUser(user);
		assertEquals(user,savedUser);
	}
	
	/**
	 * @throws Exception
	 */
	@Test(expected=UserNotFoundExeception.class)
	public void testAddUserSuccessFailure() throws Exception
	{
		when(userRepository.save((User)any())).thenReturn(null);
		User savedUser=userServiceImpl.addUser(user);
		assertEquals(user,savedUser);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testDeleteUserSuccess() throws Exception
	{
		when(userRepository.findById(user.getUserId())).thenReturn(options);	
		boolean flag=userServiceImpl.deleteUser(user.getUserId());
		assertTrue(flag);
	}
	
	
	/**
	 * @throws Exception
	 */
	@Test(expected=UserNotFoundExeception.class)
	public void testDeleteUserFailure() throws Exception
	{
		when(userRepository.findById(user.getUserId())).thenReturn(null);	
		boolean flag=userServiceImpl.deleteUser("1");
		assertFalse(flag);
	}
	

	/**
	 * @throws Exception
	 */
	@Test
	public void testUpdateUserSuccess() throws Exception
	{
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		user.setUserName("vishal");
		User fetchedUser=userServiceImpl.updateUser(user.getUserId(),user);
		assertEquals(user.getUserName(),fetchedUser.getUserName());
			
	}
	
	/**
	 * @throws Exception
	 */
	@Test(expected=UserNotFoundExeception.class)
	public void testUpdateUserSuccessFailure() throws Exception
	{
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		user.setUserName("vishal");
		User fetchedUser=userServiceImpl.updateUser(user.getUserId(), user);
		assertEquals("successfully",fetchedUser.getUserId());	
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testFindUserSuccess() throws Exception
	{
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		User findUser=userServiceImpl.findUser(user.getUserId());
		assertEquals(user.getUserId(), findUser.getUserId());
	}
	
	/**
	 * @throws Exception
	 */
	@Test(expected=UserNotFoundExeception.class)
	public void testFindUserFailure() throws Exception
	{
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		User findUser=userServiceImpl.findUser(user.getUserId());
		assertEquals(user.getUserId(), findUser.getUserId());
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testIsUserExistsSuccess() throws Exception
	{
		when(userRepository.findById(user.getUserId())).thenReturn(options);	
		boolean flag=userServiceImpl.isUserExists(user.getUserId());
		assertTrue(flag);
	}
	
	/**
	 * @throws Exception
	 */
	@Test(expected=UserExistsExeception.class)
	public void testIsUserExistsFailure() throws Exception
	{
		when(userRepository.findById(user.getUserId())).thenReturn(options);	
		boolean flag=userServiceImpl.isUserExists("");
		assertTrue(flag);
	}
	
	

	/**
	 * @throws Exception
	 */
	@Test
	public void testLoginSuccess() throws Exception
	{
		//when(userRepository.findById(user.getUserId())).thenReturn(options);
		User findUser=userServiceImpl.Login(user.getUserName(), user.getPassword());
		assertEquals(user.getUserName(), findUser.getPassword());
	}
	
	/**
	 * @throws Exception
	 */
	@Test(expected=UserNotFoundExeception.class)
	public void testLoginFailure() throws Exception
	{
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		User findUser=userServiceImpl.Login(user.getUserName(), user.getPassword());
		assertEquals(user.getUserId(), findUser.getUserId());
	}

}
