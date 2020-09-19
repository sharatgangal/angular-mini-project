package com.dxc.cricketmatch.user.bcrypt;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * @author lenovo
 *
 */

@Component
public class EncryptionImpl implements Encryption{
	
	@Override
	public boolean match(String password, String hashed) {
		BCrypt bCrypt = new BCrypt();
		return bCrypt.checkpw(password, hashed);
	}

	@Override
	public String encoder(String password) {
		BCrypt bCrypt = new BCrypt();
//		String round = bCrypt.gensalt(10);
		String hashPassword = bCrypt.hashpw(password, bCrypt.gensalt());
		System.out.println("passHash "+hashPassword);
		return hashPassword;
	}
}
