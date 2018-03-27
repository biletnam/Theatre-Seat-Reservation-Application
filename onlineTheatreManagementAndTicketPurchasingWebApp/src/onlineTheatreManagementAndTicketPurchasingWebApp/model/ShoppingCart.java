package onlineTheatreManagementAndTicketPurchasingWebApp.model;

public class ShoppingCart {
	private int requestedTicketQuantity;
	private String movieName;
	private String imageURL;
	private String theatreName;
	private int showroomId;
	private String showtimeStart;
	private int price;
	
	public int getRequestedTicketQuantity() {
		return requestedTicketQuantity;
	}
	public void setRequestedTicketQuantity(int requestedTicketQuantity) {
		this.requestedTicketQuantity = requestedTicketQuantity;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getTheatreName() {
		return theatreName;
	}
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		int totalPrice = price*(this.requestedTicketQuantity);
		this.price = totalPrice;
	}
	public String getShowtimeStart() {
		return showtimeStart;
	}
	public void setShowtimeStart(String showtimeStart) {
		this.showtimeStart = showtimeStart;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public int getShowroomId() {
		return showroomId;
	}
	public void setShowroomId(int showroomId) {
		this.showroomId = showroomId;
	}
}
