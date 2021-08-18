package com.skilldistillery.foodfinder.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RatingId implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	@Column(name="service_location_id")
	private int serviceLocationId;

	@Column(name="recipient_id")
	private int recipientId;

	public RatingId() {
	}
	
	public RatingId(int serviceLocationId, int recipientId) {
		super();
		this.serviceLocationId = serviceLocationId;
		this.recipientId = recipientId;
	}

	public int getServiceLocationId() {
		return serviceLocationId;
	}

	public void setServiceLocationId(int serviceLocationId) {
		this.serviceLocationId = serviceLocationId;
	}

	public int getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "RatingId [serviceLocationId=" + serviceLocationId + ", recipientId=" + recipientId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + recipientId;
		result = prime * result + serviceLocationId;
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
		RatingId other = (RatingId) obj;
		if (recipientId != other.recipientId)
			return false;
		if (serviceLocationId != other.serviceLocationId)
			return false;
		return true;
	}
	
}
