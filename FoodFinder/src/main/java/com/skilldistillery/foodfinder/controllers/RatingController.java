package com.skilldistillery.foodfinder.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.foodfinder.entities.Rating;
import com.skilldistillery.foodfinder.entities.Recipient;
import com.skilldistillery.foodfinder.services.RatingService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4242" })
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@GetMapping("ratings/{locationName}")
	public List<Rating> index(@PathVariable String locationName) {
		return ratingService.index(locationName);
	}

	@PostMapping("ratings/{locationName}")
	public Rating create(@PathVariable String locationName, HttpServletRequest req, HttpServletResponse res,
			@RequestBody Rating rating, Principal principal, Recipient recipient) {
		rating = ratingService.create(recipient, rating, principal.getName());
		try {
			if (rating == null) {
				res.setStatus(404);
			} else {
				res.setStatus(201); // Created
				StringBuffer url = req.getRequestURL();
				url.append("/").append(rating.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			res.setStatus(400); // Bad Request
			rating = null;
		}
		return rating;
	}

	@PutMapping("ratings/{rid}")
	public Rating update(HttpServletRequest req, HttpServletResponse res, @PathVariable int rid,
			@RequestBody Rating rating, Principal principal) {
		System.out.println(rating);
		try {
			rating = ratingService.update(principal.getName(), rating);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			rating = null;
		}
		if (rating == null) {
			res.setStatus(404);
		}
		return rating;
	}
}