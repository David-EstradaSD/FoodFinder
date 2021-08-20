package com.skilldistillery.foodfinder.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodfinder.entities.Donor;
import com.skilldistillery.foodfinder.repositories.DonorRepository;

@Service
public class DonorServiceImpl implements DonorService {
	
	@Autowired
	private DonorRepository donorRepo;

	@Override
	public List<Donor> index(String category) {
		return donorRepo.findByCategory(category);
	}
	
	
	

}
