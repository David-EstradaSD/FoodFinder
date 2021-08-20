package com.skilldistillery.foodfinder.services;

import java.util.List;

import com.skilldistillery.foodfinder.entities.Rating;
import com.skilldistillery.foodfinder.entities.Recipient;

public interface RatingService {

	public List<Rating> index(String locationName);

	public Rating create(Recipient recipient, Rating rating, String username);

	public Rating update(String username, Rating rating);

}
