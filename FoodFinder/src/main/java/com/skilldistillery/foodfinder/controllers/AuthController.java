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
	RecipientService recipServ;
	@Autowired
	AddressService addyServ;
	
	@PostMapping("register")
	public User register(@RequestBody User user, @RequestBody Address address, HttpServletResponse res) {
		
		
	    try {
			if (user == null || address == null) {
			    res.setStatus(400);
			    System.out.println("In null error");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    user = authService.register(user);
	    
	    if (user.getRole().equals("recipient")) {
			Recipient recipient = new Recipient();
			
			recipient.setUser(user);
			address = addyServ.create(address);
			recipient.setAddress(address);
			recipServ.create(recipient);

		}


	    return user;
	}

	@GetMapping("authenticate")
	public Principal authenticate(Principal principal) {
	    return principal;
		
	}
}
