package com.skilldistillery.foodfinder.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String street_address;
	private String city;
	private String state;
	private String zip;
	// map service_location 
	// map to donor 
	// map to recipient 

}
