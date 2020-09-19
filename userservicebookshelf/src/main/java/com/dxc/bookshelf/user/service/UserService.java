package com.dxc.bookshelf.user.service;


import com.dxc.bookshelf.user.exception.UserExistsExeception;
import com.dxc.bookshelf.user.exception.UserNotFoundExeception;
import com.dxc.bookshelf.user.model.User;

/**
 * @author sgangal2
 *
 */
public interface UserService {
	    
	    //This is for adding users.
		//if the user is already exists,throws an error.

	/**
	 * @param user
	 * @return User object
	 * @throws UserExistsExeception
	 */
	public User addUser(User user) throws UserExistsExeception ;
	    
	    /**
	     * @param userId
	     * @return boolean
	     * @throws UserNotFoundExeception
	     */
	    //This is for deleting user.
		//if the user is not exists,throws an error.

	public boolean deleteUser(String userId) throws UserNotFoundExeception;
	     
	     /**
	     * @param userId
	     * @param userToUpdate
	     * @return User Object
	     * @throws UserNotFoundExeception
	     */
	    //This is for updating the user.
		//if the user is not exists,throws an error.

	public User updateUser(String userId,User userToUpdate) throws UserNotFoundExeception;
	    
	    /**
	     * @param userId
	     * @return User Object
	     * @throws UserNotFoundExeception
	     */
	    //This is for finding the user.
		//if the user is not exists,throws an error.

	public User findUser(String userId) throws UserNotFoundExeception;
	
	/**
	 * @param userId
	 * @return boolean
	 * @throws UserNotFoundExeception
	 */
	public boolean isUserExists(String userId) throws UserNotFoundExeception;
	     /**
	     * @param userId
	     * @param passowrd
	     * @return User object
	     * @throws UserNotFoundExeception
	     */
	    //This is for login.
		//if the user trying to login with the credentials,which is not 
		//stored in the database throws an error.
	public User Login(String userId, String passowrd) throws UserNotFoundExeception;

}
