package com.skilldistillery.foodfinder.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodfinder.entities.User;
import com.skilldistillery.foodfinder.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User userByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> index() {
		return userRepo.findAll();
	}

	@Override
	public User create(User user) {
		return userRepo.saveAndFlush(user);
	}

	@Override
	public User update(User user, String username) {
		User dbUser = userRepo.findByUsername(username);
		dbUser = user;
		return userRepo.saveAndFlush(dbUser);
	}

	@Override
	public boolean destroy(String username) {
		Boolean deleted = false;
		
		User user = userRepo.findByUsername(username);
		
		if (user != null) {
			int id = user.getId();
				userRepo.deleteById(id);
				deleted = true;
			
		}
		return deleted;	
		}

	@Override
	public User disableUser(User user, String username) {
		User dbUser = userRepo.findByUsername(username);
		dbUser = user;
		dbUser.setEnabled(false);
		return userRepo.saveAndFlush(dbUser);
	}

	@Override
	public User enableUser(User user, String username) {
		User dbUser = userRepo.findByUsername(username);
		dbUser = user;
		dbUser.setEnabled(true);
		return userRepo.saveAndFlush(dbUser);
	}
	
	
}
