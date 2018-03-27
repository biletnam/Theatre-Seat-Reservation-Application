package onlineTheatreManagementAndTicketPurchasingWebApp.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
	public static Connection OpenConnection(String username, String password, String url){
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			System.out.println("InstantiationException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		//2. Make database connection
		Connection conn = null;
		
			try {
				conn = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return conn;
		}
		
		public static Connection CloseConnection(ResultSet rs, PreparedStatement ps, Connection conn){
			
			try {
				if(rs != null && !rs.isClosed())
					rs.close();
				if(ps != null && !ps.isClosed())
					ps.close();
				if(conn != null && !conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			return conn;
		}
}
