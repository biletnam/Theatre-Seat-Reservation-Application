package onlineTheatreManagementAndTicketPurchasingWebApp.DB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import onlineTheatreManagementAndTicketPurchasingWebApp.model.MovieShowing;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Users;

public class UsersDB {

	public Users getUsersByUserId(int userId){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);

		Users aUser = new Users();

		String query = "SELECT * FROM User WHERE Id = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if(rs.next()){

				aUser.setFirstName(rs.getString("FirstName"));
				aUser.setLastName(rs.getString("LastName"));
				aUser.setStreet(rs.getString("Address"));
				aUser.setCity(rs.getString("City"));
				aUser.setState(rs.getString("State"));
				aUser.setZipCode(rs.getString("PostalCode"));
				aUser.setEmailAddress(rs.getString("EmailAddress"));
				aUser.setPhoneNumber(rs.getString("PhoneNumber"));
				aUser.setUserName(rs.getString("Username"));
				aUser.setHashedAndSaltedPassword(rs.getString("HashedAndSaltedPassword"));	
				aUser.setSaltValue(rs.getString("Salt"));
			} 
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		return aUser;
	}

	public int getUserId(String userName){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);

		String query = "SELECT Id FROM User WHERE Username = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int userId = 0;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			if(rs.next()){
				userId = rs.getInt("Id");
			} 
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		return userId;
	}

	public void updatePassword(int userId, String newPassword) {
		//open connection
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);

		String hashedAndSaltedPassword = "";
		String salt = Users.generateSalt();
		try{
			hashedAndSaltedPassword = Users.hashAndSaltPassword(newPassword, salt);
		} catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		
		String query = "UPDATE User SET HashedAndSaltedPassword = ?, Salt = ? "
				+ "WHERE Id = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, hashedAndSaltedPassword);
			ps.setString(2, salt);
			ps.setInt(3, userId);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
	}
	
	public void registerUser(Users aUser) {
		//open connection
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);

		String query = "INSERT INTO User (FirstName, LastName, Address, City, State, PostalCode, EmailAddress, PhoneNumber, Username, HashedAndSaltedPassword, Salt) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, aUser.getFirstName());
			ps.setString(2, aUser.getLastName());
			ps.setString(3, aUser.getStreet());
			ps.setString(4, aUser.getCity());
			ps.setString(5, aUser.getState());
			ps.setString(6, aUser.getZipCode());
			ps.setString(7, aUser.getEmailAddress());
			ps.setString(8, aUser.getPhoneNumber());
			ps.setString(9, aUser.getUserName());
			ps.setString(10, aUser.getHashedAndSaltedPassword());
			ps.setString(11, aUser.getSaltValue());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
	}

	public boolean isOwner(String userName){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);

		boolean isOwner = false;
		int type = 1; //type 1 represent owner 
		String query = "SELECT Type FROM User WHERE Username = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			if(rs.next()){
				if(type == (rs.getInt("Type")))
					isOwner = true;
			} 
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		return isOwner;
	}

	public boolean userExist(String userName) {
		//open connection
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);

		boolean userExistBool = false;
		String query = "SELECT Username FROM User";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				if(userName.equals(rs.getString("Username")))
					userExistBool = true;
			} 
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		return userExistBool;
	}

	public boolean passwordValidation(Users aUser) {
		//open connection
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		String userName = aUser.getUserName();
		String password = aUser.getHashedAndSaltedPassword();
		
		boolean passwordExistBool = false;
		String query = "SELECT HashedAndSaltedPassword, Salt FROM User WHERE Username = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			if(rs.next()){
				String HashedAndSaltedPasswordDB = rs.getString("HashedAndSaltedPassword");
				String saltValue = rs.getString("Salt");
				try {
					if(HashedAndSaltedPasswordDB.equals(Users.hashAndSaltPassword(password, saltValue))){
						passwordExistBool = true;
					}
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		return passwordExistBool;
	}

	public int reauthenticateUser(Users aOriginalUser, Users aUser){
		int code = 1;
		String saltValue = aOriginalUser.getSaltValue();
		String hashedAndSaltedPassword = aOriginalUser.getHashedAndSaltedPassword();
		try {
			if(aOriginalUser.getUserName().trim().equals(aUser.getUserName()) && hashedAndSaltedPassword.trim().equals(Users.hashAndSaltPassword(aUser.getHashedAndSaltedPassword(), saltValue))){
				code = 0;
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return code;
	}
	
	public void validateUser(Users aUser, HttpServletRequest  request, HttpServletResponse response) throws IOException, ServletException {
		UsersDB user = new UsersDB();
		String userName = aUser.getUserName();

		// Check whether the username exists or not
		if(!user.userExist(userName)) {		
			response.sendRedirect("Registration.jsp");
		} else { 
			// Check whether the password matches or not  
			if(!passwordValidation(aUser)) {
				response.sendRedirect("Registration.jsp"); // Link-redirection
			} else {
				
				if(request.getParameter("rememberMe") != null){
					Cookie userNameCookie = new Cookie("rememberMe", aUser.getUserName());
					userNameCookie.setMaxAge(-1);
					response.addCookie(userNameCookie);
				}

				HttpSession session = request.getSession();

				Users newUser = 
						(Users)session.getAttribute("userInfo");

				if(newUser == null){
					newUser = new Users();
					session.setAttribute("userInfo", newUser);
				}

				newUser.setUserName(userName);	
				session.setAttribute("userId", user.getUserId(userName));
				session.setAttribute("ownerId", user.getUserId(userName));
				
				if(!user.isOwner(userName)){
					ArrayList<String> aTheatreList = 
							(ArrayList<String>)session.getAttribute("theatreNameList");

					aTheatreList = new ArrayList<String>();
					session.setAttribute("theatreNameList", aTheatreList);

					TheatresDB aTheatreDB = new TheatresDB();
					ArrayList<String> theatreList = aTheatreDB.fetchAllTheatreName();

					for(String name: theatreList){
						aTheatreList.add(name);
					}

					Cookie cookieList[] = request.getCookies();

					boolean newVisitor = true;

					if(cookieList != null) {
						for (int i = 0; i < cookieList.length; i++) {
							Cookie aCookie = cookieList[i];
							if(aCookie.getName().equals("previousVisitor")){
								if(aCookie.getValue().equals("yes")) {
									newVisitor = false;
									break;
								}
							}
						}	
					}

					if(newVisitor) {
						Cookie c = new Cookie("previousVisitor", "yes");
						c.setMaxAge(10 * 365 * 24 * 60 * 60); 
						response.addCookie(c);
						Cookie thumbnailCookie = new Cookie("targetAd", "images/moviePoster.jpg");
						thumbnailCookie.setMaxAge(10 * 365 * 24 * 60 * 60);
						response.addCookie(thumbnailCookie);
					} 

					RequestDispatcher dispatcher =
							request.getRequestDispatcher("CustomerHomePage.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher =
							request.getRequestDispatcher("OwnerHomePage.jsp");
					dispatcher.forward(request, response);
				}
			}
		}

	}
	
}
