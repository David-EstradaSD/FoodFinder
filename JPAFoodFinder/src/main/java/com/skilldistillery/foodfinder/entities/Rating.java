package com.skilldistillery.foodfinder.entities;

//@Entity
public class Rating {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
////	private int id; // map recipient_id
	private int rating;
	// map service_location_id

	public Rating() {
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Rating [rating=" + rating + "]";
	}
	
	
}
