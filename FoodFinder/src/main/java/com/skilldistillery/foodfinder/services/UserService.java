package com.skilldistillery.foodfinder.services;

import java.util.List;

import com.skilldistillery.foodfinder.entities.User;

public interface UserService {
	
	User userByUsername(String username); 
	
	public List<User> index();

	public User create(User user);

    public User update(User user);

    public boolean destroy(String username);
}
