package com.skilldistillery.foodfinder.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodfinder.entities.Rating;
import com.skilldistillery.foodfinder.entities.RatingId;
import com.skilldistillery.foodfinder.entities.Recipient;
import com.skilldistillery.foodfinder.entities.ServiceLocation;
import com.skilldistillery.foodfinder.repositories.RatingRepository;
import com.skilldistillery.foodfinder.repositories.RecipientRepository;
import com.skilldistillery.foodfinder.repositories.ServiceLocationRepository;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepo;
	@Autowired
	private RecipientRepository recRepo;
	@Autowired
	private ServiceLocationRepository serviceLocationRepo;

	@Override
	public List<Rating> index(String locationName) {
		return ratingRepo.findByServiceLocation_LocationName(locationName);
	}

	@Override
	public Rating create(String username, Rating rating, int locationId, int recipientId) {
		ServiceLocation location = serviceLocationRepo.queryById(locationId);
		rating.setServiceLocation(location);
		RatingId ratingId = new RatingId(locationId, recipientId);
		rating.setId(ratingId);
		Recipient recipient = recRepo.findByUser_Username(username);
		rating.setRecipient(recipient);
		try {
			ratingRepo.saveAndFlush(rating);
		} catch (Exception e) {
			e.printStackTrace();
			rating = null;
		}
		return rating;
	}

	@Override
	public Rating update(String username, int locationId, int recipientId, Rating rating) {
		Recipient recipient = recRepo.findByUser_Username(username); 
		RatingId ratingId = new RatingId(locationId, recipientId);
		
		Optional<Rating> ratingOpt = ratingRepo.findById(ratingId);
		Rating managedRating = null;
		if (ratingOpt.isPresent()) {
			managedRating = ratingOpt.get();
			if (!(managedRating.getRecipient().getId() == recipient.getId())) {
				managedRating = null;
			}
		}
		if (managedRating != null) {
			managedRating.setRating(rating.getRating());
			managedRating.setRecipient(rating.getRecipient());
			managedRating.setServiceLocation(rating.getServiceLocation());
			ratingRepo.saveAndFlush(managedRating);
		}
		return managedRating;
	}

	@Override
	public boolean delete(String username, int locationId, int recipientId) {
		Recipient recipient = recRepo.findByUser_Username(username);
		RatingId ratingId = new RatingId(locationId, recipientId);
		boolean deleted = false;
		
		Optional<Rating> ratingOpt = ratingRepo.findById(ratingId); // RatingId
		if (ratingOpt.isPresent()) {
			Rating rating = ratingOpt.get();
			if (!(rating.getRecipient().getId() == recipient.getId())) {
				rating = null;
			}
			if (rating != null) {
				ratingRepo.delete(rating);
				deleted = true;
			}
		}
		return deleted;
		
	}
}
