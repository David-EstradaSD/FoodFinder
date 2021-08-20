package com.skilldistillery.foodfinder.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodfinder.entities.Recipient;
import com.skilldistillery.foodfinder.entities.User;
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
	public boolean destroy(String username) {
		boolean isDeleted = false;
		Recipient recipient = recRepo.findByUser_Username(username);
		if (recipient != null) {
			recRepo.delete(recipient);
			isDeleted = true;
			return isDeleted;
		}
		return false;
	}

	@Override
	public Recipient show(String username) {
		return recRepo.findByUser_Username(username);
	}
}
