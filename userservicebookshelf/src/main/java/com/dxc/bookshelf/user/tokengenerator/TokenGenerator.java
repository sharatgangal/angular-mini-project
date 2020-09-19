package com.dxc.bookshelf.user.tokengenerator;

import java.util.Map;

import com.dxc.bookshelf.user.model.User;



// An interface created for token generation
// We need to write a class which will implement this interface
/**
 * @author sgangal2
 *
 */
public interface TokenGenerator {

	/**
	 * @param user
	 * @return Map<String , String>
	 */
	public Map<String,String> generateToken(User user);
}
