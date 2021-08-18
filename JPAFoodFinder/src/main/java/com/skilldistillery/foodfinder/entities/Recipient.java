package com.skilldistillery.foodfinder.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recipient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	// map user_id
	// map address_id
	// map to comment
	// map to service_location_has_recipient many to many
	// map to rating 

}
