package com.skilldistillery.foodfinder.services;

import java.util.List;

import com.skilldistillery.foodfinder.entities.Recipient;

public interface RecipientService {

	public List<Recipient> index();
	
	public Recipient show(int id);

	public Recipient create(Recipient recipient);

	public Recipient update(Recipient recipient);

<<<<<<< HEAD
	public boolean destroy(String username);

	public Recipient show(String username);
=======
	public boolean destroy(int id);
>>>>>>> 293c7ca794f84966cbecd061cc94785129158ba8
}