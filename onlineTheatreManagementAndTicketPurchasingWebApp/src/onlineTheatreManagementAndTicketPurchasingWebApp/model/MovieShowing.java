package onlineTheatreManagementAndTicketPurchasingWebApp.model;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MovieShowing {

	private int id;
	private int price;
	private int numberPurchased;
	private String startTime;
	private String endTime;
	private Movies aMovie;
	private Showroom aShowroom;
	private ArrayList<Review> reviewList;
	private int showroomID;
	private String MovieName;
	
	public String getMovieName() {
		return MovieName;
	}
	public void setMovieName(String movieName) {
		MovieName = movieName;
	}
	public int getShowroomID() {
		return showroomID;
	}
	public void setShowroomID(int showroomID) {
		this.showroomID = showroomID;
	}
	
	public MovieShowing() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNumberPurchased() {
		return numberPurchased;
	}
	public void setNumberPurchased(int numberPurchased) {
		this.numberPurchased = numberPurchased;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Movies getaMovie() {
		return aMovie;
	}
	public void setaMovie(Movies aMovie) {
		this.aMovie = aMovie;
	}
	public Showroom getaShowroom() {
		return aShowroom;
	}
	public void setaShowroom(Showroom aShowroom) {
		this.aShowroom = aShowroom;
	}
	public ArrayList<Review> getReviewList() {
		return reviewList;
	}
	public void setReviewList(ArrayList<Review> reviewList) {
		this.reviewList = reviewList;
	}
	public double getOverallReview(){
		double overallReview = 0;
		double reviewSum = 0;
		
		for(Review r: this.getReviewList()){
			reviewSum += r.getRating();
			
		}
		overallReview = (double) reviewSum/this.getReviewList().size();
		
		return overallReview;
	}
	public boolean getCompareDate() throws ParseException{ //compares the movie showing date with the current date
		boolean timeBefore = false;
		
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		Date time = (Date) formatter.parseObject(this.getStartTime());
		
		if(now.before(time)){
			timeBefore = true;
		}
		return timeBefore;
	}
	public boolean validTicketQuantity(int quantity){
		boolean valid = false;
		
		if(quantity > 0){
			valid = true;
		}
		
		return valid;
	}
}
