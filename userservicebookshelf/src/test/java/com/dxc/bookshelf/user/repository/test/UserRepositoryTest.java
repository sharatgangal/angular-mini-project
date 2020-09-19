package com.dxc.bookshelf.user.repository.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dxc.bookshelf.user.model.User;
import com.dxc.bookshelf.user.repository.UserRepository;
import com.dxc.bookshelf.user.service.UserService;

/**
 * @author sgangal2
 *
 */
@RunWith(SpringRunner.class)

@DataJpaTest
public class UserRepositoryTest
{
	@Autowired
	private UserRepository userRepository;
	private UserService userService;
	private User user;

	
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		user=new User();
		user.setUserId("123");
		user.setUserName("123@gmail.com");
		user.setPassword("vis#al");
		user.setGender("male");
		user.setCountry("India");
	}

	/**
	 * 
	 */
	@Test
	public void addUserTest()
	{
		//userRepository.addUser(user);
		User fetchedUser=userRepository.findById(user.getUserId()).get();
		assertEquals("123",fetchedUser.getUserId());
	}
//	
//	@Test
//	public void updateUser()
//	{
//		// 1) Inserting a record so that we can update the inserted record
//				userRepository.addUser(user); 
//				// fetching the record which we want to update
//				User fetchedUser=userRepository.findById(user.getUserId()).get();
//				assertEquals(1,fetchedUser.getUserId());
//				// We are trying to update the designation
//				fetchedUser.setUserName("Ramesh");
//				// save the data with the change
//				userRepository.save(fetchedUser);
//				// after saving fetch the employee once again to check whether the designation got updated!!!
//				fetchedUser=userRepository.findById(user.getUserId()).get();
//				assertEquals("Ramesh",fetchedUser.getUserName());
//				
//	}
	
	

}
