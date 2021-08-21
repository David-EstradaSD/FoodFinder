package com.skilldistillery.foodfinder.controllers;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.foodfinder.entities.Address;
import com.skilldistillery.foodfinder.entities.Donor;
import com.skilldistillery.foodfinder.entities.Recipient;
import com.skilldistillery.foodfinder.entities.User;
import com.skilldistillery.foodfinder.services.AddressService;
import com.skilldistillery.foodfinder.services.AuthService;
import com.skilldistillery.foodfinder.services.DonorService;
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
	@Autowired
	DonorService donorService;
	
	@PostMapping("register")
	public User register(@RequestBody Map<String, Map> json, HttpServletResponse res, Principal principal) {
		User user = new User();
		Address address = new Address();	
		Map<String, String> addressMap = json.get("address");
		Map<String, String> userMap = json.get("user");
		
		String firstName = userMap.get("firstName");
		String lastName = userMap.get("lastName");
		String username = userMap.get("username");
		String password = userMap.get("password");
		String email = userMap.get("email");
		String role = userMap.get("role");
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setRole(role);
		String streetAddress = addressMap.get("streetAddress");
		String city = addressMap.get("city");
		String state = addressMap.get("state");
		String zip = addressMap.get("zip");
		address.setStreetAddress(streetAddress);
		address.setCity(city);
		address.setState(state);
		address.setZip(zip);
		
		
		
			if (user.equals(null)) {
			    res.setStatus(400);
			    System.out.println("In null error");
			}
	    
	    user = authService.register(user);
	    
	    if (user.getRole().equals("recipient")) {
			Recipient recipient = new Recipient();
			recipient.setUser(user);
			recipient.setAddress(addressService.create(address));
			recipService.create(recipient);
		}
//	    if (user.getRole().equals("donor")) {
//	    	Donor donor = new Donor();
//	    	donor.setUser(user);
//	    	donor.setAddress(addressService.create(address));
//	    	donorService.create(donor);
//	    }
	    return user;
	}

	@GetMapping("authenticate")
	public Principal authenticate(Principal principal) {
	    return principal;
		
	}
}
