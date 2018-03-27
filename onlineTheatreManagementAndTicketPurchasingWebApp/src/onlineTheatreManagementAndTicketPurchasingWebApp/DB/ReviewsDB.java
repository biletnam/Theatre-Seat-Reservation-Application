package onlineTheatreManagementAndTicketPurchasingWebApp.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import onlineTheatreManagementAndTicketPurchasingWebApp.model.Movies;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Review;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Users;

public class ReviewsDB {

	public ArrayList<Review> fetchReview(String movieName){ //fetching movie review at movie details page
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
			
		ArrayList<Review> reviewList = new ArrayList<Review>();
		String query = "SELECT cr.ReviewDate, cr.Rating, cr.Review, u.FirstName, u.LastName "
				+ "FROM CustomerReview cr "
				+ "JOIN Movie m ON cr.movieID = m.id "
				+ "JOIN User u ON cr.userID = u.Id "
				+ "WHERE m.movieName = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, movieName);
			rs = ps.executeQuery();
			while(rs.next()){
				Users aUser = new Users();
				aUser.setFirstName(rs.getString("u.FirstName"));
				aUser.setLastName(rs.getString("u.LastName"));
				
				String reviewDate = rs.getString("cr.ReviewDate");
				int rating = rs.getInt("cr.Rating");
				String reviewText = rs.getString("cr.Review");
				
				Review aReview = new Review();
				aReview.setaUser(aUser);
				aReview.setRating(rating);
				aReview.setReviewDate(reviewDate);
				aReview.setReview(reviewText);
				
				reviewList.add(aReview);
			}
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return reviewList;
	}
	
	public void commitReview(Review aReview, int movieId, int userId){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		String query = "INSERT INTO CustomerReview (movieId, userId, ReviewDate, Rating, Review) " +
				"VALUES (?,?,?,?,?);";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, movieId);
			ps.setInt(2, userId);
			ps.setString(3, aReview.getReviewDate());
			ps.setInt(4, aReview.getRating());
			ps.setString(5, aReview.getReview());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
	}
	
}
