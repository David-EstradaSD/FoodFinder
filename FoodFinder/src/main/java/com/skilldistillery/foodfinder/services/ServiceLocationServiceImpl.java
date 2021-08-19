package com.skilldistillery.foodfinder.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodfinder.entities.ServiceLocation;
import com.skilldistillery.foodfinder.entities.User;
import com.skilldistillery.foodfinder.repositories.ServiceLocationRepository;
import com.skilldistillery.foodfinder.repositories.UserRepository;

@Service
public class ServiceLocationServiceImpl implements ServiceLocationService {

	@Autowired
	private ServiceLocationRepository locationRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<ServiceLocation> index() {
		return locationRepo.findAll();
	}

	@Override
	public List<ServiceLocation> index(String username) {
		return locationRepo.findByUser_Username(username);
	}
	
	@Override
	public ServiceLocation showDetails(String username, int slId) {
		return locationRepo.findByIdAndUser_Username(slId, username);
	}

	@Override
	public ServiceLocation create(String username, ServiceLocation location) {
		User user = userRepo.findByUsername(username);
		location.setUser(user);
		try {
			locationRepo.saveAndFlush(location);
		} catch (Exception e) {
			location = null;
		}
		return location;
	}

	@Override
	public ServiceLocation update(String username, int slId, ServiceLocation location) {
		ServiceLocation managed = locationRepo.findByIdAndUser_Username(slId, username);
		if (managed != null) {
			managed.setLocationName(location.getLocationName());
			managed.setDescription(location.getDescription());
			managed.setAddress(location.getAddress());
			managed.setCreatedDateTime(location.getCreatedDateTime());
			managed.setDonors(location.getDonors());
			managed.setHours(location.getHours());
			managed.setImageUrl(location.getImageUrl());
			managed.setServices(location.getServices());
			managed.setLocationPhone(location.getLocationPhone());
			managed.setRecipients(location.getRecipients());
			managed.setRatings(location.getRatings());
		}
		return managed;
	}

	@Override
	public boolean destroy(String username, int slId) {
		boolean deleted = false;
		ServiceLocation location = locationRepo.findByIdAndUser_Username(slId, username);
		if (location != null) {
			locationRepo.delete(location);
			deleted = true;
		}
		return deleted;
	}
	
}
