package onlineTheatreManagementAndTicketPurchasingWebApp.DB;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import onlineTheatreManagementAndTicketPurchasingWebApp.model.Transactions;
import onlineTheatreManagementAndTicketPurchasingWebApp.model.Users;

public class CreditCardsDB {

	public String getCreditCardNumByOrder(int orderId, int customerId){
		//open connection
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
				
		String creditCardNum = "";
		
		String query = "SELECT CreditCardNumber "
				+ "FROM `Order` "
				+ "WHERE Id = ? AND customerId = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, orderId);
			ps.setInt(2, customerId);
			rs = ps.executeQuery();
			if(rs.next()){
				creditCardNum = rs.getString("CreditCardNumber");
				} 
			rs.close();
			ps.close();
			} catch (SQLException e) {
				System.out.println("SQLException: ");
			e.printStackTrace();
				throw new RuntimeException(e);
			}
		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		return creditCardNum;
	}
	public void updateCreditCard(Transactions aTransaction, java.math.BigDecimal totalCost){
		//open connection
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		
		java.math.BigDecimal balance = null;
		
		String query = "SELECT Balance "
				+ "FROM CreditCard "
				+ "WHERE CreditCardNumber = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, aTransaction.getCreditCardNumber());
			rs = ps.executeQuery();
			if(rs.next()){
				balance = rs.getBigDecimal("Balance");
			} 
		rs.close();
		ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		System.out.println("Previous Balance is " + balance);
		balance = balance.subtract(totalCost);
		System.out.println("Updated Balance is " + balance);
		query = "UPDATE CreditCard SET Balance = ? "
				+ "WHERE CreditCardNumber = ?";
		ps = null;
		rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setBigDecimal(1, balance);
			ps.setString(2, aTransaction.getCreditCardNumber());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
	}
	
	//for credit card validation - returns the error number
	//0 is success, 1 if incorrect information, 2 if balance is insufficient
	public int cardNumberAndBalanceValidation(Transactions aTransaction, java.math.BigDecimal totalCost) {
		//open connection
		Connection conn = DatabaseConnection.OpenConnection(DataCred.user, DataCred.pass, DataCred.url);
		String cardHolderName = "";
		String cardType = "";
		java.math.BigDecimal balance = null;
		String cvv = "";
		String expirationDate = "";
		
		int error = 0;
		String query = "SELECT CardHolderName, Balance, CardType, CVV, ExpirationDate "
				+ "FROM CreditCard "
				+ "WHERE CreditCardNumber = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, aTransaction.getCreditCardNumber());
			rs = ps.executeQuery();
			if(rs.next()){
				cardHolderName = rs.getString("CardHolderName");
				cardType = rs.getString("CardType");
				balance = rs.getBigDecimal("Balance");
				cvv = rs.getString("CVV");
				expirationDate = rs.getString("ExpirationDate");
			} 
		rs.close();
		ps.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		conn = DatabaseConnection.CloseConnection(rs, ps, conn);
		
		if(!cardHolderName.equals(aTransaction.getCardHolderName()) || !cardType.equals(aTransaction.getCardType()) || !cvv.equals(aTransaction.getCvv()) || !expirationDate.equals(aTransaction.getExpirationDate())){
			error = 1;
			return error;
		} else if(balance.subtract(totalCost).compareTo(BigDecimal.valueOf(0.00)) < 0){
			error = 2;
			return error;
		}

		return error;
	}
	
}
