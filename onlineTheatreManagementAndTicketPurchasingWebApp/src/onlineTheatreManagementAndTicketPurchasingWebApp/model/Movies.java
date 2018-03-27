package onlineTheatreManagementAndTicketPurchasingWebApp.model;

public class Movies {

	private String movieTitle;
	private String description;
	private String thumbnail;
	private String MPAARating;
	private String genre;
	private String imageURL;
	
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getMPAARating() {
		return MPAARating;
	}
	public void setMPAARating(String mPAARating) {
		MPAARating = mPAARating;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
}
