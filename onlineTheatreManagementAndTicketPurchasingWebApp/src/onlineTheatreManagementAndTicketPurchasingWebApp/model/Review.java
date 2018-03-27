package onlineTheatreManagementAndTicketPurchasingWebApp.model;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Review {

	private Movies aMovie;
	private Users aUser;
	private String reviewDate;
	private int rating;
	private String review;
	
	public Review(){
		super();
	}
	
	public Movies getaMovie() {
		return aMovie;
	}

	public void setaMovie(Movies aMovie) {
		this.aMovie = aMovie;
	}

	public Users getaUser() {
		return aUser;
	}

	public void setaUser(Users aUser) {
		this.aUser = aUser;
	}

	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public boolean isValidSize(String review){
		boolean isValid = true;
		
		if(review.length() > 255){
			isValid = false;
		}
		
		return isValid;
	}
}
