package com.myTrips.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
/*
Defining a simple BCryptPasswordEncoder by implementing PasswordEncoder which provides
 encode and matches useful methods.
 BCrypt, will internally generate a random salt instead.
 The BCrypt algorithm generates a String of length 60.
 */
@Service
public class BCryptPasswordEncoder implements PasswordEncoder {
	
	
		@Override
		public String encode(CharSequence charSequence) {
			return BCrypt.hashpw(charSequence.toString(), BCrypt.gensalt(4));
			
		}
		
		@Override
		public boolean matches(CharSequence charSequence, String encodedPassword) {
			return BCrypt.checkpw(charSequence.toString(), encodedPassword);
		}
	}

