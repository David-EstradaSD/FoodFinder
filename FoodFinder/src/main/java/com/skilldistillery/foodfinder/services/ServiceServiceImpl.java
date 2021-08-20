package com.skilldistillery.foodfinder.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodfinder.repositories.ServiceRepository;

@Service
public class ServiceServiceImpl implements ServiceService {

	@Autowired
	private ServiceRepository serviceRepo;

	@Override
	public List<com.skilldistillery.foodfinder.entities.Service> index() {

		return serviceRepo.findAll();
	}

	@Override
	public com.skilldistillery.foodfinder.entities.Service create(
			com.skilldistillery.foodfinder.entities.Service service) {

		return serviceRepo.saveAndFlush(service);
	}

	@Override
	public com.skilldistillery.foodfinder.entities.Service update(
			com.skilldistillery.foodfinder.entities.Service service) {
		
		return serviceRepo.saveAndFlush(service);
	}

	@Override
	public boolean destroy(Integer id) {
		boolean isDeleted = false;
		Optional<com.skilldistillery.foodfinder.entities.Service> optService = serviceRepo.findById(id);
		if (optService.isPresent()) {
			com.skilldistillery.foodfinder.entities.Service svc = optService.get();
			if (svc.getId() == id) {
				serviceRepo.deleteById(id);
				isDeleted = true;
			}
		}
		return isDeleted;
	}
	

}
