package com.skilldistillery.foodfinder.services;

import java.util.List;

import com.skilldistillery.foodfinder.entities.Donor;

public interface DonorService {
	
	public List<Donor> index(String category);
	
	public Donor show(int id);
	
	public Donor create(Donor donor, String username);
	
	public Donor update(Donor donor, int donorId, String username);
	
	public boolean destroy(int id, String username);

}
