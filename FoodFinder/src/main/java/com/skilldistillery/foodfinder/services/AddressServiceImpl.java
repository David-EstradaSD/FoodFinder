package com.skilldistillery.foodfinder.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodfinder.entities.Address;
import com.skilldistillery.foodfinder.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepo;

	@Override
	public List<Address> index() {
		return addressRepo.findAll();
	}
	
	
}
