package com.skilldistillery.foodfinder.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="service_location")
public class ServiceLocation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="location_name")
	private String locationName;
	@Column(name="location_phone")
	private String locationPhone;
	private String hours;
	private String description;
	@Column(name="create_time")
	private LocalDateTime createTime;
	@Column(name="image_url")
	private String imageUrl;
	// map user_id 
	// map address_id
	// map service many to many 
	// map donor many to many
	// map recipient many to many

}
