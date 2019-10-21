package com.myTrips;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;


@SpringBootApplication
//		(exclude = { SecurityAutoConfiguration.class })
public class MyTripsApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(MyTripsApplication.class, args);
	}
	
}
