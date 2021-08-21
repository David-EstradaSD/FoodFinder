package com.skilldistillery.foodfinder.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PutMapping("donors/update/{donorId}")
	public Donor update(HttpServletRequest req, HttpServletResponse res, @PathVariable int donorId,
			@RequestBody Donor donor, Principal principal) {
		try {
			donor = donorService.update(donor, donorId, principal.getName());
		} catch (Exception e) {
			res.setStatus(400);
			donor = null;
		}
		if (donor == null) {
			res.setStatus(404);
		}
		return donor;
		
	}
	

}
