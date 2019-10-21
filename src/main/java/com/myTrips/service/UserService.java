package com.myTrips.service;

import com.myTrips.model.User;
import com.myTrips.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

/*
UserService class makes use of registerNewUserAccount method from IUserService interface.
Method registerNewUserAccount is implemented in order to create a new user account will all detailes
provided in the input fields of register page.
The username must be unique and shouldn't  match any already existing one ->then a message error will be displayed.
If username validation pass the a new user is created with an encoded password.
 */
@Service
public class UserService implements IUserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@Transactional
	@Override
	public User registerNewUserAccount(User account)throws UsernameExistsException {
		
		if (usernameExist(account.getUsername())) {
			throw new UsernameExistsException(
					"There is an account with that username: "
							+  account.getUsername());
		}
  
		User user=new User();
		user.setUsername(account.getUsername());
		user.setFirstName(account.getFirstName());
		user.setLastName(account.getLastName());
		user.setAddress(account.getAddress());
		user.setPhone(account.getPhone());
		user.setCity(account.getCity());
		user.setPassword(pwdEncoder.encode(account.getPassword()));//password encoding
		
		return userRepository.save(user);//new user is saved to database
	}

	private boolean usernameExist(String username) {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			return true;
		}
		return false;
	}
	
	
}
