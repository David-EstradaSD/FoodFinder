package com.skilldistillery.foodfinder.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String comment;
	@Column(name="private_comment")
	private boolean privateComment; // do they want to post anonymously? 
	@Column(name="create_date")
	private LocalDateTime createDate;
	// map service_location_id
	// map recipient_id

}
