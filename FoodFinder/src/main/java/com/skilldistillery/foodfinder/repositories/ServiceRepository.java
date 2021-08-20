package com.skilldistillery.foodfinder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.foodfinder.entities.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

}
