package com.myTrips.service;

import com.myTrips.model.User;
import com.myTrips.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
/*
The UserDetailsService interface is used to retrieve user-related data. It has one method
named loadUserByUsername() which can be overridden to customize the process of finding the user.
The class called MyUserDetailsService is implementing the UserDetailsService method and overrides it.
In this method, we retrieve the User object using the DAO, and if it exists, wrap it into a
 User object, which implements UserDetails, and returns it.
 */

@Service
@Transactional
public class MyUserDetailsService  implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) throw new UsernameNotFoundException(username);
		
		

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
		grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}
	
	//This method returns the name of the current logged in user
//	The object returned by getContext() is an instance of the SecurityContext interface.
//	This is the object that is stored in a thread-local storage.
	public String getUsername() {
		SecurityContext context = SecurityContextHolder.getContext();
		UserDetails userDetails = (UserDetails) context.getAuthentication().getPrincipal();
		return userDetails.getUsername();
	}
	
	}

	