package com.skilldistillery.foodfinder.services;

import java.util.List;

import com.skilldistillery.foodfinder.entities.Comment;

public interface CommentService {

	public List<Comment> index(String locationName);

	public Comment create(String username, Comment comment, int locationId);
	
//	public Comment update(String username, Comment comment, int locationId, int commentId);
	
	public boolean delete(String username, int commentId);

}
