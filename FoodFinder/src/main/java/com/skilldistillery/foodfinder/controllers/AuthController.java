package com.skilldistillery.foodfinder.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.foodfinder.entities.Address;
import com.skilldistillery.foodfinder.entities.Recipient;
import com.skilldistillery.foodfinder.entities.User;
import com.skilldistillery.foodfinder.services.AddressService;
import com.skilldistillery.foodfinder.services.AuthService;
import com.skilldistillery.foodfinder.services.RecipientService;

@RestController
@CrossOrigin({"*", "http://localhost:4202"})
public class AuthController {

	@Autowired
	private AuthService authService;
	@Autowired
	RecipientService recipService;
	@Autowired
	AddressService addressService;
	
	@PostMapping("register")
	public User register(@RequestBody User user, HttpServletResponse res, Principal principal) {
		
			if (user == null) {
			    res.setStatus(400);
			    System.out.println("In null error");
			}
	    
	    user = authService.register(user);
	    
	    if (user.getRole().equals("recipient")) {
			Recipient recipient = new Recipient();
			recipient.setUser(user);
			Address address = new Address();
			recipient.setAddress(addressService.create(address));
			recipService.create(recipient);
		}
	    return user;
	}

	@GetMapping("authenticate")
	public Principal authenticate(Principal principal) {
	    return principal;
		
	}
}
