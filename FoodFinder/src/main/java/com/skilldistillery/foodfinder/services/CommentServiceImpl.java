package com.skilldistillery.foodfinder.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodfinder.entities.Comment;
import com.skilldistillery.foodfinder.entities.Recipient;
import com.skilldistillery.foodfinder.entities.ServiceLocation;
import com.skilldistillery.foodfinder.repositories.CommentRepository;
import com.skilldistillery.foodfinder.repositories.RecipientRepository;
import com.skilldistillery.foodfinder.repositories.ServiceLocationRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private RecipientRepository recRepo;
	@Autowired
	private ServiceLocationRepository serviceLocationRepo;

	@Override
	public List<Comment> index(String locationName) {
		return commentRepo.findByServiceLocation_LocationName(locationName);
	}

	@Override
	public Comment create(String username, Comment comment, int locationId) {
		ServiceLocation location = serviceLocationRepo.queryById(locationId);
		comment.setServiceLocation(location);
		Recipient recipient = recRepo.findByUser_Username(username);
		comment.setRecipient(recipient);
		try {
			commentRepo.saveAndFlush(comment);
		} catch (Exception e) {
			e.printStackTrace();
			comment = null;
		}
		return comment;
	}

//	@Override
//	public Comment update(String username, Comment comment, int locationId, int commentId) {
//		ServiceLocation location = serviceLocationRepo.queryById(locationId);
//		comment.setServiceLocation(location);
//		Recipient recipient = recRepo.findByUser_Username(username);
//		comment.setRecipient(recipient);
//		
//		List <Comment> = recipient.getComments();                                 // need to make a list of comments and iterate thru it
//		
//		if (managedComment != null) {
//			managedComment.setComment(comment.getComment());
//			managedComment.setRecipient(comment.getRecipient());
//			managedComment.setServiceLocation(comment.getServiceLocation());
//			commentRepo.saveAndFlush(managedComment);
//		}
//		return managedComment;
//	}

	@Override
	public boolean delete(String username, int commentId) {
		
		try {
			commentRepo.deleteById(commentId);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

//	@Override
//	public Comment update(String username, Comment comment, int locationId, int commentId) {                  // delete me after making list of comments and iterating through it (above)
//		// TODO Auto-generated method stub
//		return null;
//	}	
}
	
