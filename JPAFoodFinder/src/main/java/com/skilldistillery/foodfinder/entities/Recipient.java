package com.skilldistillery.foodfinder.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Recipient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_id") // TODO: Waiting for Rob
	private User user;

	@JoinColumn(name = "address_id")
	@OneToOne
	private Address address;

	@JsonIgnore
	@OneToMany(mappedBy = "recipient")
	private List<Comment> comments;

	@JsonIgnore
	@ManyToMany(mappedBy = "recipients")
	private List<ServiceLocation> serviceLocations;

	@JsonIgnore
	@OneToMany(mappedBy = "recipient")
	private List<Rating> ratings;

	public Recipient() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<ServiceLocation> getServiceLocations() {
		return serviceLocations;
	}

	public void setServiceLocations(List<ServiceLocation> serviceLocations) {
		this.serviceLocations = serviceLocations;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	@Override
	public String toString() {
		return "Recipient [id=" + id + ", user=" + user + ", address=" + address + "]";
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
		Recipient other = (Recipient) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
