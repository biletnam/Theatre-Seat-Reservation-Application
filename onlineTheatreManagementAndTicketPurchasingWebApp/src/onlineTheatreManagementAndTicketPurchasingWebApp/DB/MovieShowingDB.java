package onlineTheatreManagementAndTicketPurchasingWebApp.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import onlineTheatreManagementAndTicketPurchasingWebApp.model.Movies;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Showroom;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Theatres;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.MovieShowing;

public class MovieShowingDB {

	public ArrayList<MovieShowing> searchQuery(String searchMovieName, String searchMovieDate, String searchMovieTheatre){ //for search query in home page
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		
		TheatresDB aTheatreDBId = new TheatresDB();
		int theatreId = aTheatreDBId.getTheatreId(searchMovieTheatre);
		searchMovieName = "%" + searchMovieName +"%";
		ArrayList<MovieShowing> movieShowingList = new ArrayList<MovieShowing>();
		String query = "SELECT m.id, m.MovieName, m.ImageURL, m.Genre, tb.Name, s.showroomNumber, s.theatreBuilding, ms.StartTime, ms.EndTime, ms.Price, ms.NumberPurchased, s.availableSeats "
				+ "FROM `Showroom` s "
				+ "JOIN TheatreBuilding tb ON tb.Id = s.theatreBuilding "
				+ "JOIN MovieShowing ms ON ms.showroomID = s.Id " 
				+ "JOIN Movie m ON m.id = ms.movieID WHERE m.MovieName LIKE ? AND s.theatreBuilding = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, searchMovieName);
			ps.setInt(2, theatreId);
			rs = ps.executeQuery();
			while(rs.next()){
				//if statement to only select the date chosen by the user
				if(rs.getDate("ms.StartTime").toString().equals(searchMovieDate)){
					Movies aMovie = new Movies();
					aMovie.setMovieTitle(rs.getString("m.MovieName"));
					aMovie.setGenre(rs.getString("m.Genre"));
					aMovie.setImageURL(rs.getString("m.ImageURL"));
					Showroom aShowroom = new Showroom();
					aShowroom.setShowroomId(rs.getInt("s.showroomNumber"));
					aShowroom.setAvailableSeats(rs.getInt("s.availableSeats") - rs.getInt("ms.NumberPurchased"));
					TheatresDB aTheatreDB = new TheatresDB();
					aShowroom.setaTheatre(aTheatreDB.getTheatreByShowroomId(rs.getInt("s.theatreBuilding")));
					MovieShowing aMovieShowing = new MovieShowing();
					aMovieShowing.setStartTime(rs.getString("ms.StartTime").replaceAll("\\.\\d+", ""));
					aMovieShowing.setEndTime(rs.getString("ms.EndTime").replaceAll("\\.\\d+", ""));
					aMovieShowing.setPrice(rs.getInt("ms.Price"));
					aMovieShowing.setaMovie(aMovie);
					aMovieShowing.setaShowroom(aShowroom);
					
					movieShowingList.add(aMovieShowing);
				}
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return movieShowingList;
	}
	
	public int getShowroomId(int showroomNumber, int theatreBuildingNumber){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		int showroomId = 0;

		String query = "SELECT Id FROM `Showroom` "
				+ "WHERE showroomNumber = ? AND theatreBuilding = ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, showroomNumber);
			ps.setInt(2, theatreBuildingNumber);
			rs = ps.executeQuery();
			if(rs.next()){
				showroomId = rs.getInt("Id");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return showroomId;
	}
	
	public Showroom getShowroomByShowroomId(int showroomId){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);		
		Showroom aShowroom = new Showroom();
		
		String query = "SELECT * FROM Showroom "
				+ "WHERE Id = ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, showroomId);
			rs = ps.executeQuery();
			if(rs.next()){	
				aShowroom.setShowroomId(rs.getInt("showroomNumber"));
				aShowroom.setAvailableSeats(rs.getInt("availableSeats"));
				TheatresDB aTheatreDB = new TheatresDB();
				aShowroom.setaTheatre(aTheatreDB.getTheatreByShowroomId(rs.getInt("theatreBuilding")));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return aShowroom;
	}
	
	public int getMovieShowingId(MovieShowing aMovieShowing, int movieId, int showroomId){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		int movieShowingId = 0;
		
		String query = "SELECT Id FROM `MovieShowing` "
				+ "WHERE StartTime = ? AND movieID = ? AND showroomID = ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, aMovieShowing.getStartTime());
			ps.setInt(2, movieId);
			ps.setInt(3, showroomId);
			rs = ps.executeQuery();
			if(rs.next()){
				movieShowingId = rs.getInt("Id");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return movieShowingId;
	}
	
	public MovieShowing getMovieShowingByMovieShowingId(int movieShowingId){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);

		MovieShowing aMovieShowing = new MovieShowing();
		String query = "SELECT * FROM MovieShowing "
				+ "WHERE Id = ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, movieShowingId);
			rs = ps.executeQuery();
			if(rs.next()){
				aMovieShowing.setId(movieShowingId);
				aMovieShowing.setNumberPurchased(rs.getInt("NumberPurchased"));
				aMovieShowing.setStartTime(rs.getString("StartTime"));
				aMovieShowing.setEndTime(rs.getString("EndTime"));
				aMovieShowing.setPrice(rs.getInt("price"));
				MoviesDB aMovieDB = new MoviesDB();
				aMovieShowing.setaMovie(aMovieDB.getMoviesByMovieId(rs.getInt("movieID")));
				aMovieShowing.setaShowroom(this.getShowroomByShowroomId(rs.getInt("showroomID")));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return aMovieShowing;
	}
	

	public int getNumberPurchased(int movieId, int showroomId, String startTime){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		int numberPurchased = 0;
		
		String query = "SELECT NumberPurchased FROM MovieShowing "
				+ "WHERE movieID = ? AND showroomID = ? AND StartTime = ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, movieId);
			ps.setInt(2, showroomId);
			ps.setString(3, startTime);

			rs = ps.executeQuery();
			if(rs.next()){
				numberPurchased = rs.getInt("NumberPurchased");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return numberPurchased;
	}
	
	public void updateNumberPurchasedByMovieShowingId(int movieShowingId, int quantity){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		
		int numberPurchased = 0;
		String query = "SELECT NumberPurchased "
				+ "FROM MovieShowing "
				+ "WHERE id = ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, movieShowingId);
			rs = ps.executeQuery();
			if(rs.next()){
				numberPurchased = rs.getInt("NumberPurchased");
			} 
		rs.close();
		ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		numberPurchased = numberPurchased + quantity;
		
		query = "UPDATE MovieShowing SET NumberPurchased = ? "
				+ "WHERE Id = ?";
		ps = null;
		rs = null;	
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, numberPurchased);
			ps.setInt(2, movieShowingId);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
	}
	
	public ArrayList<Showroom> getShowRoomList(int showroomId){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);		
		
		ArrayList<Showroom> ShowroomList = new ArrayList<Showroom>();
		String query = "SELECT * FROM Showroom "
				+ "WHERE theatreBuilding = ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, showroomId);
			rs = ps.executeQuery();
			while(rs.next()){	
				Showroom aShowroom = new Showroom();
				aShowroom.setShowroomId(rs.getInt("showroomNumber"));
				aShowroom.setAvailableSeats(rs.getInt("availableSeats"));
				TheatresDB aTheatreDB = new TheatresDB();
				aShowroom.setaTheatre(aTheatreDB.getTheatreByShowroomId(rs.getInt("theatreBuilding")));
				aShowroom.setTheatreBuilding(rs.getInt("theatreBuilding"));
				ShowroomList.add(aShowroom);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return ShowroomList;
	}
	public ArrayList<MovieShowing> getMovieShowingList(int showroomId){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);		
		
		ArrayList<MovieShowing> MovieShowingList = new ArrayList<MovieShowing>();
		String query = "SELECT * FROM MovieShowing "
				+ "WHERE showroomID = ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, showroomId);
			rs = ps.executeQuery();
			while(rs.next()){	
				MovieShowing aMovieShowingList = new MovieShowing();
				aMovieShowingList.setPrice(rs.getInt("Price"));
				aMovieShowingList.setNumberPurchased(rs.getInt("NumberPurchased"));
				aMovieShowingList.setStartTime(rs.getString("StartTime"));
				aMovieShowingList.setEndTime(rs.getString("EndTime"));
				aMovieShowingList.setShowroomID(rs.getInt("showroomID"));
				MoviesDB aMovie = new MoviesDB();
				aMovieShowingList.setaMovie(aMovie.getMoviesByMovieId(rs.getInt("movieID")));
		
				MovieShowingList.add(aMovieShowingList);
				
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return MovieShowingList;
	}public int getPriceMovieShowing(int showroomID){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		int Price = 0;
		
		String query = "SELECT Price FROM `MovieShowing` "
				+ "WHERE showroomID = ? ";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, showroomID);
			
			rs = ps.executeQuery();
			if(rs.next()){
				Price = rs.getInt("Price");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return Price;
	}
	public int getNumberPurchaseMovieShowing(int showroomID){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		int NumberPurchased = 0;
		
		String query = "SELECT NumberPurchased FROM `MovieShowing` "
				+ "WHERE showroomID = ? ";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, showroomID);
			
			rs = ps.executeQuery();
			if(rs.next()){
				NumberPurchased = rs.getInt("NumberPurchased");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return NumberPurchased;
	}
	
	public void addShowtime(int  showroomId, String startTime, String endTime, String movieName){

		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		
		String query = "INSERT INTO MovieShowing " + "(Price,NumberPurchased,StartTime,EndTime,movieID,ShowroomID) VALUES"+ "(?,?,?,?,?,?)";
		     
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, getPriceMovieShowing( showroomId));
			ps.setInt(2, getNumberPurchaseMovieShowing(showroomId));
			ps.setString(3, startTime);
			
			ps.setString(4, endTime);
			MoviesDB aMovie = new MoviesDB();
			ps.setInt(5,aMovie.getMovieIdByName(movieName));
			ps.setInt(6, showroomId);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
	}
	public void DeleteShowtime(int showroomId, int movieId ,String startTime ){

		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		
		String query = "DELETE FROM MovieShowing WHERE showroomID = ? AND  movieID = ? and StartTime =? ";
		     
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1,  showroomId);
			ps.setInt(2, movieId);
			ps.setString(3,startTime);
			
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
