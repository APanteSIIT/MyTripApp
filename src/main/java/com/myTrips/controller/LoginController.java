package com.myTrips.controller;


import com.myTrips.model.User;

import com.myTrips.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
/*
This class is responsible for taking requests from login and register page.
Register page displays multiple fields:username,first name,last name,password,city,address,phone wich
has to pass validation criteria in order to create a new account.
Login page displays 2 fields:username and password that must mach an already existing account.
If the inputs for username and password are no mach in database then the user has the posibility to
register by clicking the ling on the login page.
 */

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/login")//returns login page
	public String login() {
		return "login";
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)//return register page
	public String showRegistrationForm(User user,WebRequest request,Model model) {

		return "register";
	}
	@ExceptionHandler
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUserAccount(@Valid @ModelAttribute("user") User account, BindingResult result,
									  WebRequest request, Errors errors) {
	
		User registered = new User();
		if (!result.hasErrors()) {
				registered = createUserAccount(account, result);//creating a new account with the filed in inputs
		}
		if (registered == null) {
			result.rejectValue("username", "message.regError");//dislays an error message if the inputs are null
		}
		if (result.hasErrors()) {
			return "register";
		}
		else {
			
			return "redirect:/login";
		}
	}
	/*
	This method creates a new user by checking if there is no other user account with
	same username and password.If there is one then a new page will display a notification message
	saying that the account already exists.
	
	 */
	private User createUserAccount(User account, BindingResult result) {
		User registered = null;
		try {
			registered = userService.registerNewUserAccount(account);
		} catch (com.myTrips.service.IUserService.UsernameExistsException e) {//throws an UsernameExistException if
			// the inputs are already existing
			return null;
		}
		return registered;
	}
}
