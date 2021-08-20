package com.skilldistillery.foodfinder.services;

import java.util.List;

import com.skilldistillery.foodfinder.entities.Service;

public interface ServiceService {
	
	public List<Service> index(); 
	
	Service create (Service service);
	
	Service update (Service service);
	
	public boolean destroy(Integer id);
}
