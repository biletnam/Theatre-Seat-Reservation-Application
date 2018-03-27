package onlineTheatreManagementAndTicketPurchasingWebApp.DB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import onlineTheatreManagementAndTicketPurchasingWebApp.model.MovieShowing;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Movies;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.OrderItems;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Orders;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Transactions;

public class OrdersDB {
	
	public Orders getOrderByOrderId(int orderId){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		Orders aOrder = new Orders();
		
		String query = "SELECT * FROM `Order` "
				+ "WHERE Id = ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, orderId);
			rs = ps.executeQuery();
			if(rs.next()){
				aOrder.setOrderDate(rs.getString("OrderDate"));
				aOrder.setOrderId(rs.getInt("id"));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return aOrder;
	}
	
	public void addOrderItem(int orderId, int showingId, int quantity, int totalPrice){
		
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		String query = "INSERT INTO OrderItem (OrderId, ShowingID, Quantity, TotalPrice) " +
				"VALUES (?,?,?,?)";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, orderId);
			ps.setInt(2, showingId);
			ps.setInt(3, quantity);
			ps.setInt(4, totalPrice);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
	}
	
	public int getOrderId(Orders aOrder){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		int orderId = 0;
		
		String query = "SELECT Id FROM `Order` "
				+ "WHERE CustomerId = ? AND TotalCost = ? AND OrderDate = ? AND BillingAddress = ? AND CreditCardNumber = ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, aOrder.getCustomerId());
			ps.setDouble(2, aOrder.getTotalCost());
			ps.setString(3, aOrder.getOrderDate());
			ps.setString(4, aOrder.getBillingAddress());
			ps.setString(5, aOrder.getCreditCardNumber());
			rs = ps.executeQuery();
			if(rs.next()){
				orderId = rs.getInt("Id");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return orderId;
	}
	
	public void addOrder(Orders aOrder){

		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		String query = "INSERT INTO `Order` (CustomerId, TotalCost, OrderDate, BillingAddress, CreditCardNumber) " +
				"VALUES (?,?,?,?,?)";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, aOrder.getCustomerId());
			ps.setDouble(2, aOrder.getTotalCost());
			ps.setString(3, aOrder.getOrderDate());
			ps.setString(4, aOrder.getBillingAddress());
			ps.setString(5, aOrder.getCreditCardNumber());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
	}
	
	public ArrayList<Orders> retrieveOrder(int customerId){ //view order servlet

		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		ArrayList<Orders> aOrderList = new ArrayList<Orders>();
		String query = "SELECT * FROM `Order` WHERE CustomerId = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, customerId);
			rs = ps.executeQuery();
			while(rs.next()){
				Orders aOrder = new Orders();
				aOrder.setOrderId(rs.getInt("Id"));
				aOrder.setTotalCost(rs.getInt("TotalCost"));
				aOrder.setOrderDate(rs.getString("OrderDate"));

				aOrderList.add(aOrder);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return aOrderList;
	}
	
	
	public ArrayList<OrderItems> retrieveOrderItem(int orderId){ //view order servlet

		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		ArrayList<OrderItems> aOrderItemList = new ArrayList<OrderItems>();
		String query = "SELECT * FROM `OrderItem` WHERE OrderId = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, orderId);
			rs = ps.executeQuery();
			while(rs.next()){
				int showingId = rs.getInt("ShowingID");
				int quantity = rs.getInt("Quantity");
				int totalPrice = rs.getInt("TotalPrice");
				
				OrderItems aOrderItem = new OrderItems();
				MovieShowingDB aMovieShowingDB = new MovieShowingDB();
				OrdersDB aOrder = new OrdersDB();
				aOrderItem.setaMovieShowing(aMovieShowingDB.getMovieShowingByMovieShowingId(showingId));
				aOrderItem.setaOrder(aOrder.getOrderByOrderId(orderId));
				aOrderItem.setQuantity(quantity);
				aOrderItem.setTotalPrice(totalPrice);
				aOrderItemList.add(aOrderItem);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return aOrderItemList;
	}
	
	public void deleteOrderItem(int orderId, int movieShowingId, String startTime){
		//open connection
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		
		String query = "DELETE oi.* "
				+ "FROM OrderItem oi "
				+ "JOIN `Order` o ON o.id = oi.orderId "
				+ "JOIN MovieShowing ms ON oi.showingId = ms.Id "
				+ "WHERE o.id = ? AND oi.showingId = ? AND ms.StartTime = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, orderId);
			ps.setInt(2, movieShowingId);
			ps.setString(3, startTime);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
	}
	
	public void refund(int ShowingID){

		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		String query = "SELECT o.CreditCardNumber, o.id, oi.TotalPrice "
				+ "FROM `Order` o "
				+ "JOIN OrderItem oi on o.id = oi.OrderID "
				+ "Where oi.showingID = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, ShowingID);
			rs = ps.executeQuery();
			while(rs.next()){
				int totalPrice = rs.getInt("oi.TotalPrice");
				int refund = -1*totalPrice;
				Transactions aTransactions = new Transactions();
				
				aTransactions.setCreditCardNumber(rs.getString("o.CreditCardNumber"));
				CreditCardsDB aCreditCards = new CreditCardsDB();
				aCreditCards.updateCreditCard(aTransactions, new BigDecimal(refund));
				DeleteOrder(rs.getInt("o.id"),ShowingID);
			}
			
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
	}
	public void DeleteOrder (int OrderID , int ShowingID){
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		String query = "DELETE FROM OrderItem WHERE OrderId = ? AND ShowingID = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, OrderID);
			ps.setInt(2, ShowingID);
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
