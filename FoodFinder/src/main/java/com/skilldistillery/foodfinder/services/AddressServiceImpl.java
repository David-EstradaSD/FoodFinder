package com.skilldistillery.foodfinder.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.foodfinder.entities.Address;
import com.skilldistillery.foodfinder.entities.Donor;
import com.skilldistillery.foodfinder.entities.Recipient;
import com.skilldistillery.foodfinder.repositories.AddressRepository;
import com.skilldistillery.foodfinder.repositories.DonorRepository;
import com.skilldistillery.foodfinder.repositories.RecipientRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private DonorRepository donorRepo;
	
	@Autowired
	private RecipientRepository recipientRepo;

	@Override
	public List<Address> index() {
		return addressRepo.findAll();
	}

//	@Override
//	public Address donorCreate(Address address, String username) {
//		Donor donor = donorRepo.findByUser_Username(username);
//		donor.setAddress(address);
//		try {
//			addressRepo.saveAndFlush(address);
//		} catch (Exception e) {
//			address = null;
//		}
//
//		return address;
//		
//	}
//	
//	@Override
//	public Address recipientCreate(Address address, String username) {
//		Recipient recipient = recipientRepo.findByUser_Username(username);
//		recipient.setAddress(address);
//		try {
//			addressRepo.saveAndFlush(address);
//		} catch (Exception e) {
//			address = null;
//		}
//		return address;
//	}

	@Override
	public Address donorUpdate(Address address, String username, int id) {
		Donor donor = donorRepo.findByUser_Username(username); 
		Address managedAddress = donor.getAddress();
		if(managedAddress != null) {
			managedAddress.setStreetAddress(address.getStreetAddress());
			managedAddress.setCity(address.getCity());
			managedAddress.setState(address.getState());
			managedAddress.setZip(address.getZip());
			addressRepo.saveAndFlush(managedAddress);
		}
		return managedAddress; 
	} 
	
	@Override
	public Address recipientUpdate(Address address, String username, int id) {
		Recipient recipient = recipientRepo.findByUser_Username(username); 
		Address managedAddress = recipient.getAddress();
		if(managedAddress != null) {
			managedAddress.setStreetAddress(address.getStreetAddress());
			managedAddress.setCity(address.getCity());
			managedAddress.setState(address.getState());
			managedAddress.setZip(address.getZip());
			addressRepo.saveAndFlush(managedAddress);
		}
		return managedAddress; 
	}

	@Override
	public Address create(Address address) {
		return addressRepo.saveAndFlush(address);
	}
	
//	@Override
//	public boolean donorDelete(String username, int addressId) {
//		boolean deleted = false;
//		
//		Donor donor = donorRepo.findByUser_Username(username);
//		addressId = donor.getAddress().getId(); 
//		
//		if (donor.getAddress() != null) {
//			addressRepo.delete(donor.getAddress());
//			donorRepo.deleteById(addressId);
//			deleted = true;
//		}
//		return deleted;	
//		}
//	
//	@Override
//	public boolean recipientDelete(String username, int addressId) {
//		boolean deleted = false;
//		
//		Recipient recipient = recipientRepo.findByUser_Username(username);
//		addressId = recipient.getAddress().getId(); 
//		
//		if (recipient.getAddress() != null) {
//			addressRepo.delete(recipient.getAddress());
//			deleted = true;
//		}
//		return deleted;	
//		}
//
//	@Override
//	public boolean deleteByAddressId(int addressId) {
//		boolean deleted = false;
//		Address address = addressRepo.queryById(addressId); 
//		
//		if (address != null) {
//			addressRepo.delete(address);
//			deleted = true;
//		}
//		return deleted;
//	}
	
	
	
	
}


