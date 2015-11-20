package com.certuit.saml2.logic;
import com.certuit.common.security.exception.UserNotFoundException;
import com.certuit.saml2.entity.User;

public class UserManager {
	
	/**
	 * Get an user   
	 * 
	 * @param userName
	 * @param password
	 * @return user 
	 * @throws UserNotFoundException
	 */
	public User getUser(String userName, String password) throws UserNotFoundException {				
		
		//TODO:Stub Implementation
		System.out.println(userName+"_______________________________");
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setEmployeeId(56789);
		
		return user;
	}

}
