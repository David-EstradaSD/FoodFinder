package com.skilldistillery.foodfinder.services;

import java.util.List;

import com.skilldistillery.foodfinder.entities.User;

public interface UserService {
	
	User userByUsername(String username); 
	
	public List<User> index();

	public User create(User user);

    public User update(User user, String username);

    public boolean destroy(String username);
    
    public User disableUser(User user, String username);
    
    public User enableUser(User user, String username);
}
