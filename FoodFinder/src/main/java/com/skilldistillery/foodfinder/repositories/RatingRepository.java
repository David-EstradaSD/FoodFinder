package com.skilldistillery.foodfinder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.foodfinder.entities.Rating;
import com.skilldistillery.foodfinder.entities.RatingId;

public interface RatingRepository extends JpaRepository<Rating, RatingId> {

//	List<Rating> findByUser_Username(String username);

	List<Rating> findByServiceLocation_LocationName(String locationName);

	Rating findByIdAndRecipient_User_Username(int rid, String username);

}
