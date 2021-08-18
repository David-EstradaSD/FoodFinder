package com.skilldistillery.foodfinder.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="service_location")
public class ServiceLocation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="location_name")
	private String locationName;
	@Column(name="location_phone")
	private String locationPhone;
	private String hours;
	private String description;
	@Column(name="create_date")
	private LocalDateTime createTime;
	@Column(name="image_url")
	private String imageUrl;
	
	// map user_id 
	// map address_id
	// map service many to many 
	// map donor many to many
	// map recipient many to many
	
	
	public ServiceLocation() {
	}


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

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	@Override
	public String toString() {
		return "ServiceLocation [id=" + id + ", locationName=" + locationName + ", locationPhone=" + locationPhone
				+ ", hours=" + hours + ", description=" + description + ", createTime=" + createTime + ", imageUrl="
				+ imageUrl + "]";
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
