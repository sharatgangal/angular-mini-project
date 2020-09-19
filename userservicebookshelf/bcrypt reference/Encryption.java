package com.dxc.cricketmatch.user.bcrypt;

/**
 * @author lenovo
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
