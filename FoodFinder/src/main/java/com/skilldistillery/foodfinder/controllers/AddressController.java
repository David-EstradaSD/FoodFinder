package com.skilldistillery.foodfinder.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.foodfinder.entities.Address;
import com.skilldistillery.foodfinder.services.AddressService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4242"})
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("addresses")
	public List<Address> getAddressList(HttpServletResponse res) {
		List <Address> addresses = addressService.index();
		return addresses;
	}

}
