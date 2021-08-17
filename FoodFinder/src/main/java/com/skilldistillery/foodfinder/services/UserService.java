package com.skilldistillery.foodfinder.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.foodfinder.entities.User;
import com.skilldistillery.foodfinder.repositories.UserRepository;

public interface UserService {
	
	User userByUsername(String username); 

}
