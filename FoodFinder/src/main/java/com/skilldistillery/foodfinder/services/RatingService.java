package com.skilldistillery.foodfinder.services;

import java.util.List;

import com.skilldistillery.foodfinder.entities.Rating;
import com.skilldistillery.foodfinder.entities.Recipient;
import com.skilldistillery.foodfinder.entities.ServiceLocation;

public interface RatingService {

	public List<Rating> index(String locationName);

	public Rating create(String username, Rating rating, int locationId, int recipientId);
	
	public Rating update(String username, int locationId, int recipientId, Rating rating);
	
	public boolean delete(String username, int locationId, int recipientId);

}
