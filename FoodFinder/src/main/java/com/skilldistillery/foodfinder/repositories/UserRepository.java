package com.skilldistillery.foodfinder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.foodfinder.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username); 

}
