package com.myTrips.controller;


import com.myTrips.model.User;
import com.myTrips.model.Trip;
import com.myTrips.persistance.UserRepository;
import com.myTrips.persistance.TripRepository;
import com.myTrips.service.FileUploadService;
import com.myTrips.service.IUserService;
import com.myTrips.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
This class is responsible for taking all request coming from profile page.
On profile page the user can update his profile details.

 */

@Controller //This means that this class is a Controller
@RequestMapping("/") //this means URL's starts with /trips after app path
public class UserController {
	
	@Autowired //this means to get the bean called userRepository	which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;
	@Autowired
	private TripRepository tripRepository;
	
	@Autowired
	private FileUploadService fileService;
//	@Autowired
//	private UserService userService;
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	
	
	@GetMapping("/profile")//show profile page
	public String showProfile(Model model){
		User registered = userRepository.findByUsername(myUserDetailsService.getUsername());
		model.addAttribute("user",registered);
		model.addAttribute("username",registered.getUsername());
		model.addAttribute("password",registered.getPassword());
		model.addAttribute("firstName", registered.getFirstName());
		model.addAttribute("lastName", registered.getLastName());
		model.addAttribute("phone", registered.getPhone());
		model.addAttribute("city", registered.getCity());
		model.addAttribute("address", registered.getAddress());
		return "profile";
	}


	@PostMapping("/profile")
	public String submitProfile(@Valid @ModelAttribute("user")User user, BindingResult bindingResult, Model model){
		User registered = userRepository.findByUsername(myUserDetailsService.getUsername());

		if(bindingResult.hasErrors()){
			model.addAttribute("username",registered.getUsername());
			return "profile";
		}
		model.addAttribute("username",registered.getUsername());
		model.addAttribute("password",registered.getPassword());
		model.addAttribute("firsName", user.getFirstName());
		model.addAttribute("lastName", user.getLastName());
		model.addAttribute("phone", user.getPhone());
		model.addAttribute("city", user.getCity());
		model.addAttribute("address", user.getAddress());
		registered.setFirstName(user.getFirstName());
		registered.setLastName(user.getLastName());
		registered.setUsername(user.getUsername());
		registered.setPassword(user.getPassword());
		registered.setPhone(user.getPhone());
		registered.setCity(user.getCity());
		registered.setAddress(user.getAddress());
		userRepository.save(registered);
		return "redirect:/trips-page";
	}
	
	@ExceptionHandler(IUserService.UsernameExistsException.class)
	public String handleError(IUserService.UsernameExistsException e) {
		
		return "redirect:/error.html";
	}
}
