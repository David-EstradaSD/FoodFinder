package com.skilldistillery.foodfinder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.foodfinder.entities.Donor;

public interface DonorRepository extends JpaRepository<Donor, Integer> {
	
	Donor findByUser_Username(String username);
	
	List<Donor> findByCategory(String category);
	
	Donor findByIdAndUser_Username(int donorId, String username);


}
