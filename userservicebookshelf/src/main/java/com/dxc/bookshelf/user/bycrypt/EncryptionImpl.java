package com.dxc.bookshelf.user.bycrypt;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * @author sgangal2
 *
 */
@Component
public class EncryptionImpl implements Encryption {

	@Override
	public String encoder(String password) {
//		String round = bCrypt.gensalt(10);
		String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		System.out.println("passHash "+hashPassword);
		return hashPassword;
	}

	@Override
	public boolean match(String password, String hashedPassword) {
		return BCrypt.checkpw(password, hashedPassword);
	}

}
