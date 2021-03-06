package com.skilldistillery.foodfinder.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "service_name")
	private String serviceName;
	private String description;

	// map to service_location many to many
	@JsonIgnore
	@ManyToMany(mappedBy = "services")
	private List<ServiceLocation> serviceLocations;

	public Service() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ServiceLocation> getServiceLocations() {
		return serviceLocations;
	}

	public void setServiceLocations(List<ServiceLocation> serviceLocations) {
		this.serviceLocations = serviceLocations;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", serviceName=" + serviceName + ", description=" + description
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
		Service other = (Service) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
