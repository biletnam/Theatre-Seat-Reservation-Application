package onlineTheatreManagementAndTicketPurchasingWebApp.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import onlineTheatreManagementAndTicketPurchasingWebApp.model.Theatres;

public class TheatresDB {
	public ArrayList<Theatres> fetchTheatreForOwner(int ownerId) {
		//open connection
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);

		ArrayList<Theatres> theatreList = new ArrayList<Theatres>();
		String query = "SELECT * FROM TheatreBuilding WHERE `ownerID` =?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, ownerId);
			rs = ps.executeQuery();
			while(rs.next()){
				Theatres aTheatre = new Theatres();
				aTheatre.setTheatreName(rs.getString("Name"));
				aTheatre.setAddress(rs.getString("Address"));
				aTheatre.setCity(rs.getString("City"));
				aTheatre.setState(rs.getString("State"));
				aTheatre.setPostalCode(rs.getString("PostalCode"));
				aTheatre.setNumber(rs.getInt("Id"));
				
				theatreList.add(aTheatre);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		return theatreList;
	}
	
	public Theatres fetchTheatreDetailsForOwner(String TheatreName) {
		//open connection
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);

		
		String query = "SELECT * FROM TheatreBuilding WHERE `Name` =?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Theatres aTheatre = new Theatres();
		
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, TheatreName);
			rs = ps.executeQuery();
			while(rs.next()){
				
				aTheatre.setTheatreName(rs.getString("Name"));
				aTheatre.setAddress(rs.getString("Address"));
				aTheatre.setCity(rs.getString("City"));
				aTheatre.setState(rs.getString("State"));
				aTheatre.setPostalCode(rs.getString("PostalCode"));
				
				
				
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		return aTheatre;
	}
	
	public ArrayList<String> fetchAllTheatreName() {
		//open connection
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);

		ArrayList<String> theatreList = new ArrayList<String>();
		String query = "SELECT Name FROM TheatreBuilding";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				theatreList.add(rs.getString("Name"));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return theatreList;
	}
	
	public ArrayList<Theatres> GetAllTheare(int OwnerID) {
		//open connection
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);

		ArrayList<Theatres> TheatresList = new ArrayList<Theatres>();
		String query = "SELECT * FROM TheatreBuilding where ownerID =? " ;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, OwnerID);
			rs = ps.executeQuery();
			while(rs.next()){
				Theatres aTheatres = new Theatres();
				aTheatres.setTheatreName("Name");
				aTheatres.setAddress("Address");
				aTheatres.setCity("City");
				aTheatres.setState("State");
				aTheatres.setPostalCode("PostalCode");
				
				TheatresList.add(aTheatres);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		return TheatresList;
	}
	
	public int getTheatreId(String theatreName){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		int theatreId = 0;
		String query = "SELECT Id "
				+ "FROM TheatreBuilding WHERE Name = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, theatreName);
			rs = ps.executeQuery();
			if(rs.next()){
				theatreId = rs.getInt("Id");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return theatreId;
	}
	
	public Theatres getTheatreByShowroomId(int showroomTheatreBuildingId){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		Theatres aTheatre = new Theatres();
		String query = "SELECT tb.Name, tb.Address, tb.City, tb.State, tb.PostalCode "
				+ "FROM TheatreBuilding tb "
				+ "JOIN Showroom s ON tb.id = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, showroomTheatreBuildingId);
			rs = ps.executeQuery();
			if(rs.next()){
				aTheatre.setTheatreName(rs.getString("tb.Name"));
				aTheatre.setAddress(rs.getString("tb.Address"));
				aTheatre.setCity(rs.getString("tb.City"));
				aTheatre.setState(rs.getString("tb.State"));
				aTheatre.setPostalCode(rs.getString("tb.PostalCode"));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return aTheatre;
	}
}
