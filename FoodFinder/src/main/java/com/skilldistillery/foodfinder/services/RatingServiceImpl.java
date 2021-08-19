package com.skilldistillery.foodfinder.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodfinder.entities.Rating;
import com.skilldistillery.foodfinder.entities.Recipient;
import com.skilldistillery.foodfinder.repositories.RatingRepository;
import com.skilldistillery.foodfinder.repositories.RecipientRepository;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepo;
	@Autowired
	private RecipientRepository recRepo;

	@Override
	public List<Rating> userIndex(String username) {
//		return ratingRepo.findByUser_Username(username);
		return null;
	}

	@Override
	public List<Rating> index(String locationName) {
		return ratingRepo.findByServiceLocation_LocationName(locationName);
	}

	@Override
	public Rating create(Recipient recipient, Rating rating, String username) {
		recipient = recRepo.findByUser_Username(username);

		rating.setRecipient(recipient);
		try {
			ratingRepo.saveAndFlush(rating);
		} catch (Exception e) {
			rating = null;
		}
		return rating;
	}

	@Override
	public Rating update(String username, Rating rating) {
		Recipient recipient = recRepo.findByUser_Username(username); 
		Optional<Rating> ratingOpt = ratingRepo.findById(rating.getId());
		Rating managedRating = null;
		if (ratingOpt.isPresent()) {
			managedRating = ratingOpt.get();
			if (!(managedRating.getRecipient().getId() == recipient.getId())) {
				managedRating = null;
			}
		}
		if (managedRating != null) {
			managedRating.setId(rating.getId());
			managedRating.setRating(rating.getRating());
			managedRating.setRecipient(rating.getRecipient());
			managedRating.setServiceLocation(rating.getServiceLocation());
			ratingRepo.saveAndFlush(managedRating);
		}
		return managedRating;
	}
}
