package com.skilldistillery.foodfinder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.foodfinder.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

//	List<Rating> findByUser_Username(String username);

	List<Comment> findByServiceLocation_LocationName(String locationName);

//	Comment findByIdAndUser_Username(int cid, String username);
	
	Comment queryById(int cid);
	

}
