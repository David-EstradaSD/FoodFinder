package com.skilldistillery.foodfinder.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
	@JoinColumn(name="user_id") // TODO: FIX ME
	private User user;
	@JoinColumn(name="address_id")
	@OneToOne
	private Address address;
	@JoinColumn(name="recipient")
	@OneToMany
	private List<Comment> comments;
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="service_location_has_recipient",
				joinColumns = @JoinColumn(name="recipient_id"),
				inverseJoinColumns = @JoinColumn(name="service_location_id"))
	private List<ServiceLocation> serviceLocations;
	@OneToMany
	private List<Rating> ratings;
	
	public Recipient() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Recipient [id=" + id + "]";
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
