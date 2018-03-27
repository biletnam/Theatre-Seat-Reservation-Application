package onlineTheatreManagementAndTicketPurchasingWebApp.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import onlineTheatreManagementAndTicketPurchasingWebApp.model.Movies;

public class MoviesDB {

	public String getMovieURLByGenre(String genre, String movieName){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		
		String movieURL = "";
		String query = "SELECT ImageURL "
				+ "FROM Movie WHERE Genre = ? AND MovieName != ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, genre);
			ps.setString(2, movieName);
			rs = ps.executeQuery();
			if(rs.next()){
				 movieURL = rs.getString("ImageURL");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		if(movieURL.equals("") || movieURL == null){
			movieURL = "images/aliens.jpg";
		} 
		return movieURL;
	}
	
	public Movies getMoviesByMovieId(int movieId){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		
		Movies aMovie = new Movies();
		
		String query = "SELECT * "
				+ "FROM Movie WHERE id = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, movieId);
			rs = ps.executeQuery();
			if(rs.next()){
				 aMovie.setMovieTitle(rs.getString("MovieName"));
				 aMovie.setDescription(rs.getString("Description"));
				 aMovie.setImageURL(rs.getString("ImageURL"));
				 aMovie.setMPAARating(rs.getString("Rating"));
				 aMovie.setGenre(rs.getString("Genre"));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return aMovie;
	}
	
	public String getMovieRatingByName(String movieName) {
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		String movieRating = "";
		String query = "SELECT Rating "
				+ "FROM Movie WHERE MovieName = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, movieName);
			rs = ps.executeQuery();
			if(rs.next()){
				movieRating = rs.getString("Rating");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return movieRating;
	}
	
	public String getMovieDescriptionByName(String movieName){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		String movieDescription = "";
		String query = "SELECT Description "
				+ "FROM Movie WHERE MovieName = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, movieName);
			rs = ps.executeQuery();
			if(rs.next()){
				movieDescription = rs.getString("Description");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return movieDescription;
	}
	
	public int getMovieIdByName(String movieName){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		int movieId = 0;
		String query = "SELECT id "
				+ "FROM Movie WHERE MovieName = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, movieName);
			rs = ps.executeQuery();
			if(rs.next()){
				movieId = rs.getInt("id");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return movieId;
	}
	
	public void addMovie(String MovieName, String Description, String Genre, String Rating, String Image){

		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		
		String query = "INSERT INTO Movie " + "(MovieName,Description,Genre,Rating,ImageURL) VALUES"+ "(?,?,?,?,?)";
		     
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, MovieName);
			ps.setString(2, Description);
			ps.setString(3, Genre);
			ps.setString(4, Rating);
			ps.setString(5,Image);
			ps.executeUpdate();
			//ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
	}
	//give the movie name and give back the discription
	
	public void UpdateMovies(String movieDescription, String movieGenre, String movieRating, String movieName ){
		 Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		 // need to check back the query 
		 String query = "UPDATE Movie SET Description=?,Genre=?, Rating=? WHERE MovieName =?";
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, movieDescription);
				ps.setString(2, movieGenre);
				
				ps.setString(3, movieRating);
				ps.setString(4, movieName);
				ps.executeUpdate();
				ps.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}

			conn = DatabaseConnection.CloseConnection(rs, ps, conn);
	}
	public ArrayList<Movies> GetMovieObject(String searchQuery) {
		//open connection
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		searchQuery = "%" + searchQuery + "%";
		ArrayList<Movies> movieList = new ArrayList<Movies>();
		String query = "SELECT * FROM Movie WHERE `MovieName` LIKE ? " ;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, searchQuery);
			rs = ps.executeQuery();
			while(rs.next()){
				Movies aMovie = new Movies();
				aMovie.setMovieTitle(rs.getString("MovieName"));
				aMovie.setDescription(rs.getString("Description"));
				aMovie.setThumbnail(rs.getString("Thumbnail"));
				aMovie.setGenre(rs.getString("Genre"));
				aMovie.setMPAARating(rs.getString("Rating"));
				aMovie.setImageURL(rs.getString("ImageURL"));
				
				movieList.add(aMovie);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		return movieList;
	}
	// This search the movie by 3 conditions
	public Movies GetMovieInDetails(String movieTitle) {
		//open connection
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);

		
		String query = "SELECT * FROM Movie WHERE `MovieName` = ? " ;
		Movies aMovie = new Movies();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, movieTitle);
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				aMovie.setMovieTitle(rs.getString("MovieName"));
				aMovie.setDescription(rs.getString("Description"));
				aMovie.setThumbnail(rs.getString("Thumbnail"));
				aMovie.setGenre(rs.getString("Genre"));
				aMovie.setMPAARating(rs.getString("Rating"));
				aMovie.setImageURL(rs.getString("ImageURL"));
				
				
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		return aMovie;
	}
	public Movies GetUpdatedMovie(String movieTitle, String Genre, String Rating) {
		//open connection
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);

		
		String query = "SELECT * FROM Movie WHERE (`MovieName` = ?) and (Genre = ?) and (Rating = ?)" ;
		Movies aMovie = new Movies();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, movieTitle);
			ps.setString(2, Genre);
			ps.setString(3, Rating);
			rs = ps.executeQuery();
			while(rs.next()){
				
				aMovie.setMovieTitle(rs.getString("MovieName"));
				aMovie.setDescription(rs.getString("Description"));
				aMovie.setThumbnail(rs.getString("Thumbnail"));
				aMovie.setGenre(rs.getString("Genre"));
				aMovie.setMPAARating(rs.getString("Rating"));
				aMovie.setImageURL(rs.getString("ImageURL"));
				
				
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		return aMovie;
	}

	
}
