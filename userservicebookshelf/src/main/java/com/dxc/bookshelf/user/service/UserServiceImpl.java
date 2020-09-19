package com.dxc.bookshelf.user.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.bookshelf.user.bycrypt.Encryption;
import com.dxc.bookshelf.user.exception.UserExistsExeception;
import com.dxc.bookshelf.user.exception.UserNotFoundExeception;
import com.dxc.bookshelf.user.model.User;
import com.dxc.bookshelf.user.repository.UserRepository;

/**
 * @author sgangal2
 *
 */
@Service
//It is used to mark the class as a service provider.


public class UserServiceImpl implements UserService {

	@Autowired
	// @Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished.
	//can be used to autowire bean on the setter method ,
	//Constructors,properties
	private UserRepository userRepository;
	
	@Autowired
	private Encryption encrypt;
	
	@Override
	//If programmer makes any mistake such as wrong method name, wrong parameter types while overriding, you would get a compile time error
   //to overcome this we are using @Override.

	public User addUser(User user) throws UserExistsExeception {
		
		boolean isUserExists=false;
		try {
			isUserExists = isUserExists(user.getUserId());
		} catch (UserNotFoundExeception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isUserExists)
		{
			throw new UserExistsExeception();
		}
		else
		{
			user.setPassword(encrypt.encoder(user.getPassword()));
			return userRepository.save(user);
		}

	}
    //For deleting user
	@Override
	public boolean deleteUser(String userId) throws UserNotFoundExeception {
		boolean isUserExists=isUserExists(userId);
		if(isUserExists)
		{
			userRepository.deleteById(userId);
			return true;
		}
		else
		{
			throw new UserNotFoundExeception();
		}
		
	}
     //For updating user
	@Override
	public User updateUser(String userId, User userToUpdate) throws UserNotFoundExeception {
		boolean isUserExists=isUserExists(userId);
		if(isUserExists)
		{
		userRepository.deleteById(userId);
		userToUpdate.setPassword(encrypt.encoder(userToUpdate.getPassword()));
		return userRepository.save(userToUpdate);
		}
		else
		{
			return null;
		}
	
	}
    //For finding user
	@Override
	public User findUser(String userId) throws UserNotFoundExeception {
		User user=new User();
		try {
			user =userRepository.findById(userId).get();	
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean isUserExists(String userId) throws UserNotFoundExeception{
		Optional<User> user= userRepository.findById(userId);
		if(user.isPresent())
		{
			return true;
		}
		else
			throw new UserNotFoundExeception();
	}
    //For login with valid credentials
	@Override
	public User Login(String userId, String password) throws UserNotFoundExeception {
		boolean isUserPresent=isUserExists(userId);
		if(isUserPresent)
		{
			User userFound=findUser(userId);
			if(encrypt.match(password, userFound.getPassword()))
			{
				return userFound;
			}
			else
			{
				return null;
			}
		}
		else
		{
			throw new UserNotFoundExeception();
		}
	}

	

}
