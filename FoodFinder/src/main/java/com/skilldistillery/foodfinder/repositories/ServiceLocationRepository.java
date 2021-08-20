package com.skilldistillery.foodfinder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.foodfinder.entities.ServiceLocation;

public interface ServiceLocationRepository extends JpaRepository<ServiceLocation, Integer> {

	List<ServiceLocation> findByUser_Username(String username);
	
	ServiceLocation findByIdAndUser_Username(int slId, String username);
	
	ServiceLocation queryById(int slId);

	
}
