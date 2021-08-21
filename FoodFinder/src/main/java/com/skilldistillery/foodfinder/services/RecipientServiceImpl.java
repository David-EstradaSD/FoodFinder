package com.skilldistillery.foodfinder.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodfinder.entities.Recipient;
import com.skilldistillery.foodfinder.repositories.RecipientRepository;

@Service
public class RecipientServiceImpl implements RecipientService {

	@Autowired
	private RecipientRepository recRepo;

	@Override
	public List<Recipient> index() {
		return recRepo.findAll();
	}

	@Override
	public Recipient create(Recipient recipient) {
		return recRepo.saveAndFlush(recipient);
	}

	@Override
	public Recipient update(Recipient recipient) {
		return recRepo.saveAndFlush(recipient);
	}

	@Override
	public boolean destroy(int id) {
		boolean isDeleted = false;
		Optional<Recipient> opt = recRepo.findById(id);
		Recipient recipient = null;

		if (opt.isPresent()) {
			recipient = opt.get();
			recRepo.delete(recipient);
			isDeleted = true;
		}
		return isDeleted;
	}

	@Override
	public Recipient show(String username) {
		return recRepo.findByUser_Username(username);
	}

	public Recipient show(int id) {
		Optional<Recipient> opt = recRepo.findById(id);
		Recipient recipient = null;
		if (opt.isPresent()) {
			recipient = opt.get();
		}
		return recipient;
	}

	@Override
	public boolean destroy(String username) {
		// TODO Auto-generated method stub
		return false;
	}
}
