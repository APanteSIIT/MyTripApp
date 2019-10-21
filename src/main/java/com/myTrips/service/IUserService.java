package com.myTrips.service;

import com.myTrips.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public interface IUserService {
	
	User registerNewUserAccount(User accountDto) throws UsernameExistsException;
	
	// this is a class that creates a custom exception called UsernameExistsException which extends RuntimeException
	//when this class is called it generates a specific error message
	class UsernameExistsException extends RuntimeException {
		public UsernameExistsException(String message) {
			super(message);
		}
	}
	
}
