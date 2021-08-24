package com.skilldistillery.foodfinder.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.foodfinder.entities.Service;
import com.skilldistillery.foodfinder.entities.ServiceLocation;
import com.skilldistillery.foodfinder.entities.User;
import com.skilldistillery.foodfinder.repositories.UserRepository;
import com.skilldistillery.foodfinder.services.ServiceLocationService;
import com.skilldistillery.foodfinder.services.ServiceService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4242" })
public class ServiceLocationController {

	@Autowired
	private ServiceLocationService locationService;

	@Autowired
	private ServiceService serviceSvc;

	@Autowired
	private UserRepository userRepo;

	@GetMapping("service-locations")
	public List<ServiceLocation> index(HttpServletRequest req, HttpServletResponse res) {
		return locationService.index();
	}

	@GetMapping("service-locations/{slId}")
	public ServiceLocation detail(HttpServletRequest req, HttpServletResponse res, @PathVariable int slId) {
		return locationService.showDetails(slId);
	}

	@GetMapping("service-locations-user")
	public List<ServiceLocation> userIndex(
			HttpServletRequest req, HttpServletResponse res, Principal principal) {
		return locationService.indexLoggedIn(principal.getName());
	}

	@PostMapping("service-locations")
	public ServiceLocation create(HttpServletRequest req, HttpServletResponse res,
			@RequestBody ServiceLocation location, Principal principal) {
		location = locationService.create(principal.getName(), location);
		try {
			if (location == null) {
				res.setStatus(404);
			} else {
				res.setStatus(201); // Created
				StringBuffer url = req.getRequestURL();
				url.append("/").append(location.getId());             
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			res.setStatus(400); // Bad Request                                     
			location = null;
		}
		return location;
	}

	@PutMapping("service-locations/{slId}")
	public ServiceLocation update(HttpServletRequest req, HttpServletResponse res, @PathVariable int slId,
			@RequestBody ServiceLocation location, Principal principal) {
		try {
			location = locationService.update(principal.getName(), slId, location);
		} catch (Exception e) {
			res.setStatus(400);
			location = null;
		}
		if (location == null) {
			res.setStatus(404);
		}
		return location;
	}

	@DeleteMapping("service-locations/{slId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int slId, Principal principal) {
		try {
			boolean deleted = locationService.destroy(principal.getName(), slId);
			if (deleted) {
				res.setStatus(204); // No Content
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
		}
	}
	
/////////////////////////////////////////// Services Stuff ////////////////////////////////////////////////////

	@GetMapping("services")
	public List<Service> svcIndex(HttpServletRequest req, HttpServletResponse res) {
		return serviceSvc.index();
	}

	@PostMapping("services")
	public Service create(HttpServletRequest req, HttpServletResponse res, @RequestBody Service service, Principal principal) {
		User user = userRepo.findByUsername(principal.getName());
		
		if (user.getRole().equals("admin")) {
			service = serviceSvc.create(service);
			try {
				if (service == null) {
					res.setStatus(404);
				} else {
					res.setStatus(201); // Created
					StringBuffer url = req.getRequestURL();
					url.append("/").append(service.getId());
					res.setHeader("Location", url.toString());
				}
			} catch (Exception e) {
				res.setStatus(400); // Bad Request
				service = null;
			}
		} else {
			res.setStatus(401); // Unauthorized
		}
		return service;
	}

	@PutMapping("services/{sid}")
	public Service update(HttpServletRequest req, HttpServletResponse res, @PathVariable int sid,
			@RequestBody Service service) {
		System.out.println(service);
		try {
			service = serviceSvc.update(service);
		} catch (Exception e) {
			res.setStatus(400);
			service = null;
		}
		if (service == null) {
			res.setStatus(404);
		}
		System.out.println("***** AFTER TRY CATCH*****" + service);
		return service;
	}

	@PutMapping("services")
	public Service updateService(@RequestBody Service service, 
			HttpServletResponse resp, HttpServletRequest req, Principal principal) {
		User user = userRepo.findByUsername(principal.getName());

		if (user.getRole().equals("admin")) {
			try {
				service = serviceSvc.update(service);
				if (service == null) {
					resp.setStatus(404);
				}
			} catch (Exception e) {
				resp.setStatus(400);
				service = null;
				e.printStackTrace();
			}
		} else {
			resp.setStatus(401);
		}
		return service;
	}

	@DeleteMapping("services/{sid}")
	public void delete(@PathVariable int sid, HttpServletResponse resp, Principal principal) {
		User user = userRepo.findByUsername(principal.getName());
		if (user.getRole().equals("admin")) {
		serviceSvc.destroy(sid);
		}
	}
}
