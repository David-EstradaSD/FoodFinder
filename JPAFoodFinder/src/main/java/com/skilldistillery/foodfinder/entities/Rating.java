package com.skilldistillery.foodfinder.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Rating {

	@EmbeddedId	
	private RatingId id;
	private int rating;
	
	public Rating() {
	}
	
	public RatingId getId() {
		return id;
	}
	
	public void setId(RatingId id) {
		this.id = id;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return "Rating [id=" + id + ", rating=" + rating + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Rating other = (Rating) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
