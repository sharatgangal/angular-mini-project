package com.dxc.bookshelf.user.tokengenerator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.dxc.bookshelf.user.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author sgangal2
 *
 */
public class TokenGeneratorImpl implements TokenGenerator {

	@Override
	    //Token can be generated for both userid and password.
		//we are declaring both as string.

	public Map<String, String> generateToken(User user) {
		String jwtToken=Jwts.builder().setId(user.getUserId()).setSubject(user.getPassword()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact();
	    System.out.println(jwtToken);
		Map<String,String> tokenMap=new HashMap<>();
		//Tokens are generated,stored in ordered list.

		tokenMap.put("token", jwtToken);
		//for adding or storing the token,we are using put method
		tokenMap.put("message", "User successfully logged in");
        return tokenMap;
	}

}
