package com.skilldistillery.foodfinder.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodfinder.entities.Donor;
import com.skilldistillery.foodfinder.entities.Recipient;
import com.skilldistillery.foodfinder.entities.User;
import com.skilldistillery.foodfinder.repositories.DonorRepository;
import com.skilldistillery.foodfinder.repositories.UserRepository;

@Service
public class DonorServiceImpl implements DonorService {

	@Autowired
	private DonorRepository donorRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Donor> index(String category) {
		return donorRepo.findByCategory(category);
	}

	@Override
	public Donor show(int id) {
		Optional<Donor> opt = donorRepo.findById(id);
		Donor donor = null;
		if (opt.isPresent()) {
			donor = opt.get();
		}
		return donor;
	}

	@Override
	public Donor create(Donor donor, String username) {
		User user = userRepo.findByUsername(username);
		donor.setUser(user);
		try {
			donorRepo.saveAndFlush(donor);
		} catch (Exception e) {
			donor = null;
		}
		return donor;
	}

	@Override
	public Donor update(Donor donor, int donorId, String username) {
		Donor managed = donorRepo.findByIdAndUser_Username(donorId, username);
		if (managed != null) {
			managed.setAddress(donor.getAddress());
			managed.setUser(donor.getUser());
			managed.setCategory(donor.getCategory());
			managed.setServiceLocations(donor.getServiceLocations());
		}
		return managed;
	}

	@Override
	public boolean destroy(int id, String username) {
		boolean isDeleted = false;
		Donor donor = donorRepo.findByIdAndUser_Username(id, username);
		if (donor != null) {
			donorRepo.delete(donor);
			isDeleted = true;
		}
//		Optional<Recipient> opt = recRepo.findById(id);
//		Recipient recipient = null;
//		
//		if (opt.isPresent()) {
//			recipient = opt.get();
//			recRepo.delete(recipient);
//			isDeleted = true;
//		}
		return isDeleted;
	}

}
