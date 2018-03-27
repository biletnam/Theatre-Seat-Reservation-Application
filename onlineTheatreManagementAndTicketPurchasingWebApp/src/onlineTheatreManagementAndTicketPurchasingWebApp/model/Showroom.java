package onlineTheatreManagementAndTicketPurchasingWebApp.model;

public class Showroom {
	private int showroomId;
	private int availableSeats;
	private Theatres aTheatre;
	private int TheatreBuilding;
	
	public int getTheatreBuilding() {
		return TheatreBuilding;
	}
	public void setTheatreBuilding(int theatreBuilding) {
		TheatreBuilding = theatreBuilding;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public int getShowroomId() {
		return showroomId;
	}
	public void setShowroomId(int showroomId) {
		this.showroomId = showroomId;
	}
	public Theatres getaTheatre() {
		return aTheatre;
	}
	public void setaTheatre(Theatres aTheatre) {
		this.aTheatre = aTheatre;
	}
	
}
