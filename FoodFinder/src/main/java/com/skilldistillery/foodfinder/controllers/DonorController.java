package com.skilldistillery.foodfinder.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.foodfinder.entities.Donor;
import com.skilldistillery.foodfinder.services.DonorService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4242" })
public class DonorController {
	
	@Autowired
	private DonorService donorService;

	@GetMapping("donors/{category}")
	public List<Donor> index(@PathVariable String category) {
		return donorService.index(category);
	}

}
