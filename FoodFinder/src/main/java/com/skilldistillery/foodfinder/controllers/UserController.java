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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.foodfinder.entities.User;
import com.skilldistillery.foodfinder.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4242" })
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("users/{username}")
	public User getUserByUsername(@PathVariable String username, HttpServletResponse res) {
		User user = userService.userByUsername(username);
		return user;
	}

//	 GET
	@GetMapping("users")
	public List<User> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		List<User> users = userService.index();
		return users;
	}
	
	
	
	@PutMapping("users")
	public User update(@RequestBody User user, HttpServletRequest req, HttpServletResponse resp) {
//		System.out.println("made it in update");
		try {
			user = userService.update(user);
			if (user == null) {
				resp.setStatus(404);
			}
			
		} catch (Exception e) {
			resp.setStatus(400);
			user = null;
			e.printStackTrace();
		}
		return user;
	}
	
	@DeleteMapping("users/{username}")
	public void deleteUser(@PathVariable String username, HttpServletResponse resp) {
		Boolean isDeleted = userService.destroy(username);
		if (isDeleted) {
			resp.setStatus(204);
		} else {
			resp.setStatus(404);
		}
	}

}
