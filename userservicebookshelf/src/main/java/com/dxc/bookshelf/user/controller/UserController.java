package com.dxc.bookshelf.user.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.bookshelf.user.exception.UserExistsExeception;
import com.dxc.bookshelf.user.exception.UserNotFoundExeception;
import com.dxc.bookshelf.user.model.User;
import com.dxc.bookshelf.user.service.UserService;
import com.dxc.bookshelf.user.tokengenerator.TokenGenerator;
import com.dxc.bookshelf.user.tokengenerator.TokenGeneratorImpl;




/**
 * @author sgangal2
 *
 */
@RestController
//@RestController is a convenience annotation for creating Restful controllers. 

@CrossOrigin(origins="http://localhost:4200")
//This @CrossOrigin annotation enables cross-origin resource sharing only for this specific method

@RequestMapping("/api/v1/user")
//This annotation maps HTTP requests to handler methods of MVC and REST controllers.

public class UserController {

	private HttpSession session;
	private TokenGenerator token;
	
	
	@Autowired
	private UserService userService;
	        //This is used to adding users.
			// http://localhost:9999/api/v1/user/addUser

	/**
	 * @param user
	 * @return ResponseEntity
	 */
	@PostMapping("/addUser")
	//@PostMapping annotation maps HTTP POST requests onto specific handler methods.
	//@RequestBody annotation binds request body to method parameters.
	public ResponseEntity<?> addUser(@RequestBody User user)
	{
		try {
			User addeduser=userService.addUser(user);
			if(addeduser!=null)
			{
				return new ResponseEntity<>(addeduser,HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (UserExistsExeception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	    /**
	     * @param userId
	     * @param password
	     * @param session
	     * @return ResponseEntity
	     * @throws UserNotFoundExeception
	     */
	    //This is used to getting  user.
		// http://localhost:9999/api/v1/user/login/{userId},{password}

	@GetMapping("/login/{userId},{password}")
	//Annotation for mapping HTTP GET requests onto specific handler methods. 

	public  ResponseEntity<?>  validateUser(@PathVariable String userId,@PathVariable String password,HttpSession session) throws UserNotFoundExeception
	{
		boolean isUserExists=userService.isUserExists(userId);
		
		if(isUserExists)
		{	User userPresent=userService.Login(userId, password);
			if(userPresent!=null);
			{
				session.setAttribute("user_details", userPresent.getUserId());
				TokenGenerator tokenGenerator=new TokenGeneratorImpl();
				Map<String, String> token= tokenGenerator.generateToken(userPresent);
				return new ResponseEntity<>(token,HttpStatus.OK);	
			}
		}
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	         /**
	         * @param userId
	         * @param updateUser
	         * @return ResponseEntity
	         * 
	         */
	        //This is used to updating the users.
			// http://localhost:9999/api/v1/user/updateUser/{userId}

	@PutMapping("/updateUser/{userId}")
	//@PutMapping annotation for mapping HTTP PUT requests onto specific handler methods.
	//@PathVariable is a Spring annotation which indicates that a method parameter 
		//should be bound to a URI template variable
		

	public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody User updateUser)
    {
	 try {
		 User updatedUser=userService.updateUser(userId, updateUser);
		 if(updatedUser!=null)
		 {
			 return new ResponseEntity<>(updatedUser,HttpStatus.OK);
		 }
	} catch (UserNotFoundExeception e) {	
	}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
	
}
