package com.skilldistillery.foodfinder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.foodfinder.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
