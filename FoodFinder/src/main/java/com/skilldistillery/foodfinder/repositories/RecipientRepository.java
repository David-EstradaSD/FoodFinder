package com.skilldistillery.foodfinder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.foodfinder.entities.Recipient;
import com.skilldistillery.foodfinder.entities.User;

public interface RecipientRepository extends JpaRepository<Recipient, Integer> {

	Recipient findByUser_Username(String username);

}
