package com.skilldistillery.foodfinder.services;

import java.util.List;

import com.skilldistillery.foodfinder.entities.Address;

public interface AddressService {
	
	public List<Address> index(); 
	
//	public Address donorCreate(Address address, String username);
	
//	public Address recipientCreate(Address address, String username); 
	
	public Address create(Address address); 
	
	public Address donorUpdate(Address address, String username, int addressId); 
	
	public Address recipientUpdate(Address address, String username, int addressId);
	
//	public boolean recipientDelete(String username, int addressId);
//
//	public boolean donorDelete(String username, int addressId);
	
//	public boolean deleteByAddressId(int addressId); 
	
}
