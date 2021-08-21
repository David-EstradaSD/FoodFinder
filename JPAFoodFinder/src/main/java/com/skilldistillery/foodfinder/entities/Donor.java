package com.skilldistillery.foodfinder.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Donor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String category;

	@JoinColumn(name = "user_id")
	@ManyToOne // TODO: FIX ME - Rob
	private User user;

	@JoinColumn(name = "address_id")
	@OneToOne
	private Address address; // this is for personal address | contact information

	@ManyToMany(mappedBy = "donors")
	private List<ServiceLocation> serviceLocations;

	public Donor() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<ServiceLocation> getServiceLocations() {
		return serviceLocations;
	}

	public void setServiceLocations(List<ServiceLocation> serviceLocations) {
		this.serviceLocations = serviceLocations;
	}

	@Override
	public String toString() {
		return "Donor [id=" + id + ", category=" + category + ", user=" + user + ", address=" + address
				+ ", serviceLocations=" + serviceLocations + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Donor other = (Donor) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
