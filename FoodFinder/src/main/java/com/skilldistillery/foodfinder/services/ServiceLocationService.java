package com.skilldistillery.foodfinder.services;

import java.util.List;

import com.skilldistillery.foodfinder.entities.ServiceLocation;

public interface ServiceLocationService {
	
	public List<ServiceLocation> index(); // for general public
	
	public List<ServiceLocation> indexLoggedIn(String username); // for a User logged in
	
	public ServiceLocation showDetails(int slId);
	
	public ServiceLocation create(String username, ServiceLocation location);
	
	public ServiceLocation update(String username, int slId, ServiceLocation location);
	
	public boolean destroy(String username, int slId);

}
