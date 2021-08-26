package com.skilldistillery.foodfinder.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "service_location")
public class ServiceLocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "location_name")
	private String locationName;
	@Column(name = "location_phone")
	private String locationPhone;
	private String hours;
	private String description;
	@Column(name = "create_date")
	private LocalDateTime createdDateTime;
	@Column(name = "image_url")
	private String imageUrl;

	@JsonIgnore
	@OneToMany(mappedBy = "serviceLocation")
	private List <Rating> ratings;
	
	@JsonIgnore
	@OneToMany(mappedBy = "serviceLocation")
	private List <Comment> comments;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
 
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@JsonIgnoreProperties({"serviceLocations"})
	@ManyToMany
	@JoinTable(name = "service_location_has_service", joinColumns = @JoinColumn(name = "service_id"), inverseJoinColumns = @JoinColumn(name = "service_location_id"))
	private List<Service> services;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "donor_has_service_location", joinColumns = @JoinColumn(name = "donor_id"), inverseJoinColumns = @JoinColumn(name = "service_location_id"))
	private List<Donor> donors;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "service_location_has_recipient", joinColumns = @JoinColumn(name = "recipient_id"), inverseJoinColumns = @JoinColumn(name = "service_location_id"))
	private List<Recipient> recipients;

	public ServiceLocation() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationPhone() {
		return locationPhone;
	}

	public void setLocationPhone(String locationPhone) {
		this.locationPhone = locationPhone;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public List<Donor> getDonors() {
		return donors;
	}

	public void setDonors(List<Donor> donors) {
		this.donors = donors;
	}

	public List<Recipient> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<Recipient> recipients) {
		this.recipients = recipients;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}



	@Override
	public String toString() {
		return "ServiceLocation [id=" + id + ", locationName=" + locationName + ", locationPhone=" + locationPhone
				+ ", hours=" + hours + ", description=" + description + ", createdDateTime=" + createdDateTime
				+ ", imageUrl=" + imageUrl + ", user=" + user + "]";
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
		ServiceLocation other = (ServiceLocation) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
