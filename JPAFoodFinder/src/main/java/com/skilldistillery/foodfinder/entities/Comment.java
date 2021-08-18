package com.skilldistillery.foodfinder.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String comment;
	
	@Column(name="private_comment")
	private boolean privateComment; // do they want to post anonymously? 
	
	@Column(name="create_date")
	private LocalDateTime createDate;
	
	// map service_location_id
	@ManyToOne
	@JoinColumn(name="service_location_id")
	private ServiceLocation serviceLocation;
	
	@ManyToOne
	@JoinColumn(name="recipient_id")
	private Recipient recipient;
	
	public Comment() {}

	public int getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isPrivateComment() {
		return privateComment;
	}

	public void setPrivateComment(boolean privateComment) {
		this.privateComment = privateComment;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public ServiceLocation getServiceLocation() {
		return serviceLocation;
	}

	public void setServiceLocation(ServiceLocation serviceLocation) {
		this.serviceLocation = serviceLocation;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", privateComment=" + privateComment + ", createDate="
				+ createDate + ", serviceLocation=" + serviceLocation + ", recipient=" + recipient + "]";
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
		Comment other = (Comment) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
