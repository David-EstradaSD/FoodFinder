package com.skilldistillery.foodfinder.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.foodfinder.entities.Address;
import com.skilldistillery.foodfinder.services.AddressService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4242"})
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("addresses")
	public List<Address> getAddressList(HttpServletResponse res) {
		List <Address> addresses = addressService.index();
		return addresses;
	}
	
	@PostMapping("address/donor/{donorId}")
	public Address donorCreate(@RequestBody Address address, Principal principal, HttpServletRequest req, HttpServletResponse resp, @PathVariable int donorId) {
		try {
			address = addressService.donorCreate(address, principal.getName());
			//res.setStatus(201);
			resp.setStatus(200);
			return address;
			
		} catch (Exception e) {
			resp.setStatus(400);
			address = null;
		}
		return address;
	}
	
	@PostMapping("address/recipient/{recipientId}")
	public Address recipientCreate(@RequestBody Address address, Principal principal, HttpServletRequest req, HttpServletResponse resp, @PathVariable int recipientId) {
		try {
			address = addressService.recipientCreate(address, principal.getName());
			//res.setStatus(201);
			resp.setStatus(200);
			return address;
			
		} catch (Exception e) {
			resp.setStatus(400);
			address = null;
		}
		return address;
	}
	
	@PutMapping("address/donor/{id}")
	public Address update(@RequestBody Address address, Principal principal, HttpServletRequest req, HttpServletResponse resp) {
		try {
			address = addressService.update();
			if (address == null) {
				resp.setStatus(404);
			}
			
		} catch (Exception e) {
			resp.setStatus(400);
			address = null;
			e.printStackTrace();
		}
		return address;
	}
	
//	@DeleteMapping("address/{id}")
//	public String deleteAddress(@PathVariable Integer id, Principal principal, HttpServletResponse res) {
//		
//		try {
//			boolean deleted = addressService.delete(id);
//			if (deleted) {
//				res.setStatus(204);
//				return "Deleted";
//				
//			} else {
//				res.setStatus(404);
//				return "Not Found";
//			}
//			
//		} catch (Exception e) {
//			res.setStatus(400);
//			return "Failed to delete";
//		}
//	}	
	
}
