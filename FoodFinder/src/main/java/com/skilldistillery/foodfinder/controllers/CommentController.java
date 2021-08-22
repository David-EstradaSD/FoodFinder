package com.skilldistillery.foodfinder.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.foodfinder.entities.Comment;
import com.skilldistillery.foodfinder.repositories.CommentRepository;
import com.skilldistillery.foodfinder.services.CommentService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4242" })
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CommentRepository commentRepo;

	@GetMapping("comments/{locationName}")
	public List<Comment> index(@PathVariable String locationName) {
		return commentService.index(locationName);
	}

	@PostMapping("comments/{locationId}")
	public Comment create(
			HttpServletRequest req, 
			HttpServletResponse res,
			@RequestBody Comment comment,
			Principal principal, @PathVariable int locationId ){
		comment = commentService.create(principal.getName(), comment, locationId);
		try {
			if (comment == null) {
				res.setStatus(404);
				System.out.println(comment);
				
			} else {
				res.setStatus(201); // Created
				StringBuffer url = req.getRequestURL();
				url.append("/").append(comment.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			res.setStatus(400); // Bad Request
			comment = null;
		}
		return comment;
	}

//	@PutMapping("comments/{locationId}/{commentId}")
//	public Comment update(HttpServletRequest req, HttpServletResponse res,
//			@RequestBody Comment comment, 
//			Principal principal, 
//			@PathVariable int locationId, 
//			@PathVariable int commentId) {
//		try {
//			comment = commentService.update(principal.getName(), comment, locationId, commentId);
//		} catch (Exception e) {
//			e.printStackTrace();
//			res.setStatus(400);
//			comment = null;
//		}
//		if (comment == null) {
//			res.setStatus(404);
//		}
//		return comment;
//	}
	
	@DeleteMapping("comments/{commentId}")
	public void destroy(HttpServletRequest req,
			HttpServletResponse res, 
			@PathVariable int commentId, 
 			Principal principal) {
		try {
			boolean deleted = commentService.delete(principal.getName(), commentId);
			if (deleted) {
				res.setStatus(204); // No Content
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400); 
		}
	}
	
	
}