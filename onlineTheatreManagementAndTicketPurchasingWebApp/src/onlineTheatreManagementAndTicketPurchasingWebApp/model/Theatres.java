package onlineTheatreManagementAndTicketPurchasingWebApp.model;

public class Theatres {

	private String theatreName;
	private String address;
	private String city;
	private String state;
	private String postalCode;
	private Users aOwner;
	private int number;
	
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getTheatreName() {
		return theatreName;
	}
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public Users getaOwner() {
		return aOwner;
	}
	public void setaOwner(Users aOwner) {
		this.aOwner = aOwner;
	}
	
}
