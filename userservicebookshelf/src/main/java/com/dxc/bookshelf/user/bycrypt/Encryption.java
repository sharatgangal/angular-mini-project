package com.dxc.bookshelf.user.bycrypt;

/**
 * @author 
 *
 */
public interface Encryption {

	/**
	 * @param password
	 * @return hashedPassword
	 */
	public String encoder(String password);
	
	/**
	 * @param password
	 * @param hashedPassword
	 * @return boolean
	 */
	public boolean match(String password, String hashedPassword);
}
