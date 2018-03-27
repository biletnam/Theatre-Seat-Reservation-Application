package onlineTheatreManagementAndTicketPurchasingWebApp.model;

public class OrderItems {

	private Orders aOrder;
	private MovieShowing aMovieShowing;
	private int quantity;
	private int totalPrice;
	
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Orders getaOrder() {
		return aOrder;
	}
	public void setaOrder(Orders aOrder) {
		this.aOrder = aOrder;
	}
	public MovieShowing getaMovieShowing() {
		return aMovieShowing;
	}
	public void setaMovieShowing(MovieShowing aMovieShowing) {
		this.aMovieShowing = aMovieShowing;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
