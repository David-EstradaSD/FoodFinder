package com.skilldistillery.foodfinder.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.foodfinder.entities.ServiceLocation;
import com.skilldistillery.foodfinder.entities.User;
import com.skilldistillery.foodfinder.repositories.ServiceLocationRepository;
import com.skilldistillery.foodfinder.services.ServiceLocationService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4242"})
public class ServiceLocationController {
	
	@Autowired
	private ServiceLocationService locationService;
	
	@Autowired
	private ServiceLocationRepository locationRepo;
	
	@GetMapping("service-locations")
	public List<ServiceLocation> index(HttpServletRequest req, HttpServletResponse res) {
		return locationService.index();
	}
	
	@GetMapping("service-locations-user")
	public List<ServiceLocation> userIndex(
//			@PathVariable User username, 
			HttpServletRequest req, 
			HttpServletResponse res, 
			Principal principal) {
		return locationService.index(principal.getName());
	}

}
