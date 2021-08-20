package com.skilldistillery.foodfinder.services;

import java.util.List;

import com.skilldistillery.foodfinder.entities.Donor;

public interface DonorService {
	
	public List<Donor> index(String category);

}
